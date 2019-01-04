package com.zhiyou.entity;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Complain entity. @author MyEclipse Persistence Tools
 */
public class Complain extends AbstractComplain implements java.io.Serializable {

    // Constructors

    /**
     * default constructor
     */
    public Complain() {
    }

    /**
     * minimal constructor
     */
    public Complain(String compTitle) {
        super(compTitle);
    }

    /**
     * full constructor
     */
    public Complain(String compCompany, String compName, String compMobile,
                    Boolean isNm, Timestamp compTime, String compTitle,
                    String toCompName, String toCompDept, String compContent,
                    String state, Set complainReplies) {
        super(compCompany, compName, compMobile, isNm, compTime, compTitle,
                toCompName, toCompDept, compContent, state, complainReplies);
    }

}
