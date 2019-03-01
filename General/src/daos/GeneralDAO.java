/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import models.Region;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Pandu
 */
public class GeneralDAO {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    public GeneralDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public GeneralDAO() {

    }

    private String getQuery(Object ent, String keyword) {
        String query = "From " + ent.getClass().getSimpleName();
        if (!keyword.equals("")) {
            query += " where ";
            for (Field field : ent.getClass().getDeclaredFields()) {
                if (!field.getName().contains("UID") && !field.getName().contains("List")) {
                    query += field.getName() + " like '%" + keyword + "%' OR ";
                }
            }
            query = query.substring(0, query.lastIndexOf("OR"));
        }
        return query + " order by 1";
    }

    public List<Object> getData(Object ent, String keyword) {
        List<Object> obj = new ArrayList<>();
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        try {
            obj = session.createQuery(getQuery(ent, keyword)).list();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return obj;
    }
    
    public Object getById(Object ent, Object id) {
        Object obj = new Object();
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        try {
            obj = session.createQuery("FROM "+ent.getClass().getSimpleName()+" WHERE id = '"+id+"'").uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return obj;
    }

    public boolean saveordelete(Object ent, Boolean isSave) {
        boolean result = false;
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        try {
            if (isSave) {
                session.saveOrUpdate(ent);
            } else {
                session.delete(ent);
            }
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

}
