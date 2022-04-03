package com.mouse.ips.controller;

import com.mouse.ips.pojo.User;
import com.mouse.ips.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;
    @GetMapping("/list")
    public List<User> listUser()
    {
        return userServices.listUser();
    }
}
