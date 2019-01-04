package com.zhiyou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.ComplainReplyDao;
import com.zhiyou.entity.ComplainReply;
import com.zhiyou.service.ComplainReplyService;

@Component
public class ComplainReplyServiceImpl implements ComplainReplyService {

    @Autowired
    ComplainReplyDao crd;

    //	添加回复信息
    @Override
    public void saveReply(ComplainReply complainReply) {
        crd.saveReply(complainReply);

    }

}
