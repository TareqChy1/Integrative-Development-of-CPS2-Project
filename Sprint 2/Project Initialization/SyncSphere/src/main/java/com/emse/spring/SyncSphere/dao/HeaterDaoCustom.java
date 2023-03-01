package com.emse.spring.SyncSphere.dao;

import com.emse.spring.SyncSphere.model.Heater;
import java.util.List;


public interface HeaterDaoCustom {

    List<Heater> findHeaterByRoomId(Long Id);
    void deleteAllHeatersByRoom(Long id);
    List<Heater> findRoomOnHeaters(Long id);

}