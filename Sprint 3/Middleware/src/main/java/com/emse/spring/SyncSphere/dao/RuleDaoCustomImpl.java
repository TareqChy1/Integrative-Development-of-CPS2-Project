package com.emse.spring.SyncSphere.dao;

import com.emse.spring.SyncSphere.model.Rule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
}
