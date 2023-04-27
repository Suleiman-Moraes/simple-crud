package br.com.simplecrud.integration.api.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.simplecrud.config.TestConfig;
import br.com.simplecrud.integration.api.model.dto.PersonTestDTO;
import br.com.simplecrud.integration.testcontainer.AbstractIntegrationTest;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonTestDTO person;

    @BeforeAll
    public static void setup(){
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        person = new PersonTestDTO();
    }

    @Test
    @Order(1)
    public void testCreate() {
        mockPerson();
        final String content = given().basePath("/swagger-ui/index.html")
                .port(TestConfig.SERVER_PORT).when().get().then()
                .statusCode(200)
                .extract().body().asString();
        assertNotNull("Unexpected return", content);
        assertTrue("Unexpected return", content.contains("Swagger UI"));
    }

    private void mockPerson() {
        person.setFirstName("Suleiman");
        person.setLastName("Moraes");
        person.setAddress("Goiânia - GO");
        person.setGender("Male");
    }
}
