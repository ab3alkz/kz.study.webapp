/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.study.session;

import kz.study.entity.Words;
import kz.study.util.Utx;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * @author a.amanzhol
 */
@Stateless
public class AppSession extends Utx {


    private static final Logger logger = Logger.getLogger(AppSession.class);

    @PersistenceContext(unitName = "study")
    private EntityManager em;


    private static final int PER_DEF_START = 0;
    private static final int PER_DEF_COUNT = 30;



    public List<Words> getRandom10WordList() {
        return em.createNamedQuery("Words.findAll").setFirstResult(0).setMaxResults(10).getResultList();
    }

    public List<Words> getTestTypeList() {
        return em.createNamedQuery("TestType.findAll").getResultList();
    }
 }
