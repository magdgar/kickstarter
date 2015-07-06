package com.mgf.database;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * Created by Maciek on 05-Jul-15.
 */
@Component
public class JdbcConnection extends JdbcTemplate{
    public JdbcConnection() {
        setDataSource(new MySqlConfiguration());
    }
}
