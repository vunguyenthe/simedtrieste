package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.small.business.model.user.User;

public class UserRowMapper implements RowMapper {

    public User mapRow(ResultSet resultSet, int line) throws SQLException {

        UserExtractor userExtractor = new UserExtractor();
        return userExtractor.extractData(resultSet);
    }

}