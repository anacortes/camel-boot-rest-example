package com.examples;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.client.HttpEndpointConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = MyRouter.class)
@WebIntegrationTest("server.port:9090")
public class MyRouterTest extends TestNGCitrusTestDesigner {

    private HttpClient httpClient;

    @BeforeClass
    public void setupClient() {
        HttpEndpointConfiguration config = new HttpEndpointConfiguration();
        config.setRequestUrl("http://localhost:9090/hello");
        httpClient = new HttpClient(config);
    }

    @Test
    @CitrusTest
    public void test() {
        http().client(httpClient).get();

        http().client(httpClient)
                .response(HttpStatus.OK)
                .payload("Hello world!!! My name is Tom")
                .validator("defaultPlaintextMessageValidator");
    }

}