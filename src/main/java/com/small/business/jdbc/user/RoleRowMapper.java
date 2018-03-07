package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.small.business.model.user.Role;

public class RoleRowMapper implements RowMapper {

    public Role mapRow(ResultSet resultSet, int line) throws SQLException {

        RoleExtractor userExtractor = new RoleExtractor();
        return userExtractor.extractData(resultSet);
    }

}