package com.inquirer.utils;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class PropertiesLoader {

    private static final Logger LOGER = Logger.getLogger(PropertiesLoader.class);

    private String username;
    private String password;
    private String url;

    public PropertiesLoader(){
        loadProperties();
    }

    private void loadProperties(){
        Properties property = new Properties();
        try(InputStream propertiesFile = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            property.load(propertiesFile);

            username = property.getProperty("db.user");
            password = property.getProperty("db.password");
            url = property.getProperty("db.url");
        } catch (FileNotFoundException e) {
            LOGER.error(e);
        } catch (IOException e) {
            LOGER.error(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }
}
