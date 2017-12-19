/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.business;


import edu.sg.nus.iss.smartwall.model.Booking;
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
public class BookingBean {

    @PersistenceContext
    private EntityManager em;

    public List<Booking> getAllBookings() {

        TypedQuery<Booking> query = em.createQuery("select t from Booking t", Booking.class);
        return (query.getResultList());
    }

    public Booking findById(Integer tid) {
        Booking t = em.find(Booking.class, tid);
        return (t);
    }
    
    public List<Booking> findByName(String name) {
        TypedQuery<Booking> query = em.createQuery("select t from Booking t where t.name = :name", Booking.class);
        return (query.setParameter("name",name).getResultList());
    }
}
