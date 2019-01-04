package com.zhiyou.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

import com.zhiyou.entity.PageResult;
import com.zhiyou.entity.Role;
import com.zhiyou.entity.RolePrivilege;
import com.zhiyou.entity.RolePrivilegeId;
import com.zhiyou.service.RoleService;

public class RoleController {

    public static String PRIVILEGE_XZGL = "xzgl";
    public static String PRIVILEGE_HQFW = "hqfw";
    public static String PRIVILEGE_ZXXX = "zxxx";
    public static String PRIVILEGE_NSFW = "nsfw";
    public static String PRIVILEGE_SPACE = "space";

    public static HashMap<String, String> PRIVILEGE_MAP;

    static {
        PRIVILEGE_MAP = new HashMap<>();
        PRIVILEGE_MAP.put(PRIVILEGE_XZGL, "行政管理");
        PRIVILEGE_MAP.put(PRIVILEGE_HQFW, "后勤服务");
        PRIVILEGE_MAP.put(PRIVILEGE_ZXXX, "在线学习");
        PRIVILEGE_MAP.put(PRIVILEGE_NSFW, "纳税服务");
        PRIVILEGE_MAP.put(PRIVILEGE_SPACE, "我的空间");
    }

    private String page;
    private List<Role> roleList;
    private Map map = new HashMap();
    private PageResult<Role> pageResult = new PageResult<Role>();
    private Role role;
    private String[] privilegeIds;
    private String[] selectedRow;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }


    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public PageResult<Role> getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult<Role> pageResult) {
        this.pageResult = pageResult;
    }

    public String[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }

    public String[] getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }

    @Autowired
    RoleService rs;
    Logger log = Logger.getLogger(this.getClass());

    //	分页查询展示
    public String listUI() {
        int pageSize = 3;
        pageResult.setPageNo(1);
        if (page != null) {
            pageResult.setPageNo(Integer.parseInt(page));
        }
        int startIndex = (pageResult.getPageNo() - 1) * pageSize;
        roleList = rs.selectRole(startIndex, pageSize, role);
        pageResult.setItems(roleList);
        pageResult.setTotalCount(rs.selectRole(0, 0, role).size());
        if (pageResult.getTotalCount() != 0) {
            pageResult.setTotalPageCount((int) Math.ceil(pageResult.getTotalCount() * 1.0 / pageSize));
        }
        for (Role rr : roleList) {
            ActionContext.getContext().put("rolePrivileges", rr.getRolePrivileges());
        }
        ActionContext.getContext().put("privilegeMap", PRIVILEGE_MAP);

        return "success";
    }

    //	去添加页面
    public String addUI() {
        ActionContext.getContext().put("privilegeMap", PRIVILEGE_MAP);
        return "success";
    }

    //	添加
    public String add() {
        log.info("要添加角色啦！！");
        HashSet<RolePrivilege> rolePrivilege = new HashSet<>();
        if (privilegeIds != null) {

            for (int i = 0; i < privilegeIds.length; i++) {
                System.out.println("所选权限：" + privilegeIds[i]);
                rolePrivilege.add(new RolePrivilege((new RolePrivilegeId(role, privilegeIds[i]))));

            }
        }
        role.setRolePrivileges(rolePrivilege);
        rs.saveRole(role);
        return "success2";
    }

    //	去编辑页面
    public String editUI() {
        role = rs.selectRoleById(role.getRoleId());
        ActionContext.getContext().put("privilegeMap", PRIVILEGE_MAP);
        if (role.getRolePrivileges() != null) {

            privilegeIds = new String[role.getRolePrivileges().size()];
            int i = 0;
            Set<RolePrivilege> rolePrivileges = role.getRolePrivileges();
            for (RolePrivilege rp : rolePrivileges) {
                privilegeIds[i++] = rp.getId().getCode();
            }
        }

        return "success";
    }

    //	编辑
    public String edit() {
        rs.deleteRole(role);
        HashSet<RolePrivilege> rolePrivilege = new HashSet<>();
        if (privilegeIds != null) {

            for (int i = 0; i < privilegeIds.length; i++) {
                System.out.println("所选权限：" + privilegeIds[i]);
                rolePrivilege.add(new RolePrivilege((new RolePrivilegeId(role, privilegeIds[i]))));
            }
        }
        role.setRolePrivileges(rolePrivilege);
        rs.saveRole(role);
        return "success2";

    }

    //	删除
    public String delete() {
        rs.deleteRole(role);

        return "success2";
    }

    //	批量删除
    public String deleteSelected() {
        System.out.println("要批量删除了");
        rs.deleteRoleByIds(selectedRow);
        return "success2";
    }
}
