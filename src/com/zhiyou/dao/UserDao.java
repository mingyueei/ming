package com.zhiyou.dao;


import java.util.List;

import com.zhiyou.entity.Role;
import com.zhiyou.entity.User;

public interface UserDao {


    //	登录(查找)
    public User selectByAccount(String account);

    //	查询展示用户信息
    public List<User> selectUser(int startIndex, int pageSize, User user);

    //	根据部门查询该部门下的用户
    public List<User> selectUserByDept(String dept);

    //	新增
    public void saveUser(User user);

    //	删除
    public void deleteUser(String id);

    //	去到编辑页面（根据id查询）
    public User selectById(String id);

    //	编辑
    public void updateUser(User user);

    //	批量删除
    public void deleteRoleByIds(String[] ids);
}
