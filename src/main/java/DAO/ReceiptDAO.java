package DAO;

import Main.DBFactoryUtil;
import Models.Check;
import Models.Receipt;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ReceiptDAO {

    DBFactoryUtil dbFactoryUtil;

    public ReceiptDAO(DBFactoryUtil dbFactoryUtil) {
        this.dbFactoryUtil = dbFactoryUtil;
    }


    public List<Receipt> findAllReceiptsByUserId (long userId) {
        Criteria criteria = dbFactoryUtil.sessionFactory.openSession().createCriteria(Receipt.class)
                .add(Restrictions.eq("userId", userId));
        return criteria.list();
    }

    public Receipt findReceiptkById(long id) throws HibernateException {
        return(Receipt) dbFactoryUtil.getSessionFactory().openSession().get(Receipt.class, id);
    }

    public Receipt findReceiptByUserId(long userId) throws HibernateException {
        return(Receipt) dbFactoryUtil.getSessionFactory().openSession().get(Receipt.class, userId);
    }

    public void save(Receipt receipt) {

        Session session = dbFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(receipt);
        tx1.commit();
        session.close();

    }



}
