package com.zhiyou.entity;

import java.sql.Timestamp;

/**
 * AbstractInfo entity provides the base persistence definition of the Info
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractInfo implements java.io.Serializable {

    // Fields

    private String infoId;
    private String type;
    private String source;
    private String title;
    private String content;
    private String memo;
    private String creator;
    private Timestamp createTime;
    private String state;

    // Constructors

    /**
     * default constructor
     */
    public AbstractInfo() {
    }

    /**
     * minimal constructor
     */
    public AbstractInfo(String title) {
        this.title = title;
    }

    /**
     * full constructor
     */
    public AbstractInfo(String type, String source, String title,
                        String content, String memo, String creator, Timestamp createTime,
                        String state) {
        this.type = type;
        this.source = source;
        this.title = title;
        this.content = content;
        this.memo = memo;
        this.creator = creator;
        this.createTime = createTime;
        this.state = state;
    }

    // Property accessors

    public String getInfoId() {
        return this.infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

}