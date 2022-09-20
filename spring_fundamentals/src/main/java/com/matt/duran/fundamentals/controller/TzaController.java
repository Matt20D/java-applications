package com.matt.duran.fundamentals.controller;

import com.matt.duran.fundamentals.entity.Application;
import com.matt.duran.fundamentals.entity.ApplicationDTO;
import com.matt.duran.fundamentals.exception.ApplicationNotFoundException;
import com.matt.duran.fundamentals.service.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// these are spring specific annotations
@RestController // only accept Rest Endpoints. We are only returning object data written to HTTP response as JSON
                // adds @Controller, @ResponseBody annotations essentially
@RequestMapping("/tza") // base root of the request endpoint here

/**
 * here we have a basic crud controller, where we can read, update, delete, and create Application Entities
 */
public class TzaController {

    // since we are a rest controller we are not returning Models, i.e. for thymeleaf views.
    // we are returning serialized JSON objects to the http ResponseEntity
    // this is meant to be consumed as an API

    private ApplicationServiceImpl applicationService;

    @Autowired
    public void setApplicationService(ApplicationServiceImpl applicationService) {this.applicationService = applicationService; }


    /**
     * basic crud methods on this Application resource
     */


    @GetMapping("/application/all")
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> list = applicationService.listApplications();
        return new ResponseEntity<List<Application>>(list, HttpStatus.OK);
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable("id") long id) {

        try {
            return new ResponseEntity<Application>(applicationService.findApplication(id), HttpStatus.OK);

            // catch the HTTP response and then send a new one on the way.
        } catch (ApplicationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application Not Found");
        }
    }

    // sample curl request
    // curl -d '{"id": 6, "name": "Matt-App", "description": "test-add", "owner":"dizzle"}' -H 'Content-Type: application/json' -X POST http://localhost:8080/tza/addApp

    /**
     * This works now --__--
     */
    @PostMapping("/addApp")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicationDTO> create(@RequestBody ApplicationDTO newAppDTO) {

        try {
            var temp = applicationService.saveApp(newAppDTO);
            return new ResponseEntity<ApplicationDTO>(temp, HttpStatus.CREATED);
        } catch (ApplicationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create newApp");
        }
    }

    /**
     * This works now --__--
     */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") long idToDelete) {
        try {
            applicationService.deleteApp(idToDelete);
            return new ResponseEntity<Long>(idToDelete, HttpStatus.OK);
        } catch (ApplicationNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete newApp by ID");
        }
    }


}
