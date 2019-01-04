package com.zhiyou.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.ComplainDao;
import com.zhiyou.entity.Complain;
import com.zhiyou.service.ComplainService;

@Component
public class ComplainServiceImpl implements ComplainService {

    @Autowired
    ComplainDao cd;

    //	根据name查询投诉信息
    @Override
    public List<Complain> selectComByName(String name) {
        List<Complain> complain = cd.selectComByName(name);
        return complain;
    }

    //	分页查询
    @Override
    public List<Complain> selectComplain(int startIndex, int pageSize, Complain complain, Timestamp startTime,
                                         Timestamp endTime) {
        List<Complain> list = cd.selectComplain(startIndex, pageSize, complain, startTime, endTime);
        return list;
    }

    //	根据id查询
    @Override
    public Complain selectById(String compId) {
        Complain selectById = cd.selectById(compId);
        return selectById;
    }

    //	修改状态
    @Override
    public void updateState(Complain complain) {
        cd.updateState(complain);

    }

    //  根据年份获取投诉数据
    @Override
    public List<Object[]> getStatisticData(int year) {
        List<Object[]> list = cd.getStatisticData(year);
        List arrayList = new ArrayList();
        if (list != null) {//则一定会有12条数据

            //调用getInstance()类方法获取一个日历对象
            Calendar instance = Calendar.getInstance();
            //判断是否为当前年度
            boolean isCurrentYear = (year == instance.get(Calendar.YEAR));
            int curMonth = instance.get(Calendar.MONTH) + 1;//获取当前月份
            int month = 0;
            for (Object[] objects : list) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                month = Integer.valueOf(objects[0] + "");
                map.put("label", month + "月");
                if (isCurrentYear) {//判断是否为当前年度
                    if (month > curMonth) {
                        //如果是当前年度，在当前月份之前的月份，将其投诉置空
                        map.put("value", "");
                    } else {
                        if (objects[1] != null) {
                            map.put("value", String.valueOf(objects[1]));

                        } else {
                            map.put("value", "0");
                        }
                    }
                } else {//非当前年度
                    //如果在当月之前，所有为空的统计数默认为0，表示相应月的投诉数为0
                    if (objects[1] != null) {
                        map.put("value", String.valueOf(objects[1]));

                    } else {
                        map.put("value", "0");
                    }

                }
                arrayList.add(map);
            }
            return arrayList;
        }
        return null;
    }

    //	设置失效
    @Override
    public void updateByTime() {
        //调用getInstance()类方法获取一个日历对象
        Calendar instance = Calendar.getInstance();
        int curYear = instance.get(Calendar.YEAR);//获取当前年份
        int curMonth = instance.get(Calendar.MONTH) + 1;//获取当前月份
        System.out.println("当前月份:" + curMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = null;
        try {
            parse = simpleDateFormat.parse(curYear + "-" + curMonth + "-" + "01 00:00:00");
        } catch (ParseException e) {

            e.printStackTrace();
        }

        Timestamp endTime = new Timestamp(parse.getTime());

        cd.updateByTime(endTime);

    }

    //	投诉add
    @Override
    public void saveComplain(Complain complain) {
        cd.saveComplain(complain);

    }

}
