package br.com.simplecrud.integration.api.swagger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.simplecrud.config.TestConfig;
import br.com.simplecrud.integration.testcontainer.AbstractIntegrationTest;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testShouldDisplaySwaggerUiPage() {
        final String content = given().basePath("/swagger-ui/index.html")
                .port(TestConfig.SERVER_PORT).when().get().then()
                .statusCode(200)
                .extract().body().asString();
        assertNotNull("Unexpected return", content);
        assertTrue("Unexpected return", content.contains("Swagger UI"));
    }
}
