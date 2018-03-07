package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.small.business.model.user.Consultant;

public class ConsultantExtractor implements ResultSetExtractor<Object> {

    public Consultant extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        Consultant user = new Consultant();
        int id = 1;
        user.setId(resultSet.getLong(id++));
        user.setConsultantId(resultSet.getLong(id++));
        user.setName(resultSet.getString(id++));
        return user;
    }
}
