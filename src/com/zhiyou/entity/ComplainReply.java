package com.zhiyou.entity;

import java.sql.Timestamp;

/**
 * ComplainReply entity. @author MyEclipse Persistence Tools
 */
public class ComplainReply extends AbstractComplainReply implements
        java.io.Serializable {

    // Constructors

    /**
     * default constructor
     */
    public ComplainReply() {
    }

    /**
     * minimal constructor
     */
    public ComplainReply(Complain complain) {
        super(complain);
    }

    /**
     * full constructor
     */
    public ComplainReply(Complain complain, String replyer, String replyDept,
                         Timestamp replyTime, String replyContent) {
        super(complain, replyer, replyDept, replyTime, replyContent);
    }

}
