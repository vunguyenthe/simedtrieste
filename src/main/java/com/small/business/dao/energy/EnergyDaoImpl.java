package com.small.business.dao.energy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.small.business.jdbc.user.EnergyRowMapper;
import com.small.business.model.user.Energy;


@Service("energyDao")
public class EnergyDaoImpl implements EnergyDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnergyDaoImpl.class);

    @Autowired
    DataSource dataSource;

    @SuppressWarnings("unchecked")
	public List<Energy> getAllEnergy() {

        List<Energy> energyList = new ArrayList<Energy>();
        String sql = "select * from energy";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        energyList = jdbcTemplate.query(sql, new EnergyRowMapper());
        return energyList;
    }
	
    @SuppressWarnings("unchecked")
	public Energy getEnergyByRoomId(Long roomId) {

        Energy energy = new Energy();
        List<Energy> energyList = new ArrayList<Energy>();
        String sql = "select * from energy where room= " + roomId + " ORDER BY id DESC LIMIT 1";
        System.out.println("sql: " + sql);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        energyList = jdbcTemplate.query(sql, new EnergyRowMapper());
        if (energyList.size() > 0) {
            return energyList.get(0);
        }
        return energy;
    }

    public String encodeMD5(String p_str) {

        String ret = DigestUtils.md5Hex(p_str);
        return ret;
    }

    public boolean deleteEnergyById(Long id) {

        boolean ret = true;
        try {
            String sql = "delete from Energy where id =" + id;
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql);
        } catch (Exception ex) {
            LOGGER.error("deleteEnergyById got error: " + ex.getMessage());
        }
        return ret;
    }

    public boolean deleteAll() {

        boolean ret = true;
        try {
            String sql = "delete from energy";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql);
        } catch (Exception ex) {
            LOGGER.debug("deleteAll got error: " + ex.getMessage());
        }
        return ret;
    }
    public long getMaxEnergyId(String sql) {
    	long maxId = 0L;
    	try {
			Connection connection = dataSource.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while( rs.next() )
			{
			    maxId = rs.getLong("maxid");
			}
		} catch (SQLException ex) {
			LOGGER.error("getMaxId got error: " + ex.getMessage());
		}
    	System.out.println("maxEnergyId: " + maxId);
    	return maxId;
    }
    public long addEnergy(Energy energy) {

        boolean ret = true;
        long maxId = 0L;
        try {
            String sql = "INSERT INTO energy "
                    + "(room, csv_file_name, month, savedEnergy) VALUES "
                    + "(?, ?, ?, ?)";

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(
                    sql,
                    new Object[] {
                    		energy.getRoom(),
                    		energy.getCsv_file_name(),
                    		energy.getRoom(),
                    		energy.getSavedEnergy()
                    });
        } catch (Exception ex) {
            ret = false;
            LOGGER.error("addEnergy got error: " + ex.getMessage());
        }
        if(ret) {
        	String sql = "SELECT MAX(id) as maxid FROM energy"; 
        	maxId = getMaxEnergyId(sql);
        	LOGGER.debug("maxId: " + maxId);
        }        
        return maxId;
    }
    public boolean updateEnergy(Energy energy) {

        boolean ret = true;
        String sql = "update energy set room = ?, csv_file_name = ?, month = ?, savedEnergy = ? "
                + " where id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOGGER.debug("energyId = " + energy.getId());
        try {
            jdbcTemplate.update(
                    sql,
                    new Object[] {
                    		energy.getRoom(),
                    		energy.getCsv_file_name(),
                    		energy.getRoom(),
                    		energy.getSavedEnergy(),
                    		energy.getId()
                    		});
        } catch (Exception ex) {
            ret = false;
            LOGGER.error("updateEnergy got error: " + ex.getMessage());
        }
        return ret;
    }

}
