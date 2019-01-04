package com.zhiyou.entity;

/**
 * AbstractRolePrivilege entity provides the base persistence definition of the
 * RolePrivilege entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRolePrivilege implements java.io.Serializable {

    // Fields

    private RolePrivilegeId id;

    // Constructors

    /**
     * default constructor
     */
    public AbstractRolePrivilege() {
    }

    /**
     * full constructor
     */
    public AbstractRolePrivilege(RolePrivilegeId id) {
        this.id = id;
    }

    // Property accessors

    public RolePrivilegeId getId() {
        return this.id;
    }

    public void setId(RolePrivilegeId id) {
        this.id = id;
    }

}