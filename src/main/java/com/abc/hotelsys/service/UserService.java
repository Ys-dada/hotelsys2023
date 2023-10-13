package com.abc.hotelsys.service;


import com.abc.hotelsys.domain.User;

import java.util.List;

public interface UserService {
    List<User> loadUsers();

    void addUser(User user);

    void deleteUser(String userNo);

    User getUserByNo(String userNo);

    User checkUser(String userNo, String userPwd);

    void updateUser(User user);


}
