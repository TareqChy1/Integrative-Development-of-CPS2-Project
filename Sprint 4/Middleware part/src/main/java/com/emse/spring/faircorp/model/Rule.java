package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.model.*;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RULES")
public class Rule {
    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    private String name;

    @OneToMany(mappedBy = "rule",cascade = CascadeType.REMOVE)
    private Set<Condition> condition;

    @OneToMany(mappedBy = "rule",cascade = CascadeType.REMOVE)
    private Set<Action> action;

    public Rule() {}

    public Rule(String name) {
        this.name = name;
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

    public Set<Condition> getCondition() {
        return condition;
    }

    public void setCondition(Set<Condition> condition) {
        this.condition = condition;
    }

    public Set<Action> getAction() {
        return action;
    }

    public void setAction(Set<Action> action) {
        this.action = action;
    }
}

