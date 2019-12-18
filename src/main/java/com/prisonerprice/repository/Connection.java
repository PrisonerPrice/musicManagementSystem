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

    private boolean isSuccess = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean save(T obj){
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try{
            session.save(obj);
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
        //logger.warn(object.toString());
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
