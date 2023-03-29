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
class RuleDaoTest {
    @Autowired
    private RuleDao ruleDao;

    @Test
    public void shouldFindACondition() {
        Rule rule = ruleDao.getReferenceById(-10L);
        Assertions.assertThat(rule.getName()).isEqualTo("Rule 1");
        Assertions.assertThat(rule.getActive()).isEqualTo("True");
        Assertions.assertThat(rule.getDate()).isEqualTo("28/03/2023");
        Assertions.assertThat(rule.getTime()).isEqualTo("9:10");

    }

}
