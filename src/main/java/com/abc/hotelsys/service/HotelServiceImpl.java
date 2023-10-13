package com.abc.hotelsys.service;

import com.abc.hotelsys.dao.HotelDao;
import com.abc.hotelsys.dao.HotelDaoMyBatisImpl;
import com.abc.hotelsys.domain.Hotel;

import java.util.List;

public class HotelServiceImpl implements HotelService {

    @Override
    public void createHotel(Hotel hotel) {
        HotelDao hotelDao = new HotelDaoMyBatisImpl();
        hotelDao.addHotel(hotel);
    }

    @Override
    public List<Hotel> loadHotels() {
        HotelDao hotelDao = new HotelDaoMyBatisImpl();
        return hotelDao.loadHotels();
    }

    @Override
    public void updateHotel(Hotel hotel) {
        HotelDao hotelDao = new HotelDaoMyBatisImpl();
        hotelDao.updateHotel(hotel);
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        HotelDao hotelDao = new HotelDaoMyBatisImpl();
        return hotelDao.getHotelById(hotelId);
    }

    @Override
    public void removeHotel(int hotelId) {
        HotelDao hotelDao = new HotelDaoMyBatisImpl();
        hotelDao.delHotel(hotelId);
    }

    @Override
    public byte[] getHotelPicById(int hotelId) {
        HotelDao hotelDao = new HotelDaoMyBatisImpl();
        return hotelDao.getHotelPicById(hotelId);
    }

}
