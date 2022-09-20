package com.matt.duran.fundamentals.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matt.duran.fundamentals.entity.Application;
import com.matt.duran.fundamentals.entity.ApplicationDTO;
import com.matt.duran.fundamentals.service.ApplicationService;
import com.matt.duran.fundamentals.service.ApplicationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)  // defines the runner class to run test cases.
@WebMvcTest(TzaController.class) // creates the web application context, without the server.
public class TzaControllerUnitTest {

    // @WebMvcTest will only scan the @Controller and @RestController and will not load the full application context
    // dependent beans must be mocked, or loaded in order for the application tests to run.
    // further, @WebMvCTest will work with mockito in order to carry these out

    // since we are loading only a small portion of the app we can speed up execution

    @Autowired
    private MockMvc mockMvc; // test mvc controllers without the http server. will provess http requests
                            // and provide expectations

    @MockBean
    ApplicationServiceImpl applicationService; // creates a mockito bean for the dependency of the TzaController

    @Test
    @DisplayName("Testing get request, should pass and return an empty list")
    public void getAllApplications_thenPass() throws Exception {
        mockMvc.perform(get("/tza/application/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(applicationService, times(1)).listApplications();
    }

//    @Test // needs more work to compile. right idea tho
//    @DisplayName("Testing get request, should fail")
//    public void getAllApplications_thenFail() throws Exception {
//        mockMvc.perform(get("/tza/applications"))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(content().json(""));
//
//    }

    // Create the Application to be added using the API
    @Test
    @DisplayName("using api as test in order to create newApp")
    public void createNewApplication_viaAPI() throws Exception {
        ApplicationDTO newApp = ApplicationDTO
                .builder().id(14).name("matty").owner("danny")
                .description("mock testing").build();

        // use Jackson serializiation
        String requestStr = new ObjectMapper().writeValueAsString(newApp);

        // perform the mock API call
        mockMvc.perform((post("/tza/addApp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestStr)))
                .andExpect(status().is2xxSuccessful());


    }

    // Create the Application to be added using the API
    @Test
    @DisplayName("using api as test in order to create newApp")
    public void deleteApplication_succesfull() throws Exception {

        //
        // create the object
        //

        ApplicationDTO newApp = ApplicationDTO
                .builder().id(14).name("matty").owner("danny")
                .description("mock testing").build();

        // use Jackson serializiation
        String requestStr = new ObjectMapper().writeValueAsString(newApp);

        // perform the mock API call
        mockMvc.perform((post("/tza/addApp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestStr)))
                .andExpect(status().is2xxSuccessful());


        System.out.println("Completed the add of the item ");

        // delete it
        mockMvc.perform(delete("/tza/deleteById/14"))

                .andExpect(status().is2xxSuccessful());

        System.out.println("Completed the delete of the item ");
    }

}
