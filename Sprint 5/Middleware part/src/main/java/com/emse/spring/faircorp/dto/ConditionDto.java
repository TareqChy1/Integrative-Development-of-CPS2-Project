package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Condition;

public class ConditionDto {
    private Long id;
    private String name;
    private Long conditionValue;
    private String conditionUnit;
    private String conditionType;
    private Long ruleId;


    public ConditionDto() {
    }

    public ConditionDto(Condition condition) {
        this.id = condition.getId();
        this.name = condition.getName();
        this.conditionValue = condition.getConditionValue();
        this.conditionUnit = condition.getConditionUnit();
        this.conditionType = condition.getConditionType();
        this.ruleId = condition.getRule().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(Long conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getConditionUnit() {
        return conditionUnit;
    }

    public void setConditionUnit(String conditionUnit) {
        this.conditionUnit = conditionUnit;
    }
    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }
    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

}
