package com.matt.duran.fundamentals.controller;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// useful for integration testing. It will start the FULL app congtext and the server
// will look for the main config class, and use that to start the full main app context
// and take in http requests! also goes on a random port to avoid clashes
@AutoConfigureMockMvc // simulates calling the code from the client, like satisffying a real HTTTP requet
public class TzaControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; // Spring boot provides this automitaclly

    @Test
    @DisplayName("Testing client call of this application")
    public void getAllApplications() throws Exception {
        ResponseEntity<List> response = this.restTemplate.getForEntity("http://localhost:"+ port + "/tza/application/all", List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
