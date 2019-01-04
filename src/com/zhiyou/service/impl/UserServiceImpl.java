package com.zhiyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.UserDao;
import com.zhiyou.entity.User;
import com.zhiyou.service.UserService;

@Component
public class UserServiceImpl implements UserService {
    //注入dao层对象
    @Autowired
    UserDao ud;

    //登录（查找）
    @Override
    public User selectByAccount(String account) {
        User user = ud.selectByAccount(account);
        return user;
    }

    //	查询展示用户信息
    @Override
    public List<User> selectUser(int startIndex, int pageSize, User user) {
        List<User> list = ud.selectUser(startIndex, pageSize, user);
        return list;
    }

    //	新增
    @Override
    public void saveUser(User user) {
        ud.saveUser(user);

    }

    //	删除
    @Override
    public void deleteUser(String id) {
        ud.deleteUser(id);

    }

    //	去到编辑页面（根据id查询）
    @Override
    public User selectById(String id) {
        User user = ud.selectById(id);
        return user;
    }

    //	编辑
    @Override
    public void updateUser(User user) {
        ud.deleteUser(user.getId());
        ud.updateUser(user);

    }

    //	批量删除
    @Override
    public void deleteRoleByIds(String[] ids) {
        ud.deleteRoleByIds(ids);

    }

    //	根据部门查询该部门下的用户
    @Override
    public List<User> selectUserByDept(String dept) {
        List<User> list = ud.selectUserByDept(dept);
        return list;
    }


}
