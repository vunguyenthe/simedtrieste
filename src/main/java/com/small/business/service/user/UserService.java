package com.small.business.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.small.business.exception.NotFoundException;
import com.small.business.model.user.User;

public interface UserService {

    public List<User> getAllUser();

    public User getUserById(Long id);

    public long addUser(User User);

    public boolean deleteUserById(Long id);

    public boolean deleteAll();

    public boolean updateUser(User user);

}
