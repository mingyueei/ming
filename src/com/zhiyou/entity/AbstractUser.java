package com.zhiyou.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUser implements java.io.Serializable {

    // Fields

    private String id;
    private String name;
    private String dept;
    private String account;
    private String password;
    private String headImg;
    private Integer gender;
    private String email;
    private String mobile;
    private Timestamp birthday;
    private Integer state;
    private String memo;
    private Timestamp date;
    private Set userRoles = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public AbstractUser() {
    }

    /**
     * minimal constructor
     */
    public AbstractUser(String name, String dept, String account,
                        String password) {
        this.name = name;
        this.dept = dept;
        this.account = account;
        this.password = password;
    }

    /**
     * full constructor
     */
    public AbstractUser(String name, String dept, String account,
                        String password, String headImg, Integer gender, String email,
                        String mobile, Timestamp birthday, Integer state, String memo,
                        Timestamp date, Set userRoles) {
        this.name = name;
        this.dept = dept;
        this.account = account;
        this.password = password;
        this.headImg = headImg;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.birthday = birthday;
        this.state = state;
        this.memo = memo;
        this.date = date;
        this.userRoles = userRoles;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImg() {
        return this.headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Timestamp getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Set getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "AbstractUser [id=" + id + ", name=" + name + ", dept=" + dept + ", account=" + account + ", password="
                + password + ", headImg=" + headImg + ", gender=" + gender + ", email=" + email + ", mobile=" + mobile
                + ", birthday=" + birthday + ", state=" + state + ", memo=" + memo + ", date=" + date + "]";
    }

}