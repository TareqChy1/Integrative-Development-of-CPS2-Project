package com.emse.spring.faircorp.dao;


import com.emse.spring.faircorp.model.Action;
import com.emse.spring.faircorp.model.Condition;
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
    class ConditionDaoTest {
        @Autowired
        private ConditionDao conditionDao;
        @Autowired
        private RuleDao ruleDao;

    @Test
    public void shouldFindACondition() {
        Condition condition = conditionDao.getReferenceById(-10L);
        Assertions.assertThat(condition.getName()).isEqualTo("presence not detected");
        Assertions.assertThat(condition.getConditionValue()).isEqualTo(1);
        Assertions.assertThat(condition.getConditionUnit()).isEqualTo("hour");
        Assertions.assertThat(condition.getConditionType()).isEqualTo("above");
    }


}
