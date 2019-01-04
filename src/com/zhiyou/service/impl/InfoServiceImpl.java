package com.zhiyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.InfoDao;
import com.zhiyou.entity.Info;
import com.zhiyou.service.InfoService;

@Component
public class InfoServiceImpl implements InfoService {

    @Autowired
    InfoDao ind;

    //	查询info
    @Override
    public List<Info> selectInfo() {
        List<Info> info = ind.selectInfo();
        return info;
    }

    //	根据title分页查询info
    @Override
    public List<Info> selectInfoByTitle(int startIndex, int pageSize, Info info) {
        List<Info> list = ind.selectInfoByTitle(startIndex, pageSize, info);
        return list;
    }

    //	添加
    @Override
    public void saveInfo(Info info) {
        ind.saveInfo(info);

    }

    //	更新信息状态
    @Override
    public void updateState(String info_id, String state) {
        ind.updateState(info_id, state);

    }

    //	去编辑页面(根据id查询)
    @Override
    public Info selectInfoById(String info_id) {
        Info info = ind.selectInfoById(info_id);
        return info;
    }

    //	编辑
    @Override
    public void updateInfo(Info info) {
        ind.updateInfo(info);

    }

    //	删除
    @Override
    public void deleteInfo(Info info) {
        ind.deleteInfo(info);

    }

    //	批量删除
    @Override
    public void deleteInfoByIds(String[] ids) {
        ind.deleteInfoByIds(ids);
    }


}
