package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Connection<T>{

    private boolean isSuccess = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean save(T obj){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try{
            session.save(obj);
            t.commit();
        } catch (Exception e){
            isSuccess = false;
            if(t != null) t.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The album %s was inserted into the table", obj.toString()));
        return isSuccess;
    }

    public boolean update(T obj) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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

//    public boolean delete(int id, String typeName) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction t = null;
//        String hql = "DELETE " + typeName + " where id = :id";
//        int deletedCount = 0;
//        try {
//            Query query = session.createQuery(hql);
//            query.setParameter("id", id);
//            deletedCount = query.executeUpdate();
//            t.commit();
//        } catch (Exception e){
//            if(t != null) t.rollback();
//            logger.error(e.getMessage());
//        }
//        logger.debug(String.format("The item with the id %s was deleted", id));
//        if(deletedCount < 1) logger.debug("Delete Album is failed");
//        return deletedCount >= 1 ? true : false;
//    }

    public List<T> getObjectList(String typeName) {
        String hql = "FROM " + typeName;
        // try resources
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<T> query = session.createQuery(hql);
            return query.list();
        }
    }

    public T getObjectByName(String hql, String param){
        logger.debug("INTO the method getObjectByName");
        if (hql == null) return null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query<T> query = session.createQuery(hql);
        query.setParameter("param", param.toLowerCase());
        // setParameter
        T object = query.uniqueResult();
        t.commit();
        return object;
    }

//    public Session createSession() {
//        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        //transaction = session.beginTransaction();
//        // create new session and need to be closed
//        //session = HibernateUtil.getSessionFactory().openSession();
//        return session;
//    }

}
