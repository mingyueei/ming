package com.zhiyou.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhiyou.dao.ComplainDao;
import com.zhiyou.entity.Complain;

@Component
public class ComplainDaoImpl implements ComplainDao {

    @Autowired
    SessionFactory sf;

    //	根据name查询投诉信息
    @Override
    public List<Complain> selectComByName(String name) {
        Session currentSession = sf.getCurrentSession();
        String hql = "from Complain where compName=?";
        List<Complain> complain = (List<Complain>) currentSession.createQuery(hql).setParameter(0, name).list();
        return complain;
    }

    //	分页查询
    @Override
    public List<Complain> selectComplain(int startIndex, int pageSize, Complain complain, Timestamp startTime, Timestamp endTime) {
        List<Complain> list = new ArrayList<>();
        Session currentSession = sf.getCurrentSession();
        String hql = "from Complain where 1=1";
//				+ "compTitle like ? and compTime between ? and ? and state=?";

        if (complain != null) {
            if (complain.getCompTitle() != null && !complain.getCompTitle().equals("")) {
                hql = hql + " and compTitle like '%" + complain.getCompTitle() + "%'";
            }

            if (startTime != null) {
                if (endTime != null) {
                    hql = hql + " and compTime between '" + startTime + "' and '" + endTime + "'";
                } else {
                    hql = hql + " and compTime > '" + startTime + "'";
                }

            } else if (endTime != null) {
                hql = hql + " and compTime < '" + endTime + "'";
            }
            if (complain.getState() != null && !complain.getState().equals("")) {
                hql = hql + " and state= '" + complain.getState() + "'";
            }
        }
        Query createQuery = currentSession.createQuery(hql);
        if (!(startIndex == 0 && pageSize == 0)) {
            createQuery.setFirstResult(startIndex).setMaxResults(pageSize);
        }
        list = (List<Complain>) createQuery.list();
        return list;
    }

    //	根据id查询
    @Override
    public Complain selectById(String compId) {
        Session currentSession = sf.getCurrentSession();
        String hql = "from Complain where compId=?";
        Complain complain = (Complain) currentSession.createQuery(hql).setParameter(0, compId).uniqueResult();
        return complain;
    }

    //	修改状态
    @Override
    public void updateState(Complain complain) {
        Session currentSession = sf.getCurrentSession();
        String hql = "update Complain set state=? where compId=?";
        currentSession.createQuery(hql).setParameter(0, "1").setParameter(1, complain.getCompId()).executeUpdate();

    }

    //  根据年份获取投诉数据
    @Override
    public List<Object[]> getStatisticData(int year) {
        Session currentSession = sf.getCurrentSession();
        String sql = "select imonth,count(comp_id) from tmonth left join complain on month(comp_time)=imonth and year(comp_time)=? group by imonth order by imonth";
        List list = currentSession.createSQLQuery(sql).setParameter(0, year).list();
        return list;
    }

    //	设置失效
    @Override
    public void updateByTime(Timestamp endTime) {
        Session currentSession = sf.getCurrentSession();
        String hql = "update Complain set state=? where 1=1";
        hql = hql + " and compTime < '" + endTime + "'";
        currentSession.createQuery(hql).setParameter(0, "1").executeUpdate();

    }

    //	投诉add
    @Override
    public void saveComplain(Complain complain) {
        Session currentSession = sf.getCurrentSession();
        currentSession.save(complain);
    }

}
