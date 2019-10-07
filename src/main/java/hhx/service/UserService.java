package hhx.service;

import hhx.entity.User;

public interface UserService {
    public User checkUser(String username, String password);
    public User queryUserById(int userId);
    public int addUser(User user);
    public User updateAndReturnUser(User user); // 更新完后返回新的user
}
