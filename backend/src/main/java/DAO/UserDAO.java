package DAO;

import Main.DBFactoryUtil;
import Main.Main;
import Models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {

    DBFactoryUtil dbFactoryUtil;
    private final Map<String, User> sessionsMap;
    private final Map<String, String> adminMap;


    public UserDAO (DBFactoryUtil dbFactoryUtil) {
        this.dbFactoryUtil = dbFactoryUtil;
        sessionsMap = new HashMap<>();
        adminMap = new HashMap<>();
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

    public void addAdminSession (String session, String email) {
        adminMap.put(session, email);
    }

    public User getUserBySession (String session) {return sessionsMap.get(session);}

    public String getAdminBySession (String session) {return adminMap.get(session);}

    public void removeSession (String session, User user){
        sessionsMap.remove(session, user);
    }

    public void removeAdminSession (String session, String email){
        adminMap.remove(session, email);
    }

    public static boolean checkAuthUtil (UserDAO userDAO, HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (userDAO.getUserBySession(request.getSession().getId()) == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Вы неавторизованы");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return true;
        }
        return false;
    }

    public static boolean checkAdminAuthUtil (UserDAO userDAO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = userDAO.getAdminBySession(request.getSession().getId());
        if (!Main.config.oauth.adminUsers.contains(email)) {
            response.sendRedirect("/api/v1/admin/oauth");
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().println("Вы неавторизованы");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return true;
        }
        return false;
    }





}
