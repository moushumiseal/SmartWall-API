/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.business;

import edu.sg.nus.iss.smartwall.model.Event;
import edu.sg.nus.iss.smartwall.resource.helper.Service;
import edu.sg.nus.iss.smartwall.util.Constants;
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
public class EventBean {

    @PersistenceContext
    private EntityManager em;

    public List<Event> getAllEvents() {

        TypedQuery<Event> query = em.createQuery("select t from Event t", Event.class);
        return ((List<Event>) query.getResultList());
    }

    public Event findById(Integer tid) {
        Event t = em.find(Event.class, tid);
        return (t);
    }
    
    public List<Event> findByName(String name) {
        TypedQuery<Event> query = em.createQuery("select t from Event t where t.name = :name", Event.class);
        return (query.setParameter("name",name).getResultList());
    }
    
    public Service process(String name) {
        
        StringBuffer sb = new StringBuffer();
        
        for(Event event : findByName(name)){
        
            sb.append(event.getName()+" Event is on "+event.getDate()+" at "+event.getLocation()+" from "+event.getStartTime()+" to "+event.getEndTime()+'\n');
        }
                           
        
        return new Service(sb.toString() , sb.toString() , Constants.ACTION_EVENT);
    }
}
