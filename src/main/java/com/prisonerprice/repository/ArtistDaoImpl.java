package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArtistDaoImpl implements ArtistDao{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Artist artist) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
        } catch (Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The artist %s was inserted into the table", artist.toString()));
        return isSuccess;
    }

    @Override
    public boolean update(Artist artist) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(artist);
            transaction.commit();
        } catch (Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The artist %s was updated", artist.toString()));
        return isSuccess;
    }

    @Override
    public boolean delete(String artistNumber) {
        String hql = "DELETE Artist where serialNumber =: serial_num";
        int deletedCount = 0;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Album> query = session.createQuery(hql);
            query.setParameter("serial_num", artistNumber);
            deletedCount = query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The artist with the id %s was deleted", artistNumber));
        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Artist> getArtists() {
        String hql = "FROM Artist";
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Artist> query = session.createQuery(hql);
            return query.list();
        }
    }
}
