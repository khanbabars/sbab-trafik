package com.sbab.dev.rest;

import com.sbab.dev.domain.ApiService;
import com.sbab.dev.domain.model.LinesModel;
import com.sbab.dev.domain.model.StopPointsModel;
import com.sbab.dev.integration.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = APPLICATION_JSON_VALUE)
public class Controller {


    @Autowired
    private ApiService apiService;


    @Autowired
    ApiUtils apiUtils;

    @GetMapping(value="/toplines", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LinesModel> findLinesWithMostStops() {
        return apiService.FindTopLines(10);
    }


    @GetMapping(value="/stopsbyname/{lineNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StopPointsModel> findStopPoints(@PathVariable ("lineNumber")String lineNumber) {
        return apiService.findStopsForTopLines(lineNumber);

    }


}
