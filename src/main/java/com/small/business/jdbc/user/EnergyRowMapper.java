package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.small.business.model.user.Energy;

public class EnergyRowMapper implements RowMapper {

    public Energy mapRow(ResultSet resultSet, int line) throws SQLException {

    	EnergyExtractor energyExtractor = new EnergyExtractor();
        return energyExtractor.extractData(resultSet);
    }

}