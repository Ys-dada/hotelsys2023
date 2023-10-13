package com.abc.hotelsys.service;

import com.abc.hotelsys.domain.Room;
import com.abc.hotelsys.utils.Page;

import java.util.List;

public interface RoomService {
    void createRoom(Room room);
    List<Room> loadRooms();
    void delRoom(Integer roomId);

    List<Room> loadRooms(RoomQueryHelper helper);
    Room getRoomById(Integer roomId);
    Page loadPagedRooms(RoomQueryHelper helper, Page page);
    void updateRoom(Room room);
}
