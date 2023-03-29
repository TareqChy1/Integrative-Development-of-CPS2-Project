package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Rule;

import java.util.List;

public interface RuleDaoCustom {
    List<Rule> findRules(Long id);
    Rule findRuleByName(String name);


}
