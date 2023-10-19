package com.sbab.dev.domain;


import com.sbab.dev.domain.model.JourneyPatternModel;
import com.sbab.dev.domain.model.LinesModel;
import com.sbab.dev.domain.model.StopPointsModel;
import com.sbab.dev.exception.NotFoundException;
import com.sbab.dev.repository.ApiRepository;
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
    private final ApiRepository apiRepository;

    public void callPort() {
        port.method();
    }

    private final Comparator<Map.Entry<String, List<JourneyPatternModel>>> NumberOfStopPoints = Map.Entry.comparingByValue(Comparator.comparingInt(List::size));


    public List<LinesModel> FindTopLines(int topTenLines) {

        List<JourneyPatternModel> journeyPatterns = apiRepository.findAllJourneyPattern();

        Map<String, List<JourneyPatternModel>> lineNumbers = journeyPatterns.parallelStream().collect(Collectors.groupingBy(JourneyPatternModel::getLineNumber));

        return  lineNumbers.entrySet().stream().sorted(NumberOfStopPoints.reversed()).limit(topTenLines).map(Map.Entry::getKey).map(this::getLineById).collect(Collectors.toList());

    }




    public List<JourneyPatternModel> findStopsForTopLines(String lineNumber) {

        log.info("Fetching stop names for the line: {}", lineNumber);

        List<JourneyPatternModel> journeyPatterns = apiRepository.findAllJourneyPatternByLine(lineNumber);

        journeyPatterns =  journeyPatterns.stream().filter(item -> Boolean.parseBoolean(item.getPatternPoint())).collect(Collectors.toList());

        return journeyPatterns;

        // Map<String, List<JourneyPatternModel>> patternOnLines = journeyPatterns.parallelStream().collect(Collectors.groupingBy(JourneyPatternModel::getPatternPoint));

        // List<JourneyPatternModel> getLines = patternOnLines.entrySet().stream().forEach(item -> getStopByStopPoint(item.getKey()));

        //List<JourneyPatternModel> journeyPatternsOnRequestedLine = Optional.ofNullable(patternOnLines.get(lineNumber)).orElseThrow(() -> new NotFoundException("No bus line found for the line number: "+  lineNumber));

        //return journeyPatternsOnRequestedLine.stream().map(JourneyPatternModel::getPatternPoint).map(this::getStopByStopPoint).collect(Collectors.toList());
    }



    public LinesModel getLineById (String lineNumber) {

        log.info("Fetching line number for {}", lineNumber);

     return   apiRepository.findLineById(lineNumber);

    }


    public StopPointsModel getStopByStopPoint (String stopPointNumber){

        return   apiRepository.findStopByPoint(stopPointNumber);
    }
}



