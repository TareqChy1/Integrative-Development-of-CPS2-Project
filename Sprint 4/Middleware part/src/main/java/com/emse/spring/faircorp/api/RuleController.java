package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.*;
import com.emse.spring.faircorp.dto.RuleDto;
import com.emse.spring.faircorp.model.Rule;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rule")
@Transactional
public class RuleController {
    private final RuleDao ruleDao;
    private final ActionDao actionDao;
    private final ConditionDao conditionDao;

    public RuleController(RuleDao ruleDao, ConditionDao conditionDao, ActionDao actionDao) {
        this.ruleDao = ruleDao;
        this.conditionDao = conditionDao;
        this.actionDao = actionDao;
    }


    @GetMapping
    public List<RuleDto> findAll() {
        return ruleDao.findAll().stream().map(RuleDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{rule_id}")
    public RuleDto findById(@PathVariable Long rule_id) {
        return ruleDao.findById(rule_id).map(RuleDto::new).orElse(null);
    }


    @PostMapping
    public RuleDto create(@RequestBody RuleDto dto) {
        Rule rule = null;
        if (dto.getId() == null) {
            rule = ruleDao.save(new Rule(dto.getName()));
        } else {
            rule = ruleDao.getReferenceById(dto.getId());
            rule.setName(dto.getName());
        }
        return new RuleDto(rule);

    }

    @DeleteMapping(path = "/{rule_id}")
    public void delete(@PathVariable Long rule_id) {
        ruleDao.deleteById(rule_id);
    }
}
