package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Rule;

import java.util.Set;
import java.util.stream.Collectors;

public class RuleDto {
    private Long id;
    private String name;
    private String active;
    private String date;
    private String time;
    private Set<ConditionDto> condition;
    private Set<ActionDto> action;

    public RuleDto() {
    }

    public RuleDto(Rule rule) {
        this.id = rule.getId();
        this.name = rule.getName();
        this.active = rule.getActive();
        this.date = rule.getDate();
        this.time = rule.getTime();
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
    public String getActive() { return active; }

    public void setActive(String active) { this.active = active; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

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

