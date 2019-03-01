/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import models.Country;

/**
 *
 * @author Pandu
 */
public interface CountryControllerInterface {
    public Country getByid(String id);
    public List<Country> getAll();
    public List<Country> search(Object keyword);
    public String save(String id,String nama,String region);
    public String delete(String id);
}
