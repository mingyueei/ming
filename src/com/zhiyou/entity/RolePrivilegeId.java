package com.zhiyou.entity;

/**
 * RolePrivilegeId entity. @author MyEclipse Persistence Tools
 */
public class RolePrivilegeId extends AbstractRolePrivilegeId implements
        java.io.Serializable {

    // Constructors

    /**
     * default constructor
     */
    public RolePrivilegeId() {
    }

    /**
     * full constructor
     */
    public RolePrivilegeId(Role role, String code) {
        super(role, code);
    }

}
