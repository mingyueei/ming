package com.zhiyou.dao;

import java.util.List;

import com.zhiyou.entity.Role;
import com.zhiyou.entity.RolePrivilege;
import com.zhiyou.entity.RolePrivilegeId;

public interface RoleDao {

    //	关联权限表查询角色
    public List<Role> selectRole(int startIndex, int pageSize, Role role);

    //	添加
    public void saveRole(Role role);

    //	去编辑页面（根据id查询）
    public Role selectRoleById(String role_id);

    //	编辑
    public void updateRole(Role role);

    //	删除权限
    public void deleteRolePrivilege(Role role);

    //	删除
    public void deleteRole(Role role);

    //	批量删除
    public void deleteRoleByIds(String[] ids);
}
