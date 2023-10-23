package com.sbab.dev.integration;

import com.sbab.dev.domain.ApiPort;
import com.sbab.dev.domain.dto.JourneyPattern;
import com.sbab.dev.domain.dto.Line;
import com.sbab.dev.domain.dto.StopPoint;
import com.sbab.dev.repository.ApiRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
@RequiredArgsConstructor
@Slf4j
@Component
public class ApiUtils {


    private final ApiPort apiPort;

    private  final ApiRepository apiRepository;


    @PostConstruct
    public List<JourneyPattern.Result> getJourneyPattern() {
        try {
            JourneyPattern   journeyPattern = apiPort.getJourneyPattern();
            log.debug("Received line from journey patterns data from the traffic api");

            if (!journeyPattern.getResponseData().getResult().isEmpty()) {
                List<JourneyPattern.Result> journeyPatterns = journeyPattern.getResponseData().getResult();
                journeyPatterns.stream().forEach(item -> apiRepository.insertJourneyPattern(item.getLineNumber(), item.getJourneyPatternPointNumber()));
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
    public List<StopPoint.Result> getStopPoints() {
        try {
            StopPoint   stopPoint = apiPort.getStopPoints();

            log.debug("Received line from stop point data from the traffic api");

            if (!stopPoint.getResponseData().getResult().isEmpty()) {
                List<StopPoint.Result> stopPoints = stopPoint.getResponseData().getResult();
                stopPoints.stream().forEach(item -> apiRepository.insertStopPoints(item.getStopPointNumber(), item.getStopPointName()));
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
    public List<Line.Result> getLines() {
        try {
            Line line = apiPort.getLines();

            log.debug("Received line from line data from traffic api");

            if (!line.getResponseData().getResult().isEmpty()) {
                List<Line.Result> lines = line.getResponseData().getResult();
                lines.stream().forEach(item -> apiRepository.insertLines(item.getLineNumber(), item.getDefaultTransportModeCode()));
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