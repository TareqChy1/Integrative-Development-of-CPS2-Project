package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConditionDao extends JpaRepository<Condition, Long>, ConditionDaoCustom {
    List<Condition> findConditionByRuleId(Long ruleId);
    Condition findByName(String name);

    Condition findByConditionType(String name);
}
