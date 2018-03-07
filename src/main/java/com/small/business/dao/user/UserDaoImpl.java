package com.small.business.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.small.business.jdbc.user.UserPositionRowMapper;
import com.small.business.jdbc.user.UserRowMapper;
import com.small.business.model.user.User;
import com.small.business.model.user.UserPosition;

@Service("userDao")
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    DataSource dataSource;

    public List<User> getAllUser() {

        List userList = new ArrayList();
        String sql = "select * from user";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userList = jdbcTemplate.query(sql, new UserRowMapper());
        return userList;
    }
    
	public List<UserPosition> getAllUserPosition() {
        List userPositionList = new ArrayList<UserPosition>();
        String sql = "select u.id as userId, u.name, u.email, u.phoneNumber, u.address, p.longtitude, p.latitude "
        		+ "from user u, position p ";
        	sql += "where u.id = p.userId";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userPositionList = jdbcTemplate.query(sql, new UserPositionRowMapper());
        return userPositionList;
    }
    
	public UserPosition getUserPosition(Long userId) {
		UserPosition userPosition= new UserPosition();
        List<UserPosition> userPositionList = new ArrayList<UserPosition>();
        String sql = "select u.id as userId, u.name, u.email, u.phoneNumber, u.address, p.longtitude, p.latitude "
        		+ "from user u, position p ";
        	sql += "where u.id = p.userId and p.userId = " + userId;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userPositionList = jdbcTemplate.query(sql, new UserPositionRowMapper());
        if (userPositionList.size() > 0) {
            return userPositionList.get(0);
        }
        return userPosition;
    }
	
    public User getUserById(Long id) {

        User user = new User();
        List<User> userList = new ArrayList<User>();
        String sql = "select * from user where id= " + id;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userList = jdbcTemplate.query(sql, new UserRowMapper());
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return user;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {

        String sql = "SELECT * FROM user WHERE email = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<User> userList = jdbcTemplate.query(sql, new Object[] { email }, new UserRowMapper());
        if (userList.size() == 1) {
            return Optional.of(userList.get(0));
        } else if (userList.size() > 1) {
            throw new RuntimeException("Multiple user with the same email:" + email);
        }
        return Optional.empty();
    }

    public String encodeMD5(String p_str) {

        String ret = DigestUtils.md5Hex(p_str);
        return ret;
    }

    public Long validateUser(String user, String password) {

        Long userId = 0L;

        try {
            if (user == null || user == "" || user.length() == 0)
                return userId;
            if (password == null || password == "" || password.length() == 0)
                return userId;
            List<User> userList = new ArrayList<User>();
            String sql = "select * from user where name = ? and password = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            userList = jdbcTemplate.query(sql, new Object[] {user,  encodeMD5(password)}, new UserRowMapper());
            if (userList.size() > 0) {
                userId = userList.get(0).getId();
            }
            return userId;
        } catch (Exception ex) {
            LOGGER.error("validateUser got error: " + ex.getMessage());
        }
        return userId;
    }

    public boolean deleteUserById(Long id) {

        boolean ret = true;
        try {
            String sql = "delete from User where id =" + id;
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql);
        } catch (Exception ex) {
            LOGGER.error("deleteUserById got error: " + ex.getMessage());
        }
        return ret;
    }

    public boolean deleteAll() {

        boolean ret = true;
        try {
            String sql = "delete from user";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql);
        } catch (Exception ex) {
            LOGGER.debug("deleteAll got error: " + ex.getMessage());
        }
        return ret;
    }
    public long getMaxUserId(String sql) {
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
    	System.out.println("maxUserId: " + maxId);
    	return maxId;
    }
    public long addUser(User user) {

        boolean ret = true;
        long maxId = 0L;
        try {
            String sql = "INSERT INTO user "
                    + "(name, email, phoneNumber, password1, password2,"
                    + "address, userType, activated) VALUES "
                    + "(?, ?, ?, ?, ?,"
                    + " ?, ?, ?)";

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(
                    sql,
                    new Object[] {
                    		user.getName(),
                    		user.getEmail(),
                    		user.getPhoneNumber(),
                    		user.getPassword1(),
                    		user.getPassword2(),
                    		user.getAddress(),
                    		user.getUserType(),
                    		user.getActivated()
                    });
        } catch (Exception ex) {
            ret = false;
            LOGGER.error("addUser got error: " + ex.getMessage());
        }
        if(ret) {
        	String sql = "SELECT MAX(id) as maxid FROM user"; 
        	maxId = getMaxUserId(sql);
        	LOGGER.debug("maxId: " + maxId);
        }        
        return maxId;
    }
    public boolean updateUser(User user) {

        boolean ret = true;
        String sql = "update user set name = ?, email = ?, phoneNumber = ?, "
                + "password1 = ?, password2 = ?, address = ?, userType = ?, activated = ? "
                + " where id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOGGER.debug("userId = " + user.getId());
        try {
            jdbcTemplate.update(
                    sql,
                    new Object[] {
                    		user.getName(),
                    		user.getEmail(),
                    		user.getPhoneNumber(),
                    		user.getPassword1(),
                    		user.getPassword2(),
                    		user.getAddress(),
                    		user.getUserType(),
                    		user.getActivated(),
                    		user.getId()
                    		});
        } catch (Exception ex) {
            ret = false;
            LOGGER.error("updateUser got error: " + ex.getMessage());
        }
        return ret;
    }

    @Override
    public boolean checkForgotPasswordToken(String token) {

        boolean found = false;
        try {
            String sql = "SELECT 1 FROM user WHERE resetPwdToken = ? AND resetPwdTokenExpired > CURRENT_TIMESTAMP";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<Integer> result = jdbcTemplate.query(sql, new Object[] { token }, new RowMapper<Integer>() {

                @Override
                public Integer mapRow(ResultSet rs, int arg) throws SQLException {

                    return rs.getInt(1);
                }
            });
            if (result.size() == 1 && result.get(0).equals(1)) {
                found = true;
            }
        } catch (Exception ex) {
            LOGGER.error("Error when checkForgotPasswordToken: ", ex);
        }
        return found;
    }

    @Override
    public boolean updateForgotPasswordToken(User user, String token) {
        boolean success = false;
        try {
            String sql = "UPDATE user SET resetPwdToken = ?, resetPwdTokenExpired = CURRENT_TIMESTAMP + INTERVAL 30 MINUTE WHERE id = ? AND email = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            int updated = jdbcTemplate.update(sql, new Object[]{token, user.getId(), user.getEmail()});
            if (updated == 1) {
                success = true;
            }
        } catch (Exception ex) {
            LOGGER.error("Error when updateForgotPasswordToken: ", ex);
        }
        return success;
    }

    @Override
    public boolean updateNewPassword(String token, String newPassword) {

        boolean sucess = false;
        try {
            String sql = "UPDATE user SET password = ?, resetPwdToken = NULL, resetPwdTokenExpired = NULL WHERE resetPwdToken = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            int result = jdbcTemplate.update(sql, new Object[] { encodeMD5(newPassword), token });
            if (result == 1) {
                sucess = true;
            }
        } catch (Exception ex) {
            LOGGER.error("Error when updateNewPassword: ", ex);
        }
        return sucess;
    }

}
