package com.mouse.ips.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mouse.ips.dao.UserDao;
import com.mouse.ips.pojo.User;
import com.mouse.ips.pojo.query.UserQuery;
import com.mouse.ips.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    @Override
    public PageInfo<User> listUserByName(UserQuery userQuery) {
        // 设置分页
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());

        return new PageInfo<User>(userDao.listUserByName(userQuery));
    }
}
