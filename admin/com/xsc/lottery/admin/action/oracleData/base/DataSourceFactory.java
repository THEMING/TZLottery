package com.xsc.lottery.admin.action.oracleData.base;

import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DataSourceFactory
{
    private static DataSource dataSource;
    static {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("jdbc_oracle");
            String driverClassName = resource.getString("proxool.driver");
            String url = resource.getString("proxool.url");
            String username = resource.getString("proxool.username");
            String password = resource.getString("proxool.password");
            Properties prop = new Properties();
            prop.put("driverClassName", driverClassName);
            prop.put("username", username);
            prop.put("password", password);
            prop.put("url", url);
            prop.setProperty("maxActive", "20");
            prop.setProperty("maxIdle", "5");
            prop.setProperty("minIdle", "5");
            System.out.println("oracleo数据连接==========读取配置文件正常");
            dataSource = BasicDataSourceFactory.createDataSource(prop);
        }
        catch (Exception e) {
            System.out.println("oracleo数据连接==========数据源创建出错！");
        }
    }

    public static DataSource getDataSource()
    {
        return dataSource;
    }

    public static void closeDataSource()
    {
        try {
            ((BasicDataSource) dataSource).close();
        }
        catch (SQLException e) {
            System.out.println("oracleo数据连接==========数据源关闭出错！");
        }
    }
}
