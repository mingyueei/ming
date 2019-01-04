package com.zhiyou.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhiyou.entity.Complain;
import com.zhiyou.entity.ComplainReply;
import com.zhiyou.entity.PageResult;
import com.zhiyou.entity.User;
import com.zhiyou.service.ComplainReplyService;
import com.zhiyou.service.ComplainService;
import com.zhiyou.service.UserService;

public class ComplainController extends ActionSupport {

    public static String complainState_DSL = "0";
    public static String complainState_YSL = "1";
    public static HashMap<String, String> complainStateMAP;

    static {
        complainStateMAP = new HashMap<>();
        complainStateMAP.put(complainState_DSL, "待受理");
        complainStateMAP.put(complainState_YSL, "已受理");
    }

    private Map map = new HashMap();
    private List<Complain> complainList;
    private Complain complain;
    private Complain comp;
    private Timestamp startTime;
    private Timestamp endTime;
    private PageResult<Complain> pageResult = new PageResult<>();
    private String page;
    private ComplainReply reply;
    private List<Map<String, String>> list = new ArrayList<>();
    private HashMap<String, Object> statisticMap;
    private List<User> userList;
    private String msg = "success";

    //这个msg是我在根据dept查询用户信息（名字）的时候用到的，前端页面利用ajax有判断data.msg=="success";,
    //我直接在后台方法里面定义msg时，前端得不到这个data.msg
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Complain> getComplainList() {
        return complainList;
    }

    public void setComplainList(List<Complain> complainList) {
        this.complainList = complainList;
    }

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public PageResult<Complain> getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult<Complain> pageResult) {
        this.pageResult = pageResult;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ComplainReply getReply() {
        return reply;
    }

    public void setReply(ComplainReply reply) {
        this.reply = reply;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }

    public HashMap<String, Object> getStatisticMap() {
        return statisticMap;
    }

    public void setStatisticMap(HashMap<String, Object> statisticMap) {
        this.statisticMap = statisticMap;
    }

    public Complain getComp() {
        return comp;
    }

    public void setComp(Complain comp) {
        this.comp = comp;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Autowired
    ComplainService cs;
    @Autowired
    ComplainReplyService crs;
    @Autowired
    UserService userService;

    //	查看投诉内容
    public String complainViewUI() {
        comp = cs.selectById(complain.getCompId());
        ActionContext.getContext().put("complainStateMap", complainStateMAP);

        return SUCCESS;
    }

    //	去到complainAddUI页面
    public String complainAddUI() {
        return SUCCESS;
    }

    //	根据部门查询用户
    public String getUserJson2() {
        String dept = null;
        HttpServletRequest request = ServletActionContext.getRequest();
        if (request.getParameter("dept") != null) {
            dept = request.getParameter("dept");
            System.out.println("dept:" + dept);
        }
        userList = userService.selectUserByDept(dept);
        for (User user : userList) {
            System.out.println("用户名：" + user.getName());
        }

        if (userList == null) {
            msg = "error";
        }

        return SUCCESS;
    }

    //	投诉（添加投诉信息）
    public String complainAdd() {
        msg = "success";
        Date date = new Date();
        Timestamp compTime = new Timestamp(date.getTime());
        comp.setCompTime(compTime);
        comp.setState("0");
        cs.saveComplain(comp);
        return SUCCESS;
    }

    //	分页查询
    public String listUI() {
        int pageSize = 3;
        pageResult.setPageNo(1);
        if (page != null) {
            pageResult.setPageNo(Integer.parseInt(page));
        }
        int startIndex = (pageResult.getPageNo() - 1) * pageSize;

        complainList = cs.selectComplain(startIndex, pageSize, complain, startTime, endTime);
        pageResult.setItems(complainList);
        pageResult.setTotalCount(cs.selectComplain(0, 0, complain, startTime, endTime).size());
        if (pageResult.getTotalCount() != 0) {
            pageResult.setTotalPageCount((int) Math.ceil(pageResult.getTotalCount() * 1.0 / pageSize));
        }
        ActionContext.getContext().put("complainStateMap", complainStateMAP);
        return "success";
    }

    //	去到受理页面
    public String dealUI() {
        complain = cs.selectById(complain.getCompId());
        ActionContext.getContext().put("complainStateMap", complainStateMAP);
        return "success";
    }

    //	进行受理，受理完成，回到主页面
    public String deal() {
        System.out.println("要进行受理了");
        reply.setComplain(complain);
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        reply.setReplyTime(nousedate);
        crs.saveReply(reply);
        //受理完成后修改被受理的投诉状态为1
        cs.updateState(complain);
        return "success";
    }

    //	去到年度统计页面
    public String annualStatisticChartUI() {

        return "success";
    }

    //	数据统计
    public String getAnnualStatisticData() {
        //1、获取年份
        int year = 0;
        HttpServletRequest request = ServletActionContext.getRequest();
        if (request.getParameter("year") != null) {
            year = Integer.valueOf(request.getParameter("year"));
        } else {
            //默认统计当前年度
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        //2、根据年份获取投诉数据
        statisticMap = new HashMap<String, Object>();
        statisticMap.put("msg", "success");
        statisticMap.put("chartData", cs.getStatisticData(year));

        return SUCCESS;
    }
}
