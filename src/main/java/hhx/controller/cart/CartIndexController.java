package hhx.controller.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 负责购物车模块的页面跳转
 */
@Controller
@RequestMapping("/cart")
public class CartIndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "cart/cart";
    }

}
