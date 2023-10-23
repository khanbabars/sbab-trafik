package com.sbab.dev.integration;


import com.sbab.dev.config.AppSettings;
import com.sbab.dev.domain.dto.JourneyPattern;
import com.sbab.dev.domain.dto.Line;
import com.sbab.dev.domain.dto.StopPoint;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.unit.DataSize;
import java.io.IOException;
import static org.junit.Assert.assertEquals;




@Slf4j
@ActiveProfiles("test")
@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiAdapterTest {


    @Autowired
    private WebTestClient webTestClient;


    private  static  ApiAdapter apiAdapter;


    @BeforeEach
    void setUp() throws Exception {


        AppSettings appSettings = new AppSettings(new AppSettings.Api(  "https://api.sl.se/api2/LineData.json?key=bf16563b8b814066b3b4e7b6a0b35e4a"
                ,"https://api.sl.se/api2/linedata.json?key=bf16563b8b814066b3b4e7b6a0b35e4a&model=line&DefaultTransportModeCode=BUS"
                ,"bf16563b8b814066b3b4e7b6a0b35e4a"
                ,"https://api.sl.se/api2/linedata.json?key=bf16563b8b814066b3b4e7b6a0b35e4a&model=jour&DefaultTransportModeCode=BUS",
                "https://api.sl.se/api2/linedata.json?key=bf16563b8b814066b3b4e7b6a0b35e4a&model=stop",
                300000,
                DataSize.ofMegabytes(50)));

        apiAdapter  = new ApiAdapter(appSettings);
    }
    @AfterEach
    void tearDown() throws IOException {}



    @Test
    void getJourneyPatternTest() throws Exception {
        JourneyPattern journeyPattern = apiAdapter.getJourneyPattern();
        assertEquals(journeyPattern.getResponseData().getType(), "JourneyPatternPointOnLine");
    }

    @Test
    void getLinesTest() {
        Line line = apiAdapter.getLines();
        assertEquals(line.getResponseData().getType(), "Line");

    }

    @Test
    void StopPoint(){
        StopPoint stopPoint = apiAdapter.getStopPoints();
        assertEquals(stopPoint.getResponseData().getType(),"StopPoint");
    }



}

