package com.zhiyou.service;

import java.sql.Timestamp;

import java.util.List;

import com.zhiyou.entity.Complain;

public interface ComplainService {
    //	根据name查询投诉信息
    public List<Complain> selectComByName(String name);

    //	分页查询
    public List<Complain> selectComplain(int startIndex, int pageSize, Complain complain, Timestamp startTime, Timestamp endTime);

    //	根据id查询
    public Complain selectById(String compId);

    //	修改状态
    public void updateState(Complain complain);

    //  根据年份获取投诉数据
    public List<Object[]> getStatisticData(int year);

    //	设置失效
    public void updateByTime();

    //	投诉add
    public void saveComplain(Complain complain);
}
