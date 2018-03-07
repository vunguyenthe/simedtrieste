package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.small.business.model.user.Role;

public class RoleExtractor implements ResultSetExtractor {

    public Role extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        Role user = new Role();
        int id = 1;
        user.setId(resultSet.getLong(id++));
        user.setRoleId(resultSet.getLong(id++));
        user.setName(resultSet.getString(id++));
        return user;
    }
}
