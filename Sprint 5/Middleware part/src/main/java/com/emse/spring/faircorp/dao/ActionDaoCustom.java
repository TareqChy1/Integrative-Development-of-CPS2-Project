package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Action;

import java.util.List;

public interface ActionDaoCustom {

    List<Action> findActionByRuleId(Long Id);
    void deleteAllActionByRule(Long id);
}