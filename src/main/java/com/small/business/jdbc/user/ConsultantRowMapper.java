package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.small.business.model.user.Consultant;

public class ConsultantRowMapper implements RowMapper {

    public Consultant mapRow(ResultSet resultSet, int line) throws SQLException {

        ConsultantExtractor userExtractor = new ConsultantExtractor();
        return userExtractor.extractData(resultSet);
    }

}