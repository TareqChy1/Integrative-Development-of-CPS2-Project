package com.emse.spring.SyncSphere.dto;

import com.emse.spring.SyncSphere.model.Rule;

public class RuleDto {
    private Long id;
    private String name;
    private String condition;
    private String action;
    public RuleDto(){}
    public RuleDto(Rule rule){
        this.id = rule.getId();
        this.name = rule.getName();
        this.condition = rule.getCondition();
        this.action = rule.getAction();
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
