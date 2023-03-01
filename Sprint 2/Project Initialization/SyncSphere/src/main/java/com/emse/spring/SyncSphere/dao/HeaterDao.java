package com.emse.spring.SyncSphere.dao;


import com.emse.spring.SyncSphere.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;



public interface HeaterDao extends JpaRepository<Heater, Long>, HeaterDaoCustom{

}