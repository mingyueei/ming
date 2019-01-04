package com.zhiyou.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractRole entity provides the base persistence definition of the Role
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRole implements java.io.Serializable {

    // Fields

    private String roleId;
    private String name;
    private String state;
    private Set userRoles = new HashSet(0);
    private Set rolePrivileges = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public AbstractRole() {
    }

    /**
     * minimal constructor
     */
    public AbstractRole(String name) {
        this.name = name;
    }

    /**
     * full constructor
     */
    public AbstractRole(String name, String state, Set userRoles,
                        Set rolePrivileges) {
        this.name = name;
        this.state = state;
        this.userRoles = userRoles;
        this.rolePrivileges = rolePrivileges;
    }

    // Property accessors

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set userRoles) {
        this.userRoles = userRoles;
    }

    public Set getRolePrivileges() {
        return this.rolePrivileges;
    }

    public void setRolePrivileges(Set rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }

}