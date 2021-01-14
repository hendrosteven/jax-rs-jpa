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
public class ItemRepository {
    
    @Produces
    @PersistenceContext(unitName = "demo-jpa-pu")
    private EntityManager em;
    
    @Transactional(REQUIRED)
    public void create(Item item){
        em.persist(item);
    }
    
    public List<Item> findAll(){
        return em.createQuery("SELECT x FROM Item x").getResultList();
    }
    
    public List<Item> findAllByCategoryId(Long id){
        return em.createQuery("SELECT x FROM Item x WHERE category.id= "+id).getResultList();
    }
    
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }
    
    
    public void remove (Long id){
        em.remove(em.find(Item.class, id));
    }
    
    public Item update(Item item){
        return em.merge(item);
    }
    
}
