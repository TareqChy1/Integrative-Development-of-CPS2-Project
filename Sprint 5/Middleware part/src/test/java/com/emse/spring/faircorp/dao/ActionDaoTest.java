package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Action;
import com.emse.spring.faircorp.model.Rule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class ActionDaoTest {
    @Autowired
    private ActionDao actionDao;
    @Autowired
    private RuleDao ruleDao;

    @Test
    public void shouldFindACondition() {
        Action action = actionDao.getReferenceById(-10L);
        Assertions.assertThat(action.getName()).isEqualTo("turn heating off");

    }

    @Test
    public void shouldDeleteAction() {
        Rule rule = ruleDao.getReferenceById(-10L);
        List<Long> ruleIds = rule.getAction().stream().map(Action::getId).collect(Collectors.toList());
        Assertions.assertThat(ruleIds.size()).isEqualTo(1);

        actionDao.deleteAllActionByRule(-10L);
        List<Action> result = actionDao.findAllById(ruleIds);
        Assertions.assertThat(result).isEmpty();

    }

}
