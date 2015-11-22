package com.mgf.service;

import com.mgf.database.JdbcConnection;
import com.mgf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> login(@RequestParam(value = "name") String name){
        return (checkIfInDatabase(name)) ? new ResponseEntity<>(new User(name), HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity createUser(@RequestParam(value = "name")String name){
        if (! checkIfInDatabase(name)) {
            addUserToDatabase(name);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private boolean checkIfInDatabase(String login){
        return jdbcConnection.queryForObject("SELECT COUNT(*) from kickstarter.users where name = ?", Integer.class , login) > 0;
    }

    private void addUserToDatabase(String login){
        jdbcConnection.update("insert into Users(name) values (?)", login);
    }
}
