package com.abc.hotelsys.dao;

import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.exception.DataAccessException;
import com.abc.hotelsys.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class HotelDaoMyBatisImpl implements HotelDao{

    @Override
    public void addHotel(Hotel hotel) {
        SqlSession session = MyBatisUtils.getSession();
        session.insert("mybatis.mapper.HotelMapper.addHotel",hotel);
        session.commit();
        MyBatisUtils.closeSession(session);
    }

    @Override
    public List<Hotel> loadHotels() {
        SqlSession session = MyBatisUtils.getSession();
        List<Hotel> hotelList = session.selectList("mybatis.mapper.HotelMapper.loadHotels");
        session.commit();
        MyBatisUtils.closeSession(session);
        return hotelList;
    }

    @Override
    public void updateHotel(Hotel hotel) {
        SqlSession session = MyBatisUtils.getSession();
        session.update("mybatis.mapper.HotelMapper.updateHotel",hotel);
        session.commit();
        MyBatisUtils.closeSession(session);
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        SqlSession session = MyBatisUtils.getSession();
        Hotel hotel = session.selectOne("mybatis.mapper.HotelMapper.getHotelById",hotelId);
        session.commit();
        MyBatisUtils.closeSession(session);
        return hotel;
    }

    @Override
    public void delHotel(int hotelId) {
        SqlSession session = MyBatisUtils.getSession();

        try {
            session.delete("mybatis.mapper.HotelMapper.delHotelById", hotelId);
        }catch(Exception e){
            if(e.getMessage().contains("FK_HOTEL_ROOM")){
                Hotel hotel = this.getHotelById(hotelId);
                String msg = String.format("%s名下拥有%d间房间,不能删除拥有房间的分店!", hotel.getHotelName(), hotel.getHotelRoomCount());
                System.out.println(msg);
                throw new DataAccessException(msg);
            }
        }finally{
            session.commit();
            MyBatisUtils.closeSession(session);
        }
    }

    @Override
    public byte[] getHotelPicById(int hotelId) {

        SqlSession session = MyBatisUtils.getSession();
        Map data = session.selectOne("mybatis.mapper.HotelMapper.loadHotelPicById",hotelId);
        session.commit();
        MyBatisUtils.closeSession(session);

        if(data!=null){
            Object obj = data.get("imgBytes");
            if(obj!=null)
                return (byte[]) obj;
        }


        return null;

    }

}
