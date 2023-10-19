package com.sbab.dev.repository;


import com.sbab.dev.domain.model.JourneyPatternModel;
import com.sbab.dev.domain.model.LinesModel;
import com.sbab.dev.domain.model.StopPointsModel;
import com.sbab.dev.mapper.JourneyPatternMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ApiRepository {

    private final JdbcTemplate jdbcTemplate;


    public int insertLines(String lineNumber, String defaultTransport){
        String sql = "insert into lines(line_number, default_transport) values (?,?)";
        return jdbcTemplate.update(sql, new Object[] { lineNumber, defaultTransport});
    }

    public int insertJourneyPattern(String lineNumber, String defaultTransport){
        String sql = "insert into journey_pattern(line_number, pattern_point) values (?,?)";
        return jdbcTemplate.update(sql, new Object[] { lineNumber, defaultTransport});
    }

    public int insertStopPoints(String stopPoint, String stopName){
        String sql = "insert into stop_points(stop_point, stop_name) values (?,?)";
        return jdbcTemplate.update(sql, new Object[] { stopPoint, stopName});
    }


    public List<JourneyPatternModel> findAllJourneyPattern() {
        String sql = "select * from journey_pattern";
        List<JourneyPatternModel> journeyPatternModels = jdbcTemplate.query(sql, new JourneyPatternMapper());
        return journeyPatternModels;

    }

    public List<JourneyPatternModel> findAllJourneyPatternByLine(String lineNumber) {
        String sql = "select * from journey_pattern where line_number = ?";
        List<JourneyPatternModel> journeyPatternModels = jdbcTemplate.query(sql, new JourneyPatternMapper(), lineNumber);
        return journeyPatternModels;

    }

    public LinesModel findLineById(String lineNumber) {
        String sql = "select * from lines where line_number = ?";
        return (LinesModel) jdbcTemplate.queryForObject(
                sql,
                new Object[]{lineNumber},
                new BeanPropertyRowMapper(LinesModel.class));
    }

    public StopPointsModel findStopByPoint(String StopPoint) {
        String sql = "select * from stop_points where stop_point = ?";
        return (StopPointsModel) jdbcTemplate.queryForObject(
                sql,
                new Object[]{StopPoint},
                new BeanPropertyRowMapper(StopPointsModel.class));
    }


}
