package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ActionDao extends JpaRepository<Action, Long> , ActionDaoCustom{ }
