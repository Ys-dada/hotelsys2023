package com.abc.hotelsys.dao;

import com.abc.hotelsys.domain.Room;
import com.abc.hotelsys.service.RoomQueryHelper;

import java.util.List;

public interface RoomDao {

    void addRoom(Room room);
    Room getRoomById(int roomId);
    List<Room> loadRooms();
    void delRoom(Integer roomId);
    List<Room> loadRooms(RoomQueryHelper helper);
    List<Room> getScopedRooms(RoomQueryHelper helper,int startIndex, int fetchSize);
    int cntRooms(RoomQueryHelper helper);
    void updateRoom(Room room);
}
