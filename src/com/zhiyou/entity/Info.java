package com.zhiyou.entity;

import java.sql.Timestamp;

/**
 * Info entity. @author MyEclipse Persistence Tools
 */
public class Info extends AbstractInfo implements java.io.Serializable {

    // Constructors

    /**
     * default constructor
     */
    public Info() {
    }

    /**
     * minimal constructor
     */
    public Info(String title) {
        super(title);
    }

    /**
     * full constructor
     */
    public Info(String type, String source, String title, String content,
                String memo, String creator, Timestamp createTime, String state) {
        super(type, source, title, content, memo, creator, createTime, state);
    }

}
