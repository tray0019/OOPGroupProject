package test;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
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
public class RestLoginTest {

    @Test
    public void loginTest() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "http://localhost:8080/login";
            HttpPost httpPost = new HttpPost(url);

            String requestBody = "{\"email\": \"mahsa@gmail.com\", \"password\": \"123456\"}";
            httpPost.setEntity(new StringEntity(requestBody));

            HttpResponse<String> execute = (HttpResponse<String>) httpClient.execute(httpPost);
            assertEquals(200, execute.statusCode());
            String body = execute.body();
            Assertions.assertTrue(body.contains("<title>Food Waste Reduction Platform</title>"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RestLoginTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed successfully.");
        }
    }

}
