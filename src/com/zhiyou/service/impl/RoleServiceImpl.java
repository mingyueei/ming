package com.zhiyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.RoleDao;
import com.zhiyou.entity.Role;
import com.zhiyou.entity.RolePrivilege;
import com.zhiyou.entity.RolePrivilegeId;
import com.zhiyou.service.RoleService;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao rd;

    //	关联权限表查询角色
    @Override
    public List<Role> selectRole(int startIndex, int pageSize, Role role) {
        List<Role> list = rd.selectRole(startIndex, pageSize, role);
        return list;
    }

    //	添加
    @Override
    public void saveRole(Role role) {
        rd.saveRole(role);
    }

    //	去编辑页面（根据id查询）
    @Override
    public Role selectRoleById(String role_id) {
        Role selectRoleById = rd.selectRoleById(role_id);
        return selectRoleById;
    }

    //	编辑
    @Override
    public void updateRole(Role role) {
        rd.deleteRole(role);
        rd.updateRole(role);

    }

    //删除
    @Override
    public void deleteRole(Role role) {
        rd.deleteRole(role);

    }

    //批量删除
    @Override
    public void deleteRoleByIds(String[] ids) {
        rd.deleteRoleByIds(ids);

    }


}
