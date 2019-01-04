package com.zhiyou.entity;

import java.sql.Timestamp;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

    // Constructors

    /**
     * default constructor
     */
    public User() {
    }

    /**
     * minimal constructor
     */
    public User(String name, String dept, String account, String password) {
        super(name, dept, account, password);
    }

    /**
     * full constructor
     */
    public User(String name, String dept, String account, String password,
                String headImg, Integer gender, String email, String mobile,
                Timestamp birthday, Integer state, String memo, Timestamp date,
                Set userRoles) {
        super(name, dept, account, password, headImg, gender, email, mobile,
                birthday, state, memo, date, userRoles);
    }

}
