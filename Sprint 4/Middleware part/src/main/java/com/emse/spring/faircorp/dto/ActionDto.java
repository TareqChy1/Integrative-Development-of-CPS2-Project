package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Action;

public class ActionDto {
    private Long id;
    private String name;
    private Long ruleId;

    public ActionDto() {
    }

    public ActionDto(Action action) {
        this.id = action.getId();
        this.name = action.getName();
        this.ruleId = action.getRule().getId();
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

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }
}