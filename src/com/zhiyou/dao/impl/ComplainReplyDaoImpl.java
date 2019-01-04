package com.zhiyou.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.ComplainReplyDao;
import com.zhiyou.entity.ComplainReply;

@Component
public class ComplainReplyDaoImpl implements ComplainReplyDao {

    @Autowired
    SessionFactory sf;

    //	添加回复信息
    @Override
    public void saveReply(ComplainReply complainReply) {
        Session currentSession = sf.getCurrentSession();
        currentSession.save(complainReply);
    }

}
