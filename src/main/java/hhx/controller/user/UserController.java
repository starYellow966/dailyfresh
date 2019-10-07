package hhx.controller.user;

import hhx.entity.User;
import hhx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 检查用户名和密码是否正确
     * @param session
     * @param user
     * @return Map -- success 字段和 errMsg 字段
     */
    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkUser(HttpSession session, @RequestBody User user){
        Map<String, Object> response = new HashMap<>();

        try {
            // 1.获取请求参数
//            System.out.println(user);
            if (user.getUserName() == null || user.getPwd() == null ||
                    "".equals(user.getUserName()) || "".equals(user.getPwd())
            ){
                response.put("success", false);
                response.put("errMsg", "请填写完整的信息");
                return response;
            }

            // 2.检查
            User result = userService.checkUser(user.getUserName(), user.getPwd());

            // 3.加入到session并返回结果
            if (result != null){
                if (result.getIsActive() == 0){
                    response.put("success", false);
                    response.put("errMsg", "当前账户未激活，请到邮箱查收激活邮件");
                    return response;
                }else {
                    session.setAttribute("curuser", result);
                    response.put("success", true);
                    return response;
                }
            }else {
                response.put("success", false);
                response.put("errMsg", "用户名与密码错误");
                return response;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    /**
     * 注册一个用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerUser(@RequestBody User user){
        Map<String, Object> response = new HashMap<>();

        try {
            // 1.设置一些非显示的属性
            user.setIsActive(0);  // 未激活
            user.setCreateTime(new Date());
            user.setLastEditTime(new Date());

            // 2.检查
            if (user.getUserName() == null || "".equals(user.getUserName()) ||
                user.getEmail() == null || "".equals(user.getEmail()) ||
                user.getPwd() == null || "".equals(user.getPwd()) ){
                response.put("success", false);
                response.put("errMsg", "请填写完整信息");
                return response;
            }else {
                // 3.插入
                int effectNum = userService.addUser(user);
                if (effectNum == 1){
                    response.put("success",true);

                    // TODO:异步发送激活邮件

                    return response;
                }else {
                    response.put("success", false);
                    response.put("errMsg", "新增用户未知错误");
                    return response;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    @RequestMapping(value = "/curuser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCurUser(HttpSession session){
        Map<String, Object> response = new HashMap<>();
        try{
            if (session.getAttribute("curuser") == null){
                response.put("success", true);
//                response.put("errMsg", "用户未登录");
                return response;
            }else {
                User sessionUser = (User) session.getAttribute("curuser");
                User curUser = userService.queryUserById(sessionUser.getUserId());
                User returnUser = null;

                // 保证返回的curuser是最新的
                if (sessionUser.getLastEditTime() != null && curUser.getLastEditTime().before(sessionUser.getLastEditTime())){ // session中的user是最新的
                    returnUser = sessionUser;
                }else {  // session中的user过时了
                    returnUser = curUser;
                    session.setAttribute("curuser", curUser);  // 更新session
                }

                returnUser.setPwd("");  // 不返回密码给前端
                response.put("success", true);
                response.put("user", returnUser);
                return response;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    /**
     * 修改当前用户的某一项
     * @param
     * @return
     */
    @RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUserInfo(@RequestBody Map<String, Object> params,
                                              HttpSession session){
        Map<String, Object> response = new HashMap<>();
        try{
            // 0.获取参数
            String field = (String) params.get("field");
            String value = (String) params.get("value");

            // 1.组装待修改的user对象
            User updateUser = new User();
            User curUser = (User)session.getAttribute("curuser");
//            updateUser.setUserId(2);
            updateUser.setUserId(curUser.getUserId());  // 获取当前登录用户的身份
            updateUser.setLastEditTime(new Date());  // 更新修改时间
            switch (field){
                case "name":
                    updateUser.setUserName(value);
                    break;
                case "email":
                    updateUser.setEmail(value);
                    break;
                case "phone":
                    updateUser.setPhone(value);
                    break;
                case "status":
                    updateUser.setUserStatus(Integer.valueOf(value));
                    break;
                case "active":
                    updateUser.setIsActive(Integer.valueOf(value));
                    break;
                default:
                    throw new RuntimeException("前端传递的参数key有误");
            }

            // 2.更新数据库
            User newUser = userService.updateAndReturnUser(updateUser);
            newUser.setPwd(null);

            // 3.更新session
            session.setAttribute("curuser", newUser);

            response.put("success", true);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    /**
     * 注销，并跳转到首页
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute("curuser");
//        System.out.println("zhuxiaole");
        return "redirect:/";
    }
}
