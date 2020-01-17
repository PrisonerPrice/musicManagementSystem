package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.prisonerprice.util.HibernateUtil.*;

public class Connection<T>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean save(T obj){
        boolean isSuccess = true;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try{
            session.persist(obj);
            t.commit();
        } catch (Exception e){
            isSuccess = false;
            if(t != null) t.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The item %s was inserted into the table", obj.toString()));
        return isSuccess;
    }

    public boolean update(T obj) {
        boolean isSuccess = true;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try{
            session.saveOrUpdate(obj);
            t.commit();
        } catch (Exception e){
            isSuccess = false;
            if(t != null) t.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The item %s was updated", obj.toString()));
        return isSuccess;
    }

    public boolean delete(T obj){
        boolean isSuccess = true;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try{
            session.delete(obj);
            t.commit();
        } catch (Exception e){
            isSuccess = false;
            if(t != null) t.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The item %s was deleted", obj.toString()));
        return isSuccess;
    }

    public List<T> getObjectList(String TypeName) {
        String hql = "FROM " + TypeName;
        // try resources
        try (Session session = getSessionFactory().openSession()){
            Query query = session.createQuery(hql);
            return query.list();
        }
    }

    public List<T> getObjectListWithChildren(String hql){
        logger.info("INTO the method getObjectListWithChildren");
        try (Session session = getSessionFactory().openSession()) {
            Query<T> query = session.createQuery(hql);
            //return query.list();
            return query.list().stream().distinct().collect(Collectors.toList());
        }
    }

    public T getObjectByName(String hql, String objName){
        logger.debug("INTO the method getObjectByName");
        if (hql == null) return null;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("param", objName.toLowerCase());
        T object = (T) query.uniqueResult();
        t.commit();
        return object;
    }

    public T getObjectByName(String hql, String param1, String param2){
        logger.debug("INTO the method getObjectByName");
        if (hql == null) return null;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("param1", param1.toLowerCase().trim());
        query.setParameter("param2", param2);
        T object = (T) query.uniqueResult();
        t.commit();
        return object;
    }

    public List<Object[]> getCombinedObjects(String hql, String objName){
        logger.debug("INTO the method getCombinedObjects");
        if(hql == null) return null;
        try (Session session = getSessionFactory().openSession()){
            Query query = session.createQuery(hql);
            query.setParameter("param", objName.toLowerCase());
            List<Object[]> resultList = query.list();
            return resultList;
        }
    }

//    public Session createSession() {
//        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        //transaction = session.beginTransaction();
//        // create new session and need to be closed
//        //session = HibernateUtil.getSessionFactory().openSession();
//        return session;
//    }

}
