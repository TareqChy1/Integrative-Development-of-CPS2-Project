/* package com.emse.spring.SyncSphere.service;

import com.emse.spring.SyncSphere.dao.RuleDao;
import com.emse.spring.SyncSphere.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
    @Autowired
    private RuleDao ruleRepository;

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    public Rule getRuleById(Long id) {
        return ruleRepository.findById(id).orElse(null);
    }

    public Rule createRule(Rule rule) {
        return ruleRepository.save(rule);
    }

    public Rule updateRule(Long id, Rule rule) {
        Rule existingRule = ruleRepository.findById(id).orElse(null);
        if (existingRule == null) {
            return null;
        }
        existingRule.setName(rule.getName());
        existingRule.setCondition(rule.getCondition());
        existingRule.setAction(rule.getAction());
        return ruleRepository.save(existingRule);
    }

    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
}

**/