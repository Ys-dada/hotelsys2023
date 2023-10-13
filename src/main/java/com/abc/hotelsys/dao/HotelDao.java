package com.abc.hotelsys.dao;

import com.abc.hotelsys.domain.Hotel;

import java.util.List;

public interface HotelDao {
    void addHotel(Hotel hotel);
    List<Hotel> loadHotels();
    void updateHotel(Hotel hotel);
    Hotel getHotelById(int hotelId);
    void delHotel(int hotelId);
    byte[] getHotelPicById(int hotelId);
}
