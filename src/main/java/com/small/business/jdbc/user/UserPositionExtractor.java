package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.small.business.model.user.UserPosition;

public class UserPositionExtractor implements ResultSetExtractor {

    public UserPosition extractData(ResultSet resultSet) throws SQLException, DataAccessException {
    	UserPosition userPosition = new UserPosition();
        int id = 1;
        userPosition.setUserId(resultSet.getLong(id++));
        userPosition.setName(resultSet.getString(id++));
        userPosition.setEmail(resultSet.getString(id++));
        userPosition.setPhoneNumber(resultSet.getString(id++));
        userPosition.setAddress(resultSet.getString(id++));
        userPosition.setLongtitude(resultSet.getLong(id++));
        userPosition.setLatitude(resultSet.getLong(id++));
        return userPosition;
    }
}
