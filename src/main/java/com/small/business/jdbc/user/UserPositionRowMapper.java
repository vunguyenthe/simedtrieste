package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.small.business.model.user.UserPosition;

public class UserPositionRowMapper implements RowMapper {

    public UserPosition mapRow(ResultSet resultSet, int line) throws SQLException {

    	UserPositionExtractor userPositionExtractor = new UserPositionExtractor();
        return userPositionExtractor.extractData(resultSet);
    }

}