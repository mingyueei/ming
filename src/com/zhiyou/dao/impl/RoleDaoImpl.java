package com.zhiyou.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.RoleDao;
import com.zhiyou.entity.Role;
import com.zhiyou.entity.RolePrivilege;
import com.zhiyou.entity.RolePrivilegeId;
import com.zhiyou.entity.User;

@Component
public class RoleDaoImpl implements RoleDao {

    @Autowired
    SessionFactory sf;

    //	关联权限表查询角色
    @Override
    public List<Role> selectRole(int startIndex, int pageSize, Role role) {
        List<Role> list = new ArrayList<>();
        Session currentSession = sf.getCurrentSession();
        String hql = "from Role where 1=1";
        if (role != null) {
            hql = hql + " and name like '%" + role.getName() + "%'";
        }
        Query createQuery = currentSession.createQuery(hql);
        if (!(startIndex == 0 && pageSize == 0)) {
            createQuery.setFirstResult(startIndex).setMaxResults(pageSize);
        }

        list = (List<Role>) createQuery.list();
        return list;
    }

    //	添加角色
    @Override
    public void saveRole(Role role) {
        Session currentSession = sf.getCurrentSession();
        currentSession.save(role);

    }

    //	去编辑页面
    @Override
    public Role selectRoleById(String role_id) {
        Session currentSession = sf.getCurrentSession();
        String hql = "from Role where roleId=?";
        Role uniqueResult = (Role) currentSession.createQuery(hql).setParameter(0, role_id).uniqueResult();
        return uniqueResult;
    }

    //	编辑
    @Override
    public void updateRole(Role role) {
        Session currentSession = sf.getCurrentSession();
        currentSession.save(role);

    }

    //	删除权限
    @Override
    public void deleteRolePrivilege(Role role) {
        Session currentSession = sf.getCurrentSession();
        String hql = "delete from RolePrivilege where rolrId=?";
        currentSession.createQuery(hql).setParameter(0, role.getRoleId()).executeUpdate();

    }

    //	删除
    @Override
    public void deleteRole(Role role) {
        Session currentSession = sf.getCurrentSession();
        currentSession.delete(role);

    }

    //	批量删除
    @Override
    public void deleteRoleByIds(String[] ids) {
        Session currentSession = sf.getCurrentSession();
        for (int i = 0; i < ids.length; i++) {
            Role role = this.selectRoleById(ids[i]);
            currentSession.delete(role);
        }
    }


}
