package com.kodtodya.training.fuse.manager;


import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.Status;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySimpleCacheManager {

	Logger logger = LoggerFactory.getLogger(MySimpleCacheManager.class);

	private static final String CACHE_PATH = "/tmp/fuse-cache/kodtodya-cache";
	private static final String NAME_OF_KODTODYA_CACHE = "KODTODYA_CACHE";
	
	private CacheManager cacheManager;
			
	private Cache<String, String> cache;
		
	private static final class InstanceHolder {
		static final MySimpleCacheManager INSTANCE = new MySimpleCacheManager();
	}	
	
	public static MySimpleCacheManager getInstance() {
		return InstanceHolder.INSTANCE;
	}

	private MySimpleCacheManager() {

		logger.info("*** MySimpleCacheManager() ...");
		createCache();
		logger.info("*** MySimpleCacheManager().");
	}

	public void shutdown() {
		logger.info("*** MySimpleCache::shutdown() instance: '" + InstanceHolder.INSTANCE + "' ...");
		
		if ((InstanceHolder.INSTANCE != null) 
				&& (cacheManager != null) && (cacheManager.getStatus() == Status.AVAILABLE) ) {
			cacheManager.close();
			logger.info("***  cacheManager closed.");
		}
		
		logger.info("*** MySimpleCache::shutdown().");
	}
	
	protected int getSize() {
	    int count = 0;
	    for(Cache.Entry<String, String> entry : cache) {
	        count++;
	    }
	    return count;
	}
	
	public void populateCache() {
		
		if (getSize() < 1) {
			logger.info("*** populateCache() ..."); 
			for (int i = 0; i < 42; i++) {
				String content = new String("employee-id:" + i + "|user-id:fuse-user-" + i);
				cache.put("fuse-cache-" + i, content);
			}
			logger.info("*** populateCache()."); 
		} else {
			logger.info("*** populateCache() - cache already populated. #entries: " + getSize()); 
		}
	}
	
	public void dumpCache() {
		
		logger.info("*** dumpCache() ...");
		
		for (int i = 0; i < getSize(); i++) {
			String content = cache.get("fuse-cache-" + i);
			logger.info("***  Content: " + content);
		}
		
		logger.info("*** dumpCache().");
	}

	private void createCache() {
		
		if (cacheManager == null) {
			cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
								.with(CacheManagerBuilder.persistence(CACHE_PATH))
								.build(true);
		}
		
		if (cache == null) {
			cache = cacheManager.getCache(NAME_OF_KODTODYA_CACHE, String.class, String.class);
			logger.info("*** my cache: " + cache);
			if (cache == null) {
				cache = cacheManager.createCache(NAME_OF_KODTODYA_CACHE,
						CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
								ResourcePoolsBuilder.newResourcePoolsBuilder()
									.heap(25, EntryUnit.ENTRIES)
									.disk(10, MemoryUnit.MB, true)
							).build());	
				logger.info("*** my Cache: " + cache);
			}
		}
		
		logger.info("cache (KODTODYA_CACHE) contains entries: #" + getSize());
	}
	
}