package com.zhiyou.entity;

/**
 * UserRoleId entity. @author MyEclipse Persistence Tools
 */
public class UserRoleId extends AbstractUserRoleId implements
        java.io.Serializable {

    // Constructors

    /**
     * default constructor
     */
    public UserRoleId() {
    }

    /**
     * full constructor
     */
    public UserRoleId(Role role, User user) {
        super(role, user);
    }

}
