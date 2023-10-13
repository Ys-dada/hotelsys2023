package com.abc.hotelsys.service;

import com.abc.hotelsys.domain.Hotel;

import java.util.List;

public interface HotelService {

    void createHotel(Hotel hotel);
    List<Hotel> loadHotels();
    void updateHotel(Hotel hotel);
    Hotel getHotelById(int hotelId);
    void removeHotel(int hotelId);

    byte[] getHotelPicById(int hotelId);

}
