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
public class RestRegisterTest {

    @Test
    public void loginTest() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "http://localhost:8080/register";
            HttpPost httpPost = new HttpPost(url);

            String requestBody = """
                       {
                            "email": "mahsa@gmail.com",
                            "password": "123456",
                            "phone": "091700000000",
                            "address": "address",
                            "address": "address",
                            "Users": "consumer",
                            "first_name": "first_name",
                            "last_name": "last_name",
                            "phone_num": "phone_num"
                       }
                    """;
            httpPost.setEntity(new StringEntity(requestBody));

            HttpResponse<String> execute = (HttpResponse<String>) httpClient.execute(httpPost);
            assertEquals(200, execute.statusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RestRegisterTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed successfully.");
        }
    }

}
