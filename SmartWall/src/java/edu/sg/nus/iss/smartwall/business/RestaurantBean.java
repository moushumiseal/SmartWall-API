/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.business;

import edu.sg.nus.iss.smartwall.model.Restaurant;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Moushumi Seal
 */
@Stateless
public class RestaurantBean {

    @PersistenceContext
    private EntityManager em;

    public List<Restaurant> getAllRestaurants() {

        TypedQuery<Restaurant> query = em.createQuery("select t from Restaurant t", Restaurant.class);
        return (query.getResultList());
    }

    public Restaurant findById(Integer tid) {
        Restaurant t = em.find(Restaurant.class, tid);
        return (t);
    }
    
    public List<Restaurant> findByName(String name) {
        TypedQuery<Restaurant> query = em.createQuery("select t from Restaurant t where t.name = :name", Restaurant.class);
        return (query.setParameter("name",name).getResultList());
    }
}
