package com.mgf.service;

import com.mgf.database.JdbcConnection;
import com.mgf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Maciek on 04-Jul-15.
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.GET)
    public User login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password){
        return (checkIfInDatabase(name)) ? new User(name, password) : null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createUser(@RequestParam(value = "name")String name, @RequestParam(value = "password") String password){
        if (! checkIfInDatabase(name))
            addUserToDatabase(name, password);
    }

    private boolean checkIfInDatabase(String login){
        return jdbcConnection.queryForObject("SELECT COUNT(*) from indigo.users where Login = ?", Integer.class , login) > 0;
    }

    private void addUserToDatabase(String login, String password){
        jdbcConnection.update("insert into Users(login, password) values (?, ?)", login, password);
    }




}
