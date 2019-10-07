package hhx.dao;

import hhx.BaseTest;
import hhx.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testCheckUser(){
        User result = userDao.checkUser("admin", "1235");
        System.out.println(result);
        assert (result== null);
    }

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUserName("Test");
        user.setPwd("123");
        user.setCreateTime(new Date());
        user.setLastEditTime(new Date());
        user.setEmail("123@qq.com");

        int num = userDao.addUser(user);
        assert (num == 1);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUserId(2);
        user.setLastEditTime(new Date());
        user.setEmail("hello@11.com");
        user.setUserStatus(1);
        user.setUserName("test");

        System.out.println(user);

        assert 1==userDao.updateUser(user);
    }

}
