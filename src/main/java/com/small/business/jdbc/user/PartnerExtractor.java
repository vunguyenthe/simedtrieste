package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.small.business.model.user.Partner;

public class PartnerExtractor implements ResultSetExtractor {

    public Partner extractData(ResultSet resultSet) throws SQLException, DataAccessException {

    	Partner partner = new Partner();
        partner.setId(resultSet.getLong("id"));
        partner.setUserId(resultSet.getLong("userId"));
        partner.setIdCard(resultSet.getString("idCard"));
        partner.setPassportNumber(resultSet.getString("passportNumber"));
        partner.setTempAddress(resultSet.getString("tempAddress"));
        partner.setPermanentAddress(resultSet.getString("permanentAddress"));
        partner.setBankAccountNumberId(resultSet.getString("bankAccountNumberId"));
        return partner;
    }
}
