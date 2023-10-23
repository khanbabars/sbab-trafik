package com.sbab.dev.mapper;


import com.sbab.dev.domain.model.LinesModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LinesMapper implements RowMapper<LinesModel> {

    @Override
    public LinesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        LinesModel rows = new LinesModel();
        rows.setLineNumber(rs.getString("line_number"));
        rows.setDefaultTransport(rs.getString("default_transport"));
        return rows;

    }
}
