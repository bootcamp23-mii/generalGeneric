/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CountryDAO;
import daos.DAOInterface;
import daos.GeneralDAO2;
import java.math.BigDecimal;
import java.util.List;
import models.Country;
import models.Region;
import org.hibernate.SessionFactory;

/**
 *
 * @author Pandu
 */
public class CountryController2 implements CountryControllerInterface {

    private DAOInterface<Country> cdao;

    public CountryController2(SessionFactory factory) {
        cdao = new GeneralDAO2<>(factory, new Country());
    }

    @Override
    public Country getByid(String id) {
        return cdao.getById(id);
    }

    @Override
    public List<Country> getAll() {
        return cdao.getData("");
    }

    @Override
    public List<Country> search(Object keyword) {
        return cdao.getData(keyword);
    }

    @Override
    public String save(String id, String nama, String region) {
        if (cdao.saveOrDelete(new Country(id, nama, new Region(new BigDecimal(region))), true)) {
            return "Save Data Success!";
        } else {
            return "Save Failed!";
        }
    }

    @Override
    public String delete(String id) {
        if (cdao.saveOrDelete(new Country(id), false)) {
            return "Delete Data Success!";
        } else {
            return "Delete Failed!";
        }
    }
}
