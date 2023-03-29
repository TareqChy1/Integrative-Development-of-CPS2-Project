package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Action;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ActionDaoCustomImpl implements ActionDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void deleteAllActionByRule(Long id) {
        String jpql = "delete from Action a where a.rule.id = :id";
        em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();
    }


    @Override
    public List<Action> findActionByRuleId(Long Id) {
        String jpql = "select a from Action a where a.rule.id = :id";
        return em.createQuery(jpql, Action.class)
                .setParameter("id", Id)
                .getResultList();
    }

}
