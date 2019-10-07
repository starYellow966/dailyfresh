package hhx.dao;

import hhx.entity.User;
import org.apache.ibatis.annotations.Param;


public interface UserDao {

    /**
     * 检查用户名和密码是否正确
     * @param username
     * @param password
     * @return
     */
    public User checkUser(@Param("username")String username, @Param("pwd")String password);

    public User queryUserById(@Param("userId")int userId);

    /**
     * 新增一个用户
     * @param user
     * @return
     */
    public int addUser(User user);

    public int updateUser(User user);
}
