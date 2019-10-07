package hhx.service;

import hhx.BaseTest;
import hhx.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCheckUser(){
    }

    @Test
    public void testUpdateAndGetUser(){
        User user = new User();
        user.setUserId(2);
        user.setLastEditTime(new Date());
        user.setEmail("hello@11.com");
        user.setUserStatus(0);
        user.setUserName("test");

        User newUser = userService.updateAndReturnUser(user);
        System.out.println(newUser);
        assert null!=newUser;
    }
}
