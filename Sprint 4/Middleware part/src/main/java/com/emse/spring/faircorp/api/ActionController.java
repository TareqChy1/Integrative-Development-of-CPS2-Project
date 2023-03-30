package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.ActionDao;
import com.emse.spring.faircorp.dao.RuleDao;
import com.emse.spring.faircorp.dto.ActionDto;
import com.emse.spring.faircorp.model.Action;
import com.emse.spring.faircorp.model.Rule;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/action")
@Transactional
public class ActionController {
    private final ActionDao actionDao;
    private final RuleDao ruleDao;

    public ActionController(ActionDao actionDao, RuleDao ruleDao) {
        this.actionDao = actionDao;
        this.ruleDao = ruleDao;
    }

    @GetMapping
    public List<ActionDto> findAll() {
        return actionDao.findAll().stream().map(ActionDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{action_id}")
    public ActionDto findById(@PathVariable Long action_id) {
        return actionDao.findById(action_id).map(ActionDto::new).orElse(null);
    }

    @GetMapping(path = "/rule/{rule_id}")
    public List<ActionDto> findActionByRuleId(@PathVariable Long rule_id) {
        return actionDao.findActionByRuleId(rule_id).stream().map(ActionDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public ActionDto create(@RequestBody ActionDto dto) {
        Rule rule = ruleDao.getReferenceById(dto.getRuleId());
        Action action = null;

        if (dto.getId() == null) {
            action = actionDao.save(new Action(dto.getName(), rule));
        }
        else {
            action = actionDao.getReferenceById(dto.getId());
            action.setName(dto.getName());
        }
        return new ActionDto(action);
    }

    @DeleteMapping(path = "/{action_id}")
    public void delete(@PathVariable Long action_id) {
        actionDao.deleteById(action_id);
    }
}