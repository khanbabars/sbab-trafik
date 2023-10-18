package com.sbab.dev.integration;

import com.sbab.dev.domain.ApiPort;
import com.sbab.dev.domain.dto.JourneyPattern;
import com.sbab.dev.domain.dto.Line;
import com.sbab.dev.domain.dto.StopPoint;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
@RequiredArgsConstructor
@Slf4j
@Component
public class ApiUtils {


    private final ApiPort apiPort;


    @PostConstruct
    @Cacheable("JourneyPattern")
    public List<JourneyPattern.Result> getJourneyPattern() {

        JourneyPattern journeyPattern = null;

        try {
            journeyPattern = apiPort.getJourneyPattern();
            log.debug("Received line from journey patterns data from the traffic api");

            if (!journeyPattern.getResponseData().getResult().isEmpty()) {
                List<JourneyPattern.Result> journeyPatterns = journeyPattern.getResponseData().getResult();
                return journeyPatterns;
            }
        } catch (WebClientResponseException ex) {
            if (ex.getStatusCode().value() != 200) {
                log.warn("getLine Error! Response code was {}. The request was: {}", ex.getStatusCode().value());
                log.warn("The response body was: {}", ex.getResponseBodyAsString());
            }
        }

        return null;
    }




    @PostConstruct
    @Cacheable("StopPoints")
    public List<StopPoint.Result> getStopPoints() {

        StopPoint stopPoint = null;

        try {
            stopPoint = apiPort.getStopPoints();

            log.debug("Received line from stop point data from the traffic api");

            if (!stopPoint.getResponseData().getResult().isEmpty()) {
                List<StopPoint.Result> stopPoints = stopPoint.getResponseData().getResult();

                return stopPoints;
            }
        } catch (WebClientResponseException ex) {
            if (ex.getStatusCode().value() != 200) {
                log.warn("getLine Error! Response code was {}. The request was: {}", ex.getStatusCode().value());
                log.warn("The response body was: {}", ex.getResponseBodyAsString());
            }
        }

        return null;
    }





    @PostConstruct
    @Cacheable("getLines")
    public List<Line.Result> getLines() {

        Line line = null;

        try {
            line = apiPort.getLines();
            log.debug("Received line from line data from traffic api");
            if (!line.getResponseData().getResult().isEmpty()) {
                List<Line.Result> lines = line.getResponseData().getResult();
                return lines;
            }
        } catch (WebClientResponseException ex) {
            if (ex.getStatusCode().value() != 200) {
                log.warn("getLine Error! Response code was {}. The request was: {}", ex.getStatusCode().value());
                log.warn("The response body was: {}", ex.getResponseBodyAsString());
            }
        }

        return null;
    }


}
