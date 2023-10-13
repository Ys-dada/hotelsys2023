package com.abc.hotelsys.dao;

import com.abc.hotelsys.domain.Hotel;
import com.abc.hotelsys.domain.Room;
import com.abc.hotelsys.domain.User;
import com.abc.hotelsys.exception.DataAccessException;
import com.abc.hotelsys.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDaoMyBatisImpl implements UserDao {


    public List<User> loadUsers() {
        SqlSession session = MyBatisUtils.getSession();
        List<User> userList = session.selectList("mybatis.mapper.UserMapper.loadUsers");
        session.commit();
        MyBatisUtils.closeSession(session);
        return userList;
    }

    @Override
    public void addUser(User user) {
        SqlSession session = MyBatisUtils.getSession();

        try {
            session.insert("mybatis.mapper.UserMapper.addUser", user);
        }catch(Exception e){
            if(e.getMessage().contains("Duplicate entry")){
                String msg = String.format("登录编号已存在!");
                System.out.println(msg);
                throw new DataAccessException(msg);
            }
        }finally{
            session.commit();
            MyBatisUtils.closeSession(session);
        }
    }

    @Override
    public void deleteUser(String userNo) {
        SqlSession session = MyBatisUtils.getSession();
        session.delete("mybatis.mapper.UserMapper.deleteUser",userNo);
        session.commit();
        MyBatisUtils.closeSession(session);
    }

    @Override
    public User getUserByNo(String userNo) {
        SqlSession session = MyBatisUtils.getSession();
        User user = session.selectOne("mybatis.mapper.UserMapper.getUserByNo",userNo);
        session.commit();
        MyBatisUtils.closeSession(session);
        return user;
    }

    @Override
    public void updateUser(User user) {
        SqlSession session = MyBatisUtils.getSession();
        session.update("mybatis.mapper.UserMapper.updateUser",user);
        session.commit();
        MyBatisUtils.closeSession(session);
    }


}
