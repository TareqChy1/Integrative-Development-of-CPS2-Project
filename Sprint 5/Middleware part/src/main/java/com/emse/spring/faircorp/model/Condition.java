package com.emse.spring.faircorp.model;

import javax.persistence.*;


@Entity
@Table(name = "CONDITIONS")
public class Condition {
    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private Long conditionValue;
    @Column(nullable=false)
    private String conditionUnit;

    @Column(nullable=false)
    private String conditionType;
    @ManyToOne(optional = false)
    private Rule rule;


    public Condition() {}
    public Condition(String name, Long conditionValue, String conditionUnit, String conditionType) {
        this.name = name;
        this.conditionValue = conditionValue;
        this.conditionUnit = conditionUnit;
        this.conditionType = conditionType;
    }

    public Condition(String name, Long conditionValue, String conditionUnit, String conditionType, Rule rule) {
        this.name = name;
        this.conditionValue = conditionValue;
        this.conditionUnit = conditionUnit;
        this.conditionType = conditionType;
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

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

}
