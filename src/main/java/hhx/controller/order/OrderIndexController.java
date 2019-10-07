package hhx.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order")
public class OrderIndexController {

    /**
     * 提交订单页面
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String submitOrderIndex(){
        return "order/place_order";
    }

    /**
     * 评价页面
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public String commentOrderIndex(){
        return "order/order_comment";
    }
}
