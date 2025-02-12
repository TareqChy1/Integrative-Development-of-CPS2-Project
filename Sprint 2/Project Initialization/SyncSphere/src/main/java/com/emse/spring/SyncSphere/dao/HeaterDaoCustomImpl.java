package com.emse.spring.SyncSphere.dao;


import com.emse.spring.SyncSphere.model.Heater;
import com.emse.spring.SyncSphere.model.HeaterStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;



public class HeaterDaoCustomImpl implements HeaterDaoCustom{

    @PersistenceContext
    private EntityManager em;


    //For checking room's heaterStatus is ON
    @Override
    public List<Heater> findRoomOnHeaters(Long id) {

        String jpql = "select h from Heater h where h.room.id = :id and h.heaterStatus= :status";
        return em.createQuery(jpql, Heater.class)
                .setParameter("id", id)
                .setParameter("status", HeaterStatus.ComfortMode)
                .getResultList();

    }


    //For deleting all heaters by using roomId
    @Override
    public void deleteAllHeatersByRoom(Long id) {

        String jpql = "delete from Heater h where h.room.id = :id";
        em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();

    }


    //For getting heater list by using roomId
    @Override
    public List<Heater> findHeaterByRoomId(Long Id) {

        String jpql = "select h from Heater h where h.room.id = :id";
        return em.createQuery(jpql, Heater.class)
                .setParameter("id", Id)
                .getResultList();

    }


}