package com.mgf.database;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * Created by Maciek on 05-Jul-15.
 */
public class MySqlConfiguration extends DriverManagerDataSource {
    public MySqlConfiguration(){
        setDriverClassName("com.mysql.jdbc.Driver");
        setUrl("jdbc:mysql://localhost:3306/indigo");
        setUsername("root");
        setPassword("");
    }
}
