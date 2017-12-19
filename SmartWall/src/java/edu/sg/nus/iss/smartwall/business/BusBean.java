/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.business;

import edu.sg.nus.iss.smartwall.model.Bus;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dhruv
 */
@Stateless
public class BusBean {
@PersistenceContext
    private EntityManager em;

    public List<Bus> getAllBus() {

        TypedQuery<Bus> query = em.createQuery("select t from Bus t", Bus.class);
        return ((List<Bus>) query.getResultList());
    }

    public Bus findById(Integer tid) {
        Bus t = em.find(Bus.class, tid);
        return (t);
    }
    
    public List<Bus> findByName(String name) {
        TypedQuery<Bus> query = em.createQuery("select t from Bus t where t.name = :name", Bus.class);
        return (query.setParameter("name",name).getResultList());
    }
    
}
