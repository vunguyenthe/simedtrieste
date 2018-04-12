package com.small.business.service.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.small.business.dao.user.UserDao;
import com.small.business.model.user.User;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private static List<User> userList = new ArrayList<User>();

    @Autowired
    UserDao userDao;

    public List<User> getAllUser() {

        List<User> userList = userDao.getAllUser();
        return userList;
    }
    public User getUserById(Long id) {

        return userDao.getUserById(id);
    }
    public long addUser(User user) {

        return userDao.addUser(user);
    }

    public boolean deleteUserById(Long id) {

        boolean ret;
        ret = userDao.deleteUserById(id);
        return ret;
    }

    public boolean deleteAll() {

        userList.clear();
        return userDao.deleteAll();
    }

    public boolean updateUser(User user) {

        User foundUser = findUserById(user.getId());
        if (foundUser != null) {
            userList.remove(foundUser);
            userList.add(user);
        }
        return userDao.updateUser(user);

    }

    public User findUserById(Long id) {

        return userDao.getUserById(id);
    }

}
