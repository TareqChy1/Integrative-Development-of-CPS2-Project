package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Rule;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class RuleDaoCustomImpl implements RuleDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Rule> findRules(Long id) {
        String jpql = "select r from Rule r where r.id = :id";
        return em.createQuery(jpql, Rule.class)
                .setParameter("id", id)
                .getResultList();
    }


    @Override
    public Rule findRuleByName(String name) {
        String jpql = "select r from Rule r where r.name = :name";
        return em.createQuery(jpql, Rule.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
