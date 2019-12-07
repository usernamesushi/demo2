package cn.itcast.service.ipml;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoIplm;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

public class UserServiceIplm implements UserService {

    private UserDao dao=new UserDaoIplm();

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
