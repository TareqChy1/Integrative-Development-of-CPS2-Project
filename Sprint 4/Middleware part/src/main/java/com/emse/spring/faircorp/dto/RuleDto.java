package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Rule;

import java.util.Set;
import java.util.stream.Collectors;

public class RuleDto {
    private Long id;
    private String name;
    private Set<ConditionDto> condition;
    private Set<ActionDto> action;

    public RuleDto() {
    }

    public RuleDto(Rule rule) {
        this.id = rule.getId();
        this.name = rule.getName();
        if(rule.getCondition() != null) {
            this.condition = rule.getCondition().stream().map(ConditionDto::new).collect(Collectors.toSet());
        }
        if(rule.getAction() != null) {
            this.action = rule.getAction().stream().map(ActionDto::new).collect(Collectors.toSet());
        }
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

    public Set<ConditionDto> getCondition() {
        return condition;
    }

    public void setCondition(Set<ConditionDto> condition) {
        this.condition = condition;
    }

    public Set<ActionDto> getAction() {
        return action;
    }

    public void setAction(Set<ActionDto> action) {
        this.action = action;
    }
}

