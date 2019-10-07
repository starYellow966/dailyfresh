package hhx.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 专门负责用户模块的页面跳转Controller
 */
@Controller
@RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST})
public class UserIndexController {
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login")
    public String loginIndex(){
        return "user/login";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/reg")
    public String registerIndex(){
        return "user/register";
    }

    /**
     * 用户中心-个人信息页面
     * @return
     */
    @RequestMapping("/center")
    public String centerIndex(){
        return "user/center-info";
    }

    /**
     * 用户中心-所有订单页面
     * @return
     */
    @RequestMapping("/center/order")
    public String centerOrderIndex(){
        return "user/center-order";
    };

    /**
     * 用户中心-所有收货地址页面
     * @return
     */
    @RequestMapping("/center/address")
    public String centerAddressIndex(){
        return "user/center-addr";
    }


    /**
     * 修改某项信息页面
     * @return
     */
    @RequestMapping("/update")
    @Deprecated
    public String updateUserInfoIndex(){
        return "user/update_userInfo";
    }

    /**
     * 新增地址页面
     * @return
     */
    @RequestMapping("/addr")
    @Deprecated
    public String addAddressIndex(){
        return "user/add_address";
    }

    /**
     * 所有收货地址展示页面
     * @return
     */
    @RequestMapping("/address")
    @Deprecated
    public String addressListIndex(){
        return "user/address_list";
    }


    /**
     * 对于未登录用户，跳转到登录页面
     * 对于已登录用户，跳转到个人中心页面
     * @return
     */
    @RequestMapping("/")
    @Deprecated
    public String loginOrCenterIndex(HttpSession session){
//        System.out.println(session.getAttribute("curuser"));
        if (session.getAttribute("curuser") != null)
            return "user/center-sui";
        else
//            return "user/login";
            return "user/login";
    }
}
