package com.mouse.ips.services;

import com.github.pagehelper.PageInfo;
import com.mouse.ips.pojo.User;
import com.mouse.ips.pojo.query.UserQuery;

import java.util.List;

public interface UserServices {
    // 查询所有用户
    public List<User> listUser();
    // 根据用户名分页展示
    public PageInfo<User> listUserByName(UserQuery userQuery);
}
