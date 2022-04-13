package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DuridConfig {
    public static DataSource buildDataSource() throws Exception{
        InputStream resourceAsStream = DuridConfig.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(resourceAsStream);
        return DruidDataSourceFactory.createDataSource(prop);
    }
}
