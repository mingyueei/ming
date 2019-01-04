package com.zhiyou.service;

import com.zhiyou.dao.ComplainReplyDao;
import com.zhiyou.entity.ComplainReply;

public interface ComplainReplyService {
    //	添加回复信息
    public void saveReply(ComplainReply complainReply);
}
