package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleDao extends JpaRepository<Rule, Long>, RuleDaoCustom {
    Rule findByName(String name);

}
