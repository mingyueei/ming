package com.zhiyou.entity;

/**
 * AbstractTmonth entity provides the base persistence definition of the Tmonth
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTmonth implements java.io.Serializable {

    // Fields

    private Integer id;
    private Integer imonth;

    // Constructors

    /**
     * default constructor
     */
    public AbstractTmonth() {
    }

    /**
     * full constructor
     */
    public AbstractTmonth(Integer imonth) {
        this.imonth = imonth;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImonth() {
        return this.imonth;
    }

    public void setImonth(Integer imonth) {
        this.imonth = imonth;
    }

}