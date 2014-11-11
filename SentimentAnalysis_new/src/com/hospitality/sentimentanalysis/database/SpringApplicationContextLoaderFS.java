package com.hospitality.sentimentanalysis.database;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author ggore
 */
public class SpringApplicationContextLoaderFS {

    public static final String SPRING_JDBC_TEMPLATE_BEAN_ID = "JdbcTemplate";
    public static final String SPRING_JDBC_DATASOURCE = "DataSource";

    public static final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:databaseContext.xml");
    public static final JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean(SPRING_JDBC_TEMPLATE_BEAN_ID);
    public static final DataSource genericDataSource = (DataSource) context.getBean(SPRING_JDBC_DATASOURCE);

}
