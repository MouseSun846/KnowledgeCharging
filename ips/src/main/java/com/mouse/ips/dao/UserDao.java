package com.mouse.ips.dao;

import com.mouse.ips.pojo.User;
import com.mouse.ips.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 告诉springboot这是mybatis的一个类
@Repository // UserDao由Spring boot的容器管理
public interface UserDao {
    // 查询所有用户
     public List<User> listUser();
     // 根据用户名分页展示
     public List<User> listUserByName(UserQuery userQuery);

}
