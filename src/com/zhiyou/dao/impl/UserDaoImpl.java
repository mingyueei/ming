package com.zhiyou.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.UserDao;
import com.zhiyou.entity.User;

@Component
public class UserDaoImpl implements UserDao {
    //注入sessionFactory对象
    @Autowired
    SessionFactory sf;

    //	登录（查询）
    @Override
    public User selectByAccount(String account) {
        Session currentSession = sf.getCurrentSession();
        String hql = "from User where account=?";
        User user = (User) currentSession.createQuery(hql).setParameter(0, account).uniqueResult();
        return user;
    }

    //	查询展示用户信息
    @Override
    public List<User> selectUser(int startIndex, int pageSize, User user) {
        List<User> list = new ArrayList<>();
        Session currentSession = sf.getCurrentSession();
        String hql = "from User where 1=1";
        if (user != null) {
            hql = hql + " and name like '%" + user.getName() + "%'";
        }
        Query createQuery = currentSession.createQuery(hql);
        if (!(startIndex == 0 && pageSize == 0)) {
            createQuery.setFirstResult(startIndex).setMaxResults(pageSize);
        }
        list = createQuery.list();
        return list;

    }

    //	新增
    @Override
    public void saveUser(User user) {
        Session currentSession = sf.getCurrentSession();
        currentSession.save(user);

    }

    //	删除
    @Override
    public void deleteUser(String id) {

        Session currentSession = sf.getCurrentSession();
        User user = (User) currentSession.get(User.class, id);
        currentSession.delete(user);

    }

    //	去到编辑页面（根据id查询）
    @Override
    public User selectById(String id) {
        Session currentSession = sf.getCurrentSession();
        String hql = "from User where id=?";
        User user = (User) currentSession.createQuery(hql).setParameter(0, id).uniqueResult();
        return user;
    }

    //	编辑
    @Override
    public void updateUser(User user) {
        Session currentSession = sf.getCurrentSession();
        currentSession.save(user);

    }

    //	批量删除
    @Override
    public void deleteRoleByIds(String[] ids) {
        Session currentSession = sf.getCurrentSession();
        for (int i = 0; i < ids.length; i++) {
            User user = this.selectById(ids[i]);
            currentSession.delete(user);
        }

    }

    //	根据部门查询该部门下的用户
    @Override
    public List<User> selectUserByDept(String dept) {
        Session currentSession = sf.getCurrentSession();
        String hql = "from User where dept=?";
        List<User> list = (List<User>) currentSession.createQuery(hql).setParameter(0, dept).list();

        return list;
    }


}
