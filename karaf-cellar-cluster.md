# Karaf Cluster creation

Extract 2 instances on 2 differet machines

Update below properties(port numbers) in second instance

### org.apache.karaf.management.cfg:
```
  rmiRegistryPort = ${env:ORG_APACHE_KARAF_MANAGEMENT_RMIREGISTRYPORT:-1098}
  rmiServerPort = ${env:ORG_APACHE_KARAF_MANAGEMENT_RMISERVERPORT:-44443}
  jmxmpPort = ${env:ORG_APACHE_KARAF_MANAGEMENT_JMXMPPORT:-9998}
```

### org.apache.karaf.shell.cfg:
```
  sshPort = ${env:ORG_APACHE_KARAF_SSH_SSHPORT:-8100}
```

## Read details at https://karaf.apache.org/manual/cellar/latest-4/#_deploy_cellar

```
  feature:repo-add cellar
  feature:list |grep -i cellar
  feature:install cellar
  la|grep -i cellar
```
Check the availability of cluster commands:
```
  cluster:<TAB>
```

Optional features:
```
feature:install cellar-eventadmin
feature:install cellar-obr
feature:install cellar-dosgi
feature:install cellar-log
```

Sometimes, you may get exception for hazelcast cluster; give a restart. If nothing is broken, you can safely ignore and give couple of restarts

## Go to /etc/hazelcast.xml

and update the config as below:
```
<group>
        <name>kodtodya-group</name>
        <password>kodtodya</password>
    </group>
```

## after changes, pls restart both servers:
Now, identify nodes:
```
cluster:node-list
``` 

Ping to across nodes:
```
cluster:node-ping <ip of other node>:<port of other node>
```

## Configure synchronizers & sync policy: (read more details: https://karaf.apache.org/manual/cellar/latest-4/#_synchronizers_and_sync_policy)

Got to `etc/org.apache.karaf.cellar.groups.cfg`:

```
default.bundle.sync = cluster
default.config.sync = cluster
default.feature.sync = cluster
default.obr.urls.sync = cluster
```

Sync the cluster explicitely:
```
cluster:sync
```

## Cellar groups
```
cluster:group-list
cluster:feature-repo-list default
cluster:feature-repo-add default camel 3.4.0
cluster:feature-repo-add default hawtio
cluster:feature-install default aries-blueprint camel-blueprint camel-quartz
```
In this ways, you can install required featurs to cluster

# valdiate the installed features something like this 
```
cluster:feature-list default | grep camel
```
