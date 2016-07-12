package com.inquirer.utils;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.apache.log4j.Logger;

import java.sql.*;

public class DaoHelper {

    private static final Logger LOGER = Logger.getLogger(PropertiesLoader.class);

    public DaoHelper(){
        loadDriver();
    }

    private void loadDriver(){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e){
            LOGER.error(e);
        }
    }

    public PreparedStatement getStatement(String query) throws SQLException {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        String url = propertiesLoader.getUrl();
        String username = propertiesLoader.getUsername();
        String password = propertiesLoader.getPassword();
        Connection con = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = con.prepareStatement(query);
        return statement;
    }
}
