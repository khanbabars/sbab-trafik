package com.sbab.dev.rest;



import com.sbab.dev.domain.ApiService;
import com.sbab.dev.domain.dto.JourneyPattern;
import com.sbab.dev.domain.dto.Line;
import com.sbab.dev.domain.dto.StopPoint;
import com.sbab.dev.domain.model.JourneyPatternModel;
import com.sbab.dev.domain.model.LinesModel;
import com.sbab.dev.domain.model.StopPointsModel;
import com.sbab.dev.integration.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = APPLICATION_JSON_VALUE)
public class Controller {


    @Autowired
    private ApiService apiService;


    @Autowired
    ApiUtils apiUtils;

    @GetMapping(value = "/toplines", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LinesModel> findLinesWithMostStops() {
        return apiService.FindTopLines(10);
    }


    @GetMapping(value = "/stopsbyname/{linesNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<JourneyPatternModel> findStopPoints(@RequestParam("linesNumber")String linesNumber) {
        return apiService.findStopsForTopLines(linesNumber);

    }


}
