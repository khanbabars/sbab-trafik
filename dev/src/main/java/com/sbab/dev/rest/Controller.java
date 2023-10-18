package com.sbab.dev.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbab.dev.domain.ApiPort;
import com.sbab.dev.domain.ApiService;
import com.sbab.dev.domain.dto.JourneyPattern;
import com.sbab.dev.domain.dto.Line;
import com.sbab.dev.domain.dto.StopPoint;
import com.sbab.dev.integration.ApiAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api", produces = APPLICATION_JSON_VALUE)
public class Controller {


    @Autowired
    private ApiService apiService;



    @GetMapping(value = "/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Line.Result> findLinesWithMostStops() {

        return apiService.FindTopLines(10);

    }


    @GetMapping(value = "/v4/{linesNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StopPoint.Result> findStopPoints(@RequestParam("linesNumber")String linesNumber) {

        return apiService.findStopsForTopLines(linesNumber);

    }

}
