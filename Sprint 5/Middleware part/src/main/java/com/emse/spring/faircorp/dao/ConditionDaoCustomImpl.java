package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Condition;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class ConditionDaoCustomImpl implements ConditionDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Condition> findCondition(Long id) {
        String jpql = "select c from Condition c where c.rule.id = :id";
        return em.createQuery(jpql, Condition.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Condition findConditionByName(String name) {
        String jpql = "select c from Condition c where c.name = :name";
        return em.createQuery(jpql, Condition.class)
                .setParameter("name", name)
                .getSingleResult();
    }




}
