package com.mouse.ips.services.impl;

import com.mouse.ips.pojo.User;
import com.mouse.ips.services.UserServices;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServicesImplTest {
    @Autowired
    private UserServices userServices;

    @Test
    void listUser() {
        List<User> users = userServices.listUser();
        System.out.println(users);
    }

    @Test
    void listUserByName() {
    }
}