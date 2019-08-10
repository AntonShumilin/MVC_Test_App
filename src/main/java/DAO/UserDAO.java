package DAO;

import Main.DBFactoryUtil;
import Models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {

    DBFactoryUtil dbFactoryUtil;
    private final Map<String, User> sessionsMap;

    public UserDAO (DBFactoryUtil dbFactoryUtil) {
        this.dbFactoryUtil = dbFactoryUtil;
        sessionsMap = new HashMap<>();
    }

    public List<User> findAllUsers () {
        Criteria criteria = dbFactoryUtil.sessionFactory.openSession().createCriteria(User.class);
        return criteria.list();
    }

    public User findById(long id) throws HibernateException {
        return(User) dbFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public User getUserByEmail(String email)  {
        Criteria criteria = dbFactoryUtil.sessionFactory.openSession().createCriteria(User.class);
        return ((User) criteria.add(Restrictions.eq("email", email)).uniqueResult());
    }

    public void save(User user) {
        Session session = dbFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void addSession (String session, User user) {
        sessionsMap.put(session, user);
    }

    public User getUserBySession (String session) {return sessionsMap.get(session);}

    public void removeSession (String session, User user){
        sessionsMap.remove(session, user);
    }





}
