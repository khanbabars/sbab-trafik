package com.sbab.dev.domain;


import com.sbab.dev.domain.model.JourneyPatternModel;
import com.sbab.dev.domain.model.LinesModel;
import com.sbab.dev.domain.model.StopPointsModel;
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

        log.info("Finding top lines ..");

        List<JourneyPatternModel> journeyPatterns = apiRepository.findAllJourneyPattern();

        Map<String, List<JourneyPatternModel>> lineNumbers = journeyPatterns.parallelStream().collect(Collectors.groupingBy(JourneyPatternModel::getLineNumber));

        return  lineNumbers.entrySet().stream().sorted(NumberOfStopPoints.reversed()).limit(topTenLines).map(Map.Entry::getKey).map(this::getLineById).collect(Collectors.toList());

    }




    public List<StopPointsModel> findStopsForTopLines(String lineNumber) {

        log.info("Fetching stop names for the line: {}", lineNumber);

        List<JourneyPatternModel> journeyPatterns = apiRepository.findAllJourneyPatternByLine(lineNumber);

        List<String> stopPoints = journeyPatterns.stream().map(item -> item.getPatternPoint()).collect(Collectors.toList());

        return stopPoints.stream().map(this::getStopByStopPoint).collect(Collectors.toList());


    }



    public LinesModel getLineById (String lineNumber) {
        log.debug("Requesting lineNumber for {}", lineNumber);
     return   apiRepository.findLineById(lineNumber);

    }


    public StopPointsModel getStopByStopPoint (String stopPointNumber){
        log.debug("Requesting stopName for {}", stopPointNumber);
        return   apiRepository.findStopByPoint(stopPointNumber);
    }
}



