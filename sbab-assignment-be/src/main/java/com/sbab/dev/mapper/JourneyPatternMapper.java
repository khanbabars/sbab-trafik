package com.sbab.dev.mapper;

import com.sbab.dev.domain.model.JourneyPatternModel;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JourneyPatternMapper implements RowMapper<JourneyPatternModel> {

    @Override
    public JourneyPatternModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        JourneyPatternModel rows = new JourneyPatternModel();
        rows.setLineNumber(rs.getString("line_number"));
        rows.setPatternPoint(rs.getString("pattern_point"));
        return rows;

    }
}

