package com.emse.spring.SyncSphere.dao;

import com.emse.spring.SyncSphere.model.Room;
import java.util.List;


public interface RoomDaoCustom {

    Room findRoomByName(String name);
    List<Room> findRooms(Long id);

}