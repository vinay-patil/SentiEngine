package com.hospitality.sentimentanalysis.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;


public class DatabaseQueryExecuter {

    protected static Logger logger = Logger.getLogger(DatabaseQueryExecuter.class.getName());

    public static void execute(final String... executionSteps)
    {
        template().batchUpdate(executionSteps);
    }

    public static <T> T executeQuery(final String executionQuery, Class<T> klass) {

        if (null == executionQuery || executionQuery == "") {
            return null;
        }
        logger.debug("Executing Query" + executionQuery);
        return template().queryForObject(executionQuery, klass);
    }

    public static <T> List<Map<String,T>> executeForListOfMaps(String sqlQuery, final Class<T> klass){

        logger.debug("Executing Query" + sqlQuery);
        return template().query(sqlQuery,new ResultSetExtractor<List<Map<String,T>>>(){


            @SuppressWarnings("unchecked")
            @Override
            public List<Map<String, T>> extractData(ResultSet rs) throws SQLException, DataAccessException , ClassCastException {

                List<Map<String, T>> resultQueryList = new ArrayList<Map<String,T>>();
                ResultSetMetaData md = rs.getMetaData();
                while(rs.next())
                {
                    Map<String, T> map = (Map<String, T>) new LinkedHashMap<String, Object>(md.getColumnCount());
                    for (int i = 1; i <= md.getColumnCount(); ++i) {
                        map.put(md.getColumnName(i), (T) (null == rs.getObject(i) ? "" : klass.cast(rs.getString(i))));
                    }
                    resultQueryList.add(map);
                }
                return resultQueryList;
            }

        });
    }

    public static JdbcTemplate template() {
        return new JdbcTemplate(SpringApplicationContextLoaderFS.jdbcTemplate.getDataSource());
    }
}


