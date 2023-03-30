package com.emse.spring.SyncSphere.api;


import com.emse.spring.SyncSphere.dao.RuleDao;
import com.emse.spring.SyncSphere.dto.RuleDto;
import com.emse.spring.SyncSphere.model.Rule;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rules")
@Transactional
public class RuleController {
    private final RuleDao ruleDao;
    public RuleController(RuleDao ruleDao) {
        this.ruleDao = ruleDao;
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
            rule = ruleDao.save(new Rule(dto.getName(),dto.getCondition(),dto.getAction()));
        } else {
            rule = ruleDao.getReferenceById(dto.getId());
            rule.setName(dto.getName());
            rule.setCondition(dto.getCondition());
            rule.setAction(dto.getAction());
        }
        return new RuleDto(rule);
    }
    @DeleteMapping(path = "/{rule_id}")
    public void delete(@PathVariable Long rule_id) {
        ruleDao.deleteById(rule_id);
    }
}
