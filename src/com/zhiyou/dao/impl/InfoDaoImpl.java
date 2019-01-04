package com.zhiyou.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.InfoDao;
import com.zhiyou.entity.Info;

@Component
public class InfoDaoImpl implements InfoDao {

    @Autowired
    SessionFactory sf;

    //	查询info
    @Override
    public List<Info> selectInfo() {
        Session currentSession = sf.getCurrentSession();
        String hql = "from Info";
        List<Info> info = (List<Info>) currentSession.createQuery(hql).list();
        return info;
    }

    //	根据title分页查询info
    @Override
    public List<Info> selectInfoByTitle(int startIndex, int pageSize, Info info) {
        List<Info> list = new ArrayList<>();
        Session currentSession = sf.getCurrentSession();
        String hql = "from Info where title like ?";
        if (info != null) {
            if (startIndex == 0 && pageSize == 0) {
                list = (List<Info>) currentSession.createQuery(hql).setParameter(0, "%" + info.getTitle() + "%").list();
            } else {
                list = (List<Info>) currentSession.createQuery(hql).setParameter(0, "%" + info.getTitle() + "%").setFirstResult(startIndex).setMaxResults(pageSize).list();
            }

        } else if (startIndex == 0 && pageSize == 0) {
            list = (List<Info>) currentSession.createQuery(hql).setParameter(0, "%%").list();

        } else {
            list = (List<Info>) currentSession.createQuery(hql).setParameter(0, "%%").setFirstResult(startIndex).setMaxResults(pageSize).list();

        }
        return list;
    }

    //	添加
    @Override
    public void saveInfo(Info info) {
        Session currentSession = sf.getCurrentSession();
        currentSession.save(info);

    }

    //	更新信息状态
    @Override
    public void updateState(String info_id, String state) {
        Session currentSession = sf.getCurrentSession();
        String hql = "update Info set state=? where infoId=?";
        currentSession.createQuery(hql).setParameter(0, state).setParameter(1, info_id).executeUpdate();

    }

    //	去编辑页面
    @Override
    public Info selectInfoById(String info_id) {
        Session currentSession = sf.getCurrentSession();
        String hql = "from Info where infoId=?";
        Info info = (Info) currentSession.createQuery(hql).setParameter(0, info_id).uniqueResult();
        return info;
    }

    //	编辑
    @Override
    public void updateInfo(Info info) {
        Session currentSession = sf.getCurrentSession();
        String hql = "update Info set type=?,source=?,title=?,content=?,memo=?,createTime=? where infoId=?";
        currentSession.createQuery(hql).setParameter(0, info.getType()).setParameter(1, info.getSource()).setParameter(2, info.getTitle()).setParameter(3, info.getContent()).setParameter(4, info.getMemo()).setParameter(5, info.getCreateTime()).setParameter(6, info.getInfoId()).executeUpdate();
    }

    //	删除
    @Override
    public void deleteInfo(Info info) {
        Session currentSession = sf.getCurrentSession();
        currentSession.delete(info);

    }

    //	批量删除
    @Override
    public void deleteInfoByIds(String[] ids) {
        Session currentSession = sf.getCurrentSession();
        for (int i = 0; i < ids.length; i++) {
            Info info = this.selectInfoById(ids[i]);
            currentSession.delete(info);
        }

    }


}
