package com.abc.hotelsys.dao;

import com.abc.hotelsys.domain.Room;
import com.abc.hotelsys.domain.User;

import java.util.List;

public interface UserDao {
    List<User> loadUsers();

    void addUser(User user);

    void deleteUser(String userNo);

    User getUserByNo(String userNo);

    void updateUser(User user);
}
