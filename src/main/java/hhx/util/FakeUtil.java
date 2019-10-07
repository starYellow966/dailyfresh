package hhx.util;

import hhx.entity.User;

import javax.servlet.http.HttpSession;

/**
 * 负责伪造一些属性，进行测试
 */
public class FakeUtil {

    /**
     * 向session伪造一个curuser，用于测试
     * @param session
     */
    public static void fakeSessionUser(HttpSession session){
        User user = (User) session.getAttribute("curuser");
        if (user == null){
            user = new User();
            user.setUserId(1);
            session.setAttribute("curuser", user);
        }
    }
}
