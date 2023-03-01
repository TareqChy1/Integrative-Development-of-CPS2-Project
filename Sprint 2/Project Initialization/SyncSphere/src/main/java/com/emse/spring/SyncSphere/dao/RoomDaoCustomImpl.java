package com.emse.spring.SyncSphere.dao;
import com.emse.spring.SyncSphere.model.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;


public class RoomDaoCustomImpl implements RoomDaoCustom{

    @PersistenceContext
    private EntityManager em;


    //For finding all rooms list
    @Override
    public List<Room> findRooms(Long id) {

        String jpql = "select r from Room r where r.id = :id";
        return em.createQuery(jpql, Room.class)
                .setParameter("id", id)
                .getResultList();

    }


    //For finding room by using roomName
    @Override
    public Room findRoomByName(String name) {

        String jpql = "select r from Room r where r.name = :name";
        return em.createQuery(jpql, Room.class)
                .setParameter("name", name)
                .getSingleResult();

    }


}