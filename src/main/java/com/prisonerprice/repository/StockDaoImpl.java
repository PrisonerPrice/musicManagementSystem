package com.prisonerprice.repository;

import com.prisonerprice.model.Album;
import com.prisonerprice.model.Artist;
import com.prisonerprice.model.Stock;
import com.prisonerprice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StockDaoImpl implements StockDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Stock stock) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(stock);
            transaction.commit();
        } catch (Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The stock %s was inserted into the table", stock.toString()));
        return isSuccess;
    }

    @Override
    public boolean update(Stock stock) {
        Transaction transaction = null;
        boolean isSuccess = true;
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(stock);
            transaction.commit();
        } catch (Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The stock %s was updated", stock.toString()));
        return isSuccess;
    }

    @Override
    public boolean delete(String stockNumber) {
        String hql = "DELETE Stock where serialNumber =: serial_num";
        int deletedCount = 0;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query<Stock> query = session.createQuery(hql);
            query.setParameter("serial_num", stockNumber);
            deletedCount = query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The stock with the id %s was deleted", stockNumber));
        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Stock> getStocks() {
        String hql = "FROM Stock";
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Stock> query = session.createQuery(hql);
            return query.list();
        }
    }
}
