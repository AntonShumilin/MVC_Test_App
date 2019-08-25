package DAO;

import Main.DBFactoryUtil;
import Models.Item;
import org.hibernate.HibernateException;

public class ItemDAO {

    DBFactoryUtil dbFactoryUtil;

    public ItemDAO(DBFactoryUtil dbFactoryUtil) {
        this.dbFactoryUtil = dbFactoryUtil;
    }

    public Item findItemById(long id) throws HibernateException {
        return(Item) dbFactoryUtil.getSessionFactory().openSession().get(Item.class, id);
    }

}
