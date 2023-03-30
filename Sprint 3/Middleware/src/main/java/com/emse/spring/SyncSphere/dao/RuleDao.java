package com.emse.spring.SyncSphere.dao;

import com.emse.spring.SyncSphere.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleDao extends JpaRepository<Rule, Long> , RuleDaoCustom{
    // additional methods can be defined here
}
