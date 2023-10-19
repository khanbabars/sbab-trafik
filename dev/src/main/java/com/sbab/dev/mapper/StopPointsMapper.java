package com.sbab.dev.mapper;

import com.sbab.dev.domain.model.LinesModel;
import com.sbab.dev.domain.model.StopPointsModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StopPointsMapper implements RowMapper<StopPointsModel> {

    @Override
    public StopPointsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        StopPointsModel rows = new StopPointsModel();
        rows.setStopPoints(rs.getString("stop_point"));
        rows.setStopName(rs.getString("stop_name"));
        return rows;

    }
}

