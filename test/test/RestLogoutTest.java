package test;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
public class RestLogoutTest {

    @Test
    public void loginTest() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            String loginUrl = "http://localhost:8080/login";
            HttpPost httpLogin = new HttpPost(loginUrl);

            String requestBody = "{\"email\": \"mahsa@gmail.com\", \"password\": \"123456\"}";
            httpLogin.setEntity(new StringEntity(requestBody));

            HttpResponse<String> executeLogin = (HttpResponse<String>) httpClient.execute(httpLogin);
            assertEquals(200, executeLogin.statusCode());
            String body = executeLogin.body();
            assertTrue(body.contains("<title>Food Waste Reduction Platform</title>"));

            String logoutUrl = "http://localhost:8080/logout";
            HttpPost httpLogout = new HttpPost(logoutUrl);
            HttpResponse<String> executeLogout = (HttpResponse<String>) httpClient.execute(httpLogout);
            assertEquals(200, executeLogout.statusCode());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RestLogoutTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed successfully.");
        }
    }

}
