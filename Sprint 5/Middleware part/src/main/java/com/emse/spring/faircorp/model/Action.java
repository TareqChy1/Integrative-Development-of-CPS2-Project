package com.emse.spring.faircorp.model;;

import javax.persistence.*;

@Entity
@Table(name = "ACTIONS")
public class Action {
    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    private String name;

    @ManyToOne(optional = false)
    private Rule rule;

    public Action(String name) {
        this.name = name;
    }

    public Action() {

    }

    public Action( String name, Rule rule) {
        this.name = name;
        this.rule = rule;
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

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
