package com.sbab.dev.domain;


import com.sbab.dev.domain.dto.JourneyPattern;
import com.sbab.dev.domain.dto.Line;
import com.sbab.dev.domain.dto.StopPoint;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ApiPort {

    void method();

    JourneyPattern getJourneyPattern();

    Line getLines();

    StopPoint getStopPoints();

     ResponseEntity<List<Line.Result>> getLineHttp();


}
