/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sg.nus.iss.smartwall.business;

import edu.sg.nus.iss.smartwall.model.Test;
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
public class TestBean {

    @PersistenceContext
    private EntityManager em;

    public List<Test> getAllTests() {

        TypedQuery<Test> query = em.createQuery("select t from Test t", Test.class);
        return (query.getResultList());
    }

    public Test findById(Integer tid) {
        Test t = em.find(Test.class, tid);
        return (t);
    }
}
