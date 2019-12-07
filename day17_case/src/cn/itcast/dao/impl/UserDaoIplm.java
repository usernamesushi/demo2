package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoIplm implements UserDao {

    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());



    @Override
    public User findByUsernameAndPassword(String username, String password) {
        //jdbc操作数据库
        try {
            String sql="select * from user where username=? and password=?";
            User user = template.queryForObject(sql,new  BeanPropertyRowMapper<User>(User.class),username,password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
