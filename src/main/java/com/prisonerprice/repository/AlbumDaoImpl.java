package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class AlbumDaoImpl implements AlbumDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Album album) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try{
            // curr thread, don't need close it
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            // create new session and need to be closed
            //session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();
            session.save(album);
            transaction.commit();
        } catch (Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The album %s was inserted into the table", album.toString()));
        return isSuccess;
    }

    @Override
    public boolean update(Album album) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(album);
            transaction.commit();
        } catch (Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The album %s was updated", album.toString()));
        return isSuccess;
    }

    @Override
    public boolean delete(String albumNumber) {
        String hql = "DELETE Album where serialNumber =: serial_num";
        int deletedCount = 0;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Album> query = session.createQuery(hql);
            query.setParameter("serial_num", albumNumber);
            deletedCount = query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The album with the id %s was deleted", albumNumber));
        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Album> getAlbums() {
        String hql = "FROM Album";
        // try resources
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Album> query = session.createQuery(hql);
            return query.list();
        }
    }
}
