package com.abc.hotelsys.service;


import com.abc.hotelsys.dao.RoomDao;
import com.abc.hotelsys.dao.RoomDaoMyBatisImpl;
import com.abc.hotelsys.dao.UserDao;
import com.abc.hotelsys.dao.UserDaoMyBatisImpl;
import com.abc.hotelsys.domain.User;
import com.abc.hotelsys.exception.InvalidLoginException;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> loadUsers() {
        UserDao userDao =new UserDaoMyBatisImpl();
        return userDao.loadUsers();
    }

    @Override
    public void addUser(User user) {
        UserDao userDao=new UserDaoMyBatisImpl();
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(String userNo) {
        UserDao userDao=new UserDaoMyBatisImpl();
        userDao.deleteUser(userNo);
    }

    @Override
    public User getUserByNo(String userNo) {
        UserDao userDao=new UserDaoMyBatisImpl();
        return userDao.getUserByNo(userNo);
    }
    public User checkUser(String userNo, String userPwd) {

        UserDao userDao = new UserDaoMyBatisImpl();
        User user = userDao.getUserByNo(userNo);

        if(user==null)
            throw new InvalidLoginException("无效账号，请检查!");
        else if(!user.getUserPwd().equals(userPwd))
            throw new InvalidLoginException("账号存在，密码不正确，请检查!");
        return user;
    }

    @Override
    public void updateUser(User user) {
        UserDao userDao=new UserDaoMyBatisImpl();
        userDao.updateUser(user);
    }



}
