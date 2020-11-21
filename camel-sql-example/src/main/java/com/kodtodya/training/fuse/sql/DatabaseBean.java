package com.kodtodya.training.fuse.sql;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Bean that creates the database table
 */
public class DatabaseBean {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseBean.class);
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        String sql = "create table employees (\n"
              + "  id varchar(25) primary key,\n"
              + "  name varchar(25),\n"
              + "  city varchar(25),\n"
              + "  dept varchar(15)\n"
              + ")";

        LOG.info("Creating table employees ...");

        try {
            jdbc.execute("drop table employees");
        } catch (Throwable e) {
            // ignore
        }

        jdbc.execute(sql);

        LOG.info("... created table employees");
    }

    public void destroy() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        try {
            jdbc.execute("drop table employees");
        } catch (Throwable e) {
            // ignore
        }
    }
}
