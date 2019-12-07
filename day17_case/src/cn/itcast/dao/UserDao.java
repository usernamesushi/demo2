package cn.itcast.dao;

import cn.itcast.domain.User;

public interface UserDao {
    User findByUsernameAndPassword(String username, String password);
}
