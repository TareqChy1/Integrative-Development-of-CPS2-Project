package com.emse.spring.SyncSphere.dao;
import com.emse.spring.SyncSphere.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface RoomDao extends JpaRepository<Room, Long>, RoomDaoCustom{


}