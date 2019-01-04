package com.zhiyou.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiyou.service.ComplainService;

public class MyJob extends ActionSupport {
    @Autowired
    ComplainService complainService;

    //	任务调度
//	定义个任务(写一个方法)
    public void job1() {
        System.out.println("简单任务");
    }

    public void job2() {
        System.out.println("复杂任务");
    }

    public void job3() {
        System.out.println("任务调度了");

        complainService.updateByTime();
        System.out.println("投诉过期");
    }
}
