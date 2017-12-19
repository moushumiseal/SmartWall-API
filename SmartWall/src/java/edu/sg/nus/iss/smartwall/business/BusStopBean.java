/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.business;

import edu.sg.nus.iss.smartwall.model.BusStop;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dhruv
 */
@Stateless
public class BusStopBean  {

    @PersistenceContext
    private EntityManager em;

    public List<BusStop> getAllBusStops() {

        TypedQuery<BusStop> query = em.createQuery("select t from BusStop t", BusStop.class);
        return ((List<BusStop>) query.getResultList());
    }

    public BusStop findById(Integer tid) {
        BusStop t = em.find(BusStop.class, tid);
        return (t);
    }
    
    public List<BusStop> findByName(String name) {
        TypedQuery<BusStop> query = em.createQuery("select t from BusStop t where t.name = :name", BusStop.class);
        return (query.setParameter("name",name).getResultList());
    }
    
}
