package com.small.business.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.small.business.model.user.ForgotPasswordRequest;
import com.small.business.model.user.ResetPasswordRequest;
import com.small.business.model.user.User;
import com.small.business.model.user.UserPosition;
import com.small.business.service.user.UserService;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("cachedTokenService")

    @Value("#{'${adminPage.user.accessable}'.split(',')}")
    private Set<String> adminPageUserAccessable;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable long id) {

        return userService.getUserById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody List<User> getUserList() {

        return userService.getAllUser();
    }

    @RequestMapping(value = "/userPosition", method = RequestMethod.GET)
    public @ResponseBody List<UserPosition> getUserPositionList() {

        return userService.getAllUserPosition();
    }
    
    @RequestMapping(value = "/userPosition/{userId}", method = RequestMethod.GET)
    public @ResponseBody UserPosition getUserPosition(@PathVariable("userId") Long userId) {

        return userService.getUserPosition(userId);
    }    
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public @ResponseBody long addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public @ResponseBody boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public @ResponseBody boolean removeUser(@PathVariable("id") Long id) {

        return userService.deleteUserById(id);
    }

    @RequestMapping(value = "/user/forgot-password", method = RequestMethod.POST)
    public @ResponseBody boolean forgotPasswordRequest(@RequestBody ForgotPasswordRequest forgotPasswordRequest) throws Exception {

        return userService.forgotPassword(forgotPasswordRequest);
    }

    @RequestMapping(value = "/user/reset-password", method = RequestMethod.POST)
    public @ResponseBody boolean resetPasswordRequest(@RequestBody ResetPasswordRequest resetPasswordRequest) throws Exception {

        return userService.resetPassword(resetPasswordRequest);
    }

    @RequestMapping(value = "/removeAllUser", method = RequestMethod.DELETE)
    public @ResponseBody boolean removeAllUser() {

        return userService.deleteAll();
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public @ResponseBody String fileUpload(
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request) {

        return userService.uploadFilePostMethod(file, request);

    }
}
