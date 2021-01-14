/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.REQUIRED;

/**
 *
 * @author jarvis
 */
@ApplicationScoped
public class CategoryRepository {
      
    @Produces
    @PersistenceContext(unitName = "demo-jpa-pu")
    private EntityManager em;
    
    @Transactional(REQUIRED)
    public void create(Category category){
        em.persist(category);
    }
    
    public List<Category> findAll(){
        return em.createQuery("SELECT c FROM Category c").getResultList();
    }
    
    public Category findOne(Long id){
        return em.find(Category.class, id);
    }
    
    
    public void remove (Long id){
        em.remove(em.find(Category.class, id));
    }
    
    public Category update(Category category){
        return em.merge(category);
    }
    
}
