package com.zhiyou.dao;

import java.util.List;

import com.zhiyou.entity.Info;

public interface InfoDao {

    //	查询info
    public List<Info> selectInfo();

    //	根据title分页查询info
    public List<Info> selectInfoByTitle(int startIndex, int pageSize, Info info);

    //	添加
    public void saveInfo(Info info);

    //	更新信息状态
    public void updateState(String info_id, String state);

    //	去编辑页面(根据id查询)
    public Info selectInfoById(String info_id);

    //	编辑
    public void updateInfo(Info info);

    //	删除
    public void deleteInfo(Info info);

    //	批量删除
    public void deleteInfoByIds(String[] ids);
}
