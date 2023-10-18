package com.sbab.dev.domain;


import com.sbab.dev.domain.dto.JourneyPattern;
import com.sbab.dev.domain.dto.Line;
import com.sbab.dev.domain.dto.StopPoint;
import com.sbab.dev.exception.NotFoundException;
import com.sbab.dev.integration.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {

    private final ApiPort port;

    @Autowired
    private final ApiUtils apiUtils;

    public void callPort() {
        port.method();
    }

    private final Comparator<Map.Entry<String, List<JourneyPattern.Result>>> NumberOfStopPoints = Map.Entry.comparingByValue(Comparator.comparingInt(List::size));


    public List<Line.Result> FindTopLines(int topTenLines) {

        List<JourneyPattern.Result> journeyPatterns = apiUtils.getJourneyPattern();

        Map<String, List<JourneyPattern.Result>> lineNumbers = journeyPatterns.parallelStream().collect(Collectors.groupingBy(JourneyPattern.Result::getLineNumber));

        return  lineNumbers.entrySet().stream().sorted(NumberOfStopPoints.reversed()).limit(topTenLines).map(Map.Entry::getKey).map(this::getLineById).collect(Collectors.toList());

    }




    public List<StopPoint.Result> findStopsForTopLines(String lineNumber) {

        log.info("Fetching stop names for the line: {}", lineNumber);

        List<JourneyPattern.Result> journeyPatterns = apiUtils.getJourneyPattern();

        Map<String, List<JourneyPattern.Result>> lineNumbers = journeyPatterns.parallelStream().collect(Collectors.groupingBy(JourneyPattern.Result::getLineNumber));

        List<JourneyPattern.Result> journeyPatternsOnRequestedLine = Optional.ofNullable(lineNumbers.get(lineNumber)).orElseThrow(() -> new NotFoundException("No bus line found for the line number: "+  lineNumber));

        return journeyPatternsOnRequestedLine.stream().map(JourneyPattern.Result::getJourneyPatternPointNumber).map(this::getStopByStopPoint).collect(Collectors.toList());
    }

    public Line.Result getLineById (String lineNumber) {

        return apiUtils.getLines().stream().filter(item -> item.getLineNumber().equals(lineNumber)).findAny()
        .orElseThrow(()-> new IllegalStateException("No LineNumber found: " + lineNumber));
    }

    public StopPoint.Result getStopByStopPoint (String stopPointNumber){

        return   apiUtils.getStopPoints().stream().filter(item -> item.getStopPointNumber().equals(stopPointNumber)).findAny()
        .orElseThrow(()-> new IllegalStateException("No StopPointNumber: " + stopPointNumber));
    }
}



