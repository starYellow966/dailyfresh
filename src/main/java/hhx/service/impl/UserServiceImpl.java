package hhx.service.impl;

import hhx.dao.UserDao;
import hhx.entity.User;
import hhx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 检查用户名和密码是否正确
     * @param username
     * @param password
     * @return 用户名密码正确返回User，其余情况返回null
     */
    @Override
    public User checkUser(String username, String password) {
        try{
            return userDao.checkUser(username, password);
        }catch (Exception e){
            throw new RuntimeException("检查用户名和密码的Service层异常" + e.getMessage());
        }
    }

    @Override
    public User queryUserById(int userId) {
        try {
            return userDao.queryUserById(userId);
        }catch (Exception e){
            throw new RuntimeException("Service.queryUserById异常," + e.getMessage());
        }
    }

    /**
     * 新增一个用户
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {
        try {
            return userDao.addUser(user);
        }catch (Exception e){
            throw new RuntimeException("新增用户的Service层异常" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public User updateAndReturnUser(User user) {
        try{
            // 1.更新
            int effectNum = userDao.updateUser(user);
            if (effectNum != 1){
                throw new RuntimeException("更新影响值不为1");
            }

            // 2.返回最新值
            User retUser = userDao.queryUserById(user.getUserId());  // 返回最细的user
            return retUser;
        }catch (Exception e){
            throw new RuntimeException("service.updateAndReturnUser异常，" + e.getMessage());
        }
    }
}
