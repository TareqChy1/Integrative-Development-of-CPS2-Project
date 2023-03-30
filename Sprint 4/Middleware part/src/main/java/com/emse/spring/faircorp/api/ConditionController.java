package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.ConditionDao;
import com.emse.spring.faircorp.dao.RuleDao;
import com.emse.spring.faircorp.dto.ConditionDto;
import com.emse.spring.faircorp.model.*;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/condition")
@Transactional
public class ConditionController {

    private final ConditionDao conditionDao;
    private final RuleDao ruleDao;

    public ConditionController(ConditionDao conditionDao, RuleDao ruleDao) {
        this.conditionDao = conditionDao;
        this.ruleDao = ruleDao;
    }

    @GetMapping
    public List<ConditionDto> findAll() {
        return conditionDao.findAll().stream().map(ConditionDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{condition_id}")
    public ConditionDto findById(@PathVariable Long condition_id) {
        return conditionDao.findById(condition_id).map(ConditionDto::new).orElse(null);
    }

    @GetMapping(path = "/rule/{rule_id}")
    public List<ConditionDto> findConditionByRuleId(@PathVariable Long rule_id) {
        return conditionDao.findConditionByRuleId(rule_id).stream().map(ConditionDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public ConditionDto create(@RequestBody ConditionDto dto) {
        Rule rule = ruleDao.getReferenceById(dto.getRuleId());
        Condition condition = null;

        if (dto.getId() == null) {
            condition = conditionDao.save(new Condition( dto.getName(), dto.getConditionValue(), dto.getConditionUnit(), rule));
        }
        else {
            condition = conditionDao.getReferenceById(dto.getId());
            condition.setName(dto.getName());
            condition.setConditionValue(dto.getConditionValue());
            condition.setConditionUnit(dto.getConditionUnit());
        }
        return new ConditionDto(condition);
    }

    @DeleteMapping(path = "/{condition_id}")
    public void delete(@PathVariable Long condition_id) {
        conditionDao.deleteById(condition_id);
    }
}