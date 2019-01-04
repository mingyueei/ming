package com.zhiyou.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhiyou.entity.Info;
import com.zhiyou.entity.PageResult;
import com.zhiyou.service.InfoService;

public class InfoController extends ActionSupport {

    public static String infoType_TZ = "1";
    public static String infoType_ZC = "0";
    public static HashMap<String, String> infoTypeMAP;

    static {
        infoTypeMAP = new HashMap<>();
        infoTypeMAP.put(infoType_TZ, "通知公告");
        infoTypeMAP.put(infoType_ZC, "政策速递");
    }

    private Map map = new HashMap();
    private List<Info> infoList;
    private PageResult<Info> pageResult = new PageResult<Info>();
    private String page;
    private Info info;
    private String[] selectedRow;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    public PageResult<Info> getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult<Info> pageResult) {
        this.pageResult = pageResult;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String[] getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }


    @Autowired
    InfoService infoService;

    //	去到infoViewUI页面(查看信息)
    public String infoViewUI() {
        info = infoService.selectInfoById(info.getInfoId());
        ActionContext.getContext().put("infoTypeMap", infoTypeMAP);
        return SUCCESS;
    }

    //	分页查询展示
    public String listUI() {
        System.out.println("要分页查询了");
        int pageSize = 3;
        pageResult.setPageNo(1);
        if (page != null) {
            pageResult.setPageNo(Integer.parseInt(page));
        }
        int startIndex = (pageResult.getPageNo() - 1) * pageSize;

        infoList = infoService.selectInfoByTitle(startIndex, pageSize, info);
        pageResult.setItems(infoList);
        pageResult.setTotalCount(infoService.selectInfoByTitle(0, 0, info).size());
        if (pageResult.getTotalCount() != 0) {
            pageResult.setTotalPageCount((int) Math.ceil(pageResult.getTotalCount() * 1.0 / pageSize));
        }
        ActionContext.getContext().put("infoTypeMap", infoTypeMAP);
        return "success";
    }
//	根据title分页查询info

    //	去添加页面
    public String addUI() {
        ActionContext.getContext().put("infoTypeMap", infoTypeMAP);
        return "success";
    }

    //	添加
    public String add() {
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        info.setCreateTime(nousedate);
        infoService.saveInfo(info);
        return "success2";
    }

    //	更新状态
    public String doPublic() {
        System.out.println("更新状态");
        infoService.updateState(info.getInfoId(), info.getState());
        System.out.println("已更新");

        return "success";
    }

    //	去编辑页面
    public String editUI() {
        info = infoService.selectInfoById(info.getInfoId());
        ActionContext.getContext().put("infoTypeMap", infoTypeMAP);
        return "success";
    }

    //	编辑
    public String edit() {
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        info.setCreateTime(nousedate);
        infoService.updateInfo(info);

        return "success2";
    }

    //	删除
    public String delete() {
        infoService.deleteInfo(info);
        return "success2";
    }

    //	批量删除
    public String deleteSelected() {
        System.out.println("要批量删除了");
        infoService.deleteInfoByIds(selectedRow);
        return "success2";
    }

}
