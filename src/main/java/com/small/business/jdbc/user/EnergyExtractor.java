package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.small.business.model.user.Energy;

public class EnergyExtractor implements ResultSetExtractor {

    public Energy extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        Energy energy = new Energy();
        int id = 1;
        energy.setId(resultSet.getLong(id++));
        energy.setRoom(resultSet.getInt(id++));
        energy.setCsv_file_name(resultSet.getString(id++));
        energy.setMonth(resultSet.getInt(id++));
        energy.setSavedEnergy(resultSet.getFloat(id++));
        return energy;
    }
}
