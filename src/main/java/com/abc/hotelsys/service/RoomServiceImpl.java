package com.abc.hotelsys.service;

import com.abc.hotelsys.dao.HotelDao;
import com.abc.hotelsys.dao.HotelDaoMyBatisImpl;
import com.abc.hotelsys.dao.RoomDao;
import com.abc.hotelsys.dao.RoomDaoMyBatisImpl;
import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.domain.Room;
import com.abc.hotelsys.utils.Page;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    @Override
    public void createRoom(Room room) {

        RoomDao roomDao = new RoomDaoMyBatisImpl();
        HotelDao hotelDao = new HotelDaoMyBatisImpl();

        roomDao.addRoom(room);
        Hotel hotel = hotelDao.getHotelById(room.getHotel().getHotelId());
        hotel.setHotelRoomCount(hotel.getHotelRoomCount()+1);
        hotelDao.updateHotel(hotel);
    }

    @Override
    public List<Room> loadRooms() {
        RoomDao roomDao = new RoomDaoMyBatisImpl();
        return roomDao.loadRooms();
    }

    @Override
    public void delRoom(Integer roomId) {
        RoomDao roomDao = new RoomDaoMyBatisImpl();
        HotelDao hotelDao=new HotelDaoMyBatisImpl();
        Room room= roomDao.getRoomById(roomId);
        roomDao.delRoom(roomId);

        Hotel hotel=hotelDao.getHotelById(room.getHotel().getHotelId());
        hotel.setHotelRoomCount(hotel.getHotelRoomCount()-1);
        hotelDao.updateHotel(hotel);

    }
    @Override
    public List<Room> loadRooms(RoomQueryHelper helper) {
        RoomDao roomDao = new RoomDaoMyBatisImpl();
        return roomDao.loadRooms(helper);
    }
    @Override
    public Room getRoomById(Integer roomId) {
        RoomDao roomDao=new RoomDaoMyBatisImpl();
        return roomDao.getRoomById(roomId);
    }
    @Override
    public Page loadPagedRooms(RoomQueryHelper helper, Page page) {

        RoomDao roomDao = new RoomDaoMyBatisImpl();
        page.setTotalRecNum((long)roomDao.cntRooms(helper));
        page.setPageContent(roomDao.getScopedRooms(helper,page.getStartIndex(),page.getPageSize()));

        return page;
    }

    @Override
    public void updateRoom(Room room) {
        HotelDao hotelDao=new HotelDaoMyBatisImpl();
        RoomDao roomDao=new RoomDaoMyBatisImpl();
        roomDao.updateRoom(room);
        Hotel hotel = hotelDao.getHotelById(room.getHotel().getHotelId());
        hotelDao.updateHotel(hotel);
    }
}
