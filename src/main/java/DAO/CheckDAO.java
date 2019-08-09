package DAO;

import Main.DBFactoryUtil;
import Models.Check;
import Models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class CheckDAO {

    DBFactoryUtil dbFactoryUtil;

    public CheckDAO(DBFactoryUtil dbFactoryUtil) {
        this.dbFactoryUtil = dbFactoryUtil;
    }

    public ArrayList<Check> findAllChecksByUserId (long userId) throws HibernateException {
        Criteria criteria = dbFactoryUtil.sessionFactory.openSession().createCriteria(Check.class);
        return ((ArrayList<Check>) criteria.add(Restrictions.eq("userId", userId)));
    }


    public Check findCheckById(long id) throws HibernateException {
        return(Check) dbFactoryUtil.getSessionFactory().openSession().get(Check.class, id);
    }

    public Check findCheckByUserId(long userId) throws HibernateException {
        return(Check) dbFactoryUtil.getSessionFactory().openSession().get(Check.class, userId);
    }

    public void save(Check check) {
        Session session = dbFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(check);
        tx1.commit();
        session.close();
    }


}
