package com.small.business.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class UserController {

    @RequestMapping(value = "/helloTest", method = RequestMethod.GET)
    public @ResponseBody String helloTest(@PathVariable long id) {

        return new String("Hello");
    }



}
