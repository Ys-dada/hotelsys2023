package com.abc.hotelsys.dao;

import com.abc.hotelsys.domain.Room;
import com.abc.hotelsys.service.RoomQueryHelper;
import com.abc.hotelsys.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDaoMyBatisImpl implements RoomDao {

    @Override
    public void addRoom(Room room) {
        SqlSession session = MyBatisUtils.getSession();
        session.insert("mybatis.mapper.RoomMapper.addRoom",room);
        session.commit();
        MyBatisUtils.closeSession(session);
    }

    @Override
    public Room getRoomById(int roomId) {
        SqlSession session = MyBatisUtils.getSession();
        Room room = session.selectOne("mybatis.mapper.RoomMapper.getRoomById",roomId);
        session.commit();
        MyBatisUtils.closeSession(session);
        return room;
    }

    @Override
    public List<Room> loadRooms() {
        SqlSession session = MyBatisUtils.getSession();
        List<Room> roomList = session.selectList("mybatis.mapper.RoomMapper.loadRooms");
        session.commit();
        MyBatisUtils.closeSession(session);
        return roomList;
    }

    @Override
    public void delRoom(Integer roomId) {
        SqlSession session = MyBatisUtils.getSession();
        session.delete("mybatis.mapper.RoomMapper.delRoom",roomId);
        session.commit();
        MyBatisUtils.closeSession(session);
    }

    @Override
    public List<Room> loadRooms(RoomQueryHelper helper) {
        SqlSession session = MyBatisUtils.getSession();
        List<Room> roomList = session.selectList("mybatis.mapper.RoomMapper.loadRoomsByHelper",helper);
        session.commit();
        MyBatisUtils.closeSession(session);
        return roomList;
    }
    public List<Room> getScopedRooms(RoomQueryHelper helper, int startIdx, int fetchSize) {

        SqlSession session = MyBatisUtils.getSession();

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("helper",helper);
        paramMap.put("start",startIdx);
        paramMap.put("size",fetchSize);

        List<Room> roomList = session.selectList("mybatis.mapper.RoomMapper.getScopedRooms",paramMap);
        session.commit();
        MyBatisUtils.closeSession(session);

        return roomList;

    }
    @Override
    public int cntRooms(RoomQueryHelper helper) {
        SqlSession session = MyBatisUtils.getSession();
        int cnt = (Integer)session.selectOne("mybatis.mapper.RoomMapper.cntRooms",helper);
        session.commit();
        MyBatisUtils.closeSession(session);
        return cnt;
    }

    @Override
    public void updateRoom(Room room) {
        SqlSession session = MyBatisUtils.getSession();
        session.update("mybatis.mapper.RoomMapper.updateRoom",room);
        session.commit();
        MyBatisUtils.closeSession(session);
    }

}
