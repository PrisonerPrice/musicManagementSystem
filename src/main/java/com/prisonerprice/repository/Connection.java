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

    private Transaction transaction = null;
    private boolean isSuccess = true;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean save(T obj){
        try{
            // curr thread, don't need close it
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            // create new session and need to be closed
            //session = HibernateUtil.getSessionFactory().openSession();
            session.save(obj);
            transaction.commit();
        } catch (Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The album %s was inserted into the table", obj.toString()));
        return isSuccess;
    }

    public boolean update(T obj) {
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(obj);
            transaction.commit();
        } catch (Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The item %s was updated", obj.toString()));
        return isSuccess;
    }

    public boolean delete(String serialNumber, String typeName) {
        String hql = "DELETE " + typeName + " where serialNumber =: serial_num";
        int deletedCount = 0;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Album> query = session.createQuery(hql);
            query.setParameter("serial_num", serialNumber);
            deletedCount = query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The item with the id %s was deleted", serialNumber));
        return deletedCount >= 1 ? true : false;
    }

    public List<T> getAll(String typeName) {
        String hql = "FROM " + typeName;
        // try resources
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<T> query = session.createQuery(hql);
            return query.list();
        }
    }

}
