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

    @Column(nullable=false)
    private String active;
    @Column(nullable=false)
    private String date;
    @Column(nullable=false)
    private String time;
    @OneToMany(mappedBy = "rule",cascade = CascadeType.REMOVE)
    private Set<Condition> condition;

    @OneToMany(mappedBy = "rule",cascade = CascadeType.REMOVE)
    private Set<Action> action;

    public Rule() {}

    public Rule(String name, String active, String date, String time) {
        this.name = name;
        this.active = active;
        this.date = date;
        this.time = time;
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
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

