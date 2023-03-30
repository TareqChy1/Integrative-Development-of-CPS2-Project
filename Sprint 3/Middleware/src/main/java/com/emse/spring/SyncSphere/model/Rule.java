package com.emse.spring.SyncSphere.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RULES")
public class Rule {
    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String condition;

    @Column(nullable = false)
    private String action;

    public Rule(){}
    public Rule(String name, String condition, String action) {
        this.name = name;
        this.condition = condition;
        this.action = action;
    }

    // getters and setters
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

