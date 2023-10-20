package com.sbab.dev.integration;

import static org.junit.jupiter.api.Assertions.*;
import com.sbab.dev.utils.TestHelperUtils;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;


@ActiveProfiles("test")
@SpringBootTest
public class ApiAdapterTest {


    private static MockWebServer mockWebServer;

    @Mock
    private  ApiAdapter apiAdapter;





    @BeforeEach
    void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8082);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetEmployee() throws Exception {
        mockWebServer.enqueue(new MockResponse().setHeader("Content-Type", "application/json")
                .setBody(TestHelperUtils.readFileFromResources("pattern.json")));


        apiAdapter.getJourneyPattern();

        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals(request.getMethod(),"GET");
      //  assertEquals(request.getBody(),"/WebApi/api/employees?instance=keolis&companynumber=1&employmentnumber=002837");

    }


}

