package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.dto.RuleDto;
import com.emse.spring.faircorp.model.Condition;

import java.util.List;

public interface ConditionDaoCustom {
    List<Condition> findCondition(Long id);
    Condition findConditionByName(String name);

}
