package DAO;

import Main.DBFactoryUtil;
import Models.DateParams;
import Models.Receipt;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<Receipt> findAllReceiptsWithFilters (long userId, DateParams dateParams, String userInn) {
        if (userInn == null) {
            try {
                if (dateParams.afteDate == null) dateParams.afteDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("1970-01-01T00:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateParams.beforeDate == null) dateParams.beforeDate = new Date();
            Criteria criteria = dbFactoryUtil.sessionFactory.openSession().createCriteria(Receipt.class)
                    .add(Restrictions.eq("userId", userId))
                    .add(Restrictions.between("dateTime", dateParams.afteDate, dateParams.beforeDate));
            return criteria.list();
        } else {
            try {
                if (dateParams.afteDate == null) dateParams.afteDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("1970-01-01T00:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateParams.beforeDate == null) dateParams.beforeDate = new Date();
            Criteria criteria = dbFactoryUtil.sessionFactory.openSession().createCriteria(Receipt.class)
                    .add(Restrictions.eq("userId", userId))
                    .add(Restrictions.eq("userInn", userInn))
                    .add(Restrictions.between("dateTime", dateParams.afteDate, dateParams.beforeDate));
            return criteria.list();

        }
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
