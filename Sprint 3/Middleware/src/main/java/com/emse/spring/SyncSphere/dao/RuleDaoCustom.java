package com.emse.spring.SyncSphere.dao;

import com.emse.spring.SyncSphere.model.Rule;

import java.util.List;

public interface RuleDaoCustom {
    List<Rule> findRules(Long id);

}
