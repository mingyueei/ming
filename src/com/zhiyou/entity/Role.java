package com.zhiyou.entity;

import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
public class Role extends AbstractRole implements java.io.Serializable {

    // Constructors

    /**
     * default constructor
     */
    public Role() {
    }

    /**
     * minimal constructor
     */
    public Role(String name) {
        super(name);
    }

    /**
     * full constructor
     */
    public Role(String name, String state, Set userRoles, Set rolePrivileges) {
        super(name, state, userRoles, rolePrivileges);
    }

}
