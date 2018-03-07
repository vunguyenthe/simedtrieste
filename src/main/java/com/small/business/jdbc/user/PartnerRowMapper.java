package com.small.business.jdbc.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.small.business.model.user.Partner;

public class PartnerRowMapper implements RowMapper {

    public Partner mapRow(ResultSet resultSet, int line) throws SQLException {

        PartnerExtractor partnerExtractor = new PartnerExtractor();
        return partnerExtractor.extractData(resultSet);
    }

}