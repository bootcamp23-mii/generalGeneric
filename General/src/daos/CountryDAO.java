/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.ArrayList;
import java.util.List;
import models.Country;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author gerydanu
 */
public class CountryDAO {

    private Session session;
    private SessionFactory factory;
    private Transaction transaction;

    public CountryDAO(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     *
     * @param keyword
     * @param isGetById
     * @return
     */
    public boolean saveOrDelete(Country country, boolean isSave) {
        boolean result = false;
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        try {
            if (isSave) {
                session.save(country);
            } else {
                session.delete(country);
            }

            transaction.commit();
            result = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public List<Country> search(Object key, boolean isById) {
        List<Country> listCountry = new ArrayList<>();
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        try {
            if (isById) {
                listCountry = session.createQuery("FROM Country WHERE id = " + key + " order by 1").list();
            } else {
                listCountry = session.createQuery("FROM Country WHERE id like '%"
                        + key + "%' or name like '%" + key + "%' or region like '%" + key + "%' order by 1").list();
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return listCountry;

    }

}
