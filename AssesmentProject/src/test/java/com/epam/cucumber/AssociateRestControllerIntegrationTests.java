package com.epam.cucumber;

import com.epam.dto.AssociateDto;
import com.epam.model.Batch;
import com.epam.restcontroller.AssociateRestController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssociateRestControllerIntegrationTests {

    AssociateDto associateDto;

    String gender="";
    Batch batch;
    private ResponseEntity<String> response;

    RestTemplate restTemplate=new RestTemplate();


    @Given("the client wants to create an associate")
    public void the_client_wants_to_create_an_associate() {
        batch=new Batch();
        batch.setId(1);
        batch.setName("rd-java-2023");
        batch.setPractice("java");
        batch.setStartDate(new Date());
        batch.setEndDate(new Date());

        associateDto=new AssociateDto();
        associateDto.setBatch(batch);
        associateDto.setCollege("vignan");
        associateDto.setEmail("lavanya@gmail.com");
        associateDto.setGender("Male");
        associateDto.setId(1);
        associateDto.setName("lavanya");
        associateDto.setStatus("Active");

    }
    @When("the client sends a POST request to {string} with valid associate data")
    public void the_client_sends_a_post_request_to_with_valid_associate_data(String string) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String validAssociateData;
        HttpEntity<AssociateDto> requestEntity = new HttpEntity<>(associateDto, headers);
        response = restTemplate.exchange("http://localhost:7000/associates", HttpMethod.POST, requestEntity, String.class);


    }
    @Then("the response status code should be {int} Created")
    public void the_response_status_code_should_be_created(Integer int1) {
        assertEquals(int1, response.getStatusCodeValue());
    }
    @Then("the response body should contain the created associate's details")
    public void the_response_body_should_contain_the_created_associate_s_details() throws JSONException {
        String responseBody = response.getBody();
        assertNotNull(responseBody);
    }

    @Given("the client wants to retrieve associates by gender")
    public void the_client_wants_to_retrieve_associates_by_gender() {
       gender="male";
    }
    @When("the client sends a GET request to {string} with a valid gender")
    public void the_client_sends_a_get_request_to_with_a_valid_gender(String string) {
        response=restTemplate.exchange("http://localhost:7000/associates/gender", HttpMethod.GET,null, String.class);
    }
    @Then("the response status code should be {int} OK")
    public void the_response_status_code_should_be_ok(Integer int1) {
        assertEquals(int1,response.getStatusCode().value());
    }
    @Then("the response body should contain a list of associates with the specified gender")
    public void the_response_body_should_contain_a_list_of_associates_with_the_specified_gender() {
        assertNotNull(response.getBody());
    }

}
