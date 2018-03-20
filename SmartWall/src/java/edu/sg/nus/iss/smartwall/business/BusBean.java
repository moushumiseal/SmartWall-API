/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.business;

import edu.sg.nus.iss.smartwall.model.Bus;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
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
        LocalDateTime now;
        now= LocalDateTime.now();
        String t_now,t_max;
        t_now= DateTimeFormatter.ofPattern("HH:mm:ss").format(now);
        t_max=shift(t_now);
        Time tn,tm;
        tn=Time.valueOf(t_now);
        tm=Time.valueOf(t_max);
        TypedQuery<Bus> query = em.createQuery("select t from Bus t where t.name = :name and t.time >= '"+tn.toString() +"' and t.time<= '"+tm.toString() +"'", Bus.class);
        return (query.setParameter("name",name).getResultList());
    }
    
    public String shift(String in)
    {
        String res="";
        int h,m;
        h=Integer.parseInt(in.substring(0, 2));
        m=Integer.parseInt(in.substring(3,5));
        if(m<30)
            m+=30;
        else
        {
            h+=1; 
            m=m+30;
            m=m%60;
        }
        if(m<10)
            res+=h+":0"+m+":"+in.substring(6);
        else
        res+=h+":"+m+":"+in.substring(6);
        return res;
    }
}
