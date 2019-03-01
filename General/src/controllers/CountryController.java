/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CountryDAO;
import java.math.BigDecimal;
import java.util.List;
import models.Country;
import models.Region;
import org.hibernate.SessionFactory;

/**
 *
 * @author gerydanu
 */
public class CountryController {
    
    private CountryDAO cdao;

    public CountryController(SessionFactory factory) {
        cdao=new CountryDAO(factory);
    }
    
    
     public String insert(String id, String name, String region){
        if (cdao.saveOrDelete(new Country(id,name,new Region(new BigDecimal(region))), true)) {
             return "Selamat Data Berhasil di Input";
        }
         return "Maaf Anda Gagal memasukkan data";
    }
    
    public String delete(String id){
        if (cdao.saveOrDelete(new Country(id), false)) {
             return "Selamat Berhasil menghapus data";
        }
         return "Maaf Anda Gagal menghapus data";
  
        
    }
    
    public String update(String id, String name, String region){
        if (cdao.saveOrDelete(new Country(id,name,new Region(new BigDecimal(region))), true)) {
             return "Selamat Data Berhasil di Update";
        }
         return "Maaf Anda Gagal melakukan update";

        
    }
    
    public List<Country> getAll(){
        return cdao.search("", false);
        
    }
    public List<Country> getData(String key, boolean isById){
        return cdao.search(key, isById);
        
    }
    
    public Country getById(Object key){
        return cdao.search(key, true).get(0);
        
    }
}
