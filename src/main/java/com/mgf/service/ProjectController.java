package com.mgf.service;


import com.mgf.database.JdbcConnection;
import com.mgf.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/project")
class ProjectController {
    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Project> getProject(@RequestParam(value = "name") String name){
        return (checkIfInDatabase(name)) ? new ResponseEntity<>(getprojectFromDB(name), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity createProject(@RequestParam(value = "name") String name, @RequestParam(value = "desc") String desc, @RequestParam(value = "creatorID") int creatorID){
        if (! checkIfInDatabase(name)) {
            addProjectToDatabase(name, desc, creatorID);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private boolean checkIfInDatabase(String name){
        return jdbcConnection.queryForObject("SELECT COUNT(*) from kickstarter.projects where name = ?", Integer.class , name) > 0;
    }

    private void addProjectToDatabase(String name, String desc, int creatorID){
        jdbcConnection.update("insert into Projects(name, description, creator) values (?,?,?)", name, desc, creatorID);
    }

    private Project getprojectFromDB(String name){
        String desc = jdbcConnection.queryForObject("SELECT description from kickstarter.projects where name = ?", String.class , name);
        int creatorID =  jdbcConnection.queryForObject("SELECT creator from kickstarter.projects where name = ?", Integer.class , name);
        return new Project(name, desc, creatorID);
    }
}