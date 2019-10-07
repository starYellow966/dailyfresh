package hhx.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import hhx.dto.OrderDetailDto;
import hhx.entity.OrderDetail;
import hhx.entity.User;
import hhx.enums.PayMethodEnum;
import hhx.service.OrderDetailService;
import hhx.util.FakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 对应购物车中去结算按钮
     * 工作内容：将其存放到 redis 中，并设置30min的过期时间
     * @return
     */
    @RequestMapping(value = "/settle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> commitOrder(HttpServletRequest request, @RequestBody String jsonString){
        Map<String, Object> response = new HashMap<>();
        try{
            FakeUtil.fakeSessionUser(request.getSession()); // 测试

            // 1.获取参数
            User curUser = (User) request.getSession().getAttribute("curuser");
//            System.out.println(jsonString);

            // 2.将其临时存在redis中
            String key = orderDetailService.addTempCart(curUser.getUserId(), jsonString);

            // 3.转发到支付页面
            response.put("success", true);
            response.put("data", "/order/?k=" + key);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    /**
     * 从redis中取出临时数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/getsettle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSettle(HttpServletRequest request, @RequestBody String param){
        Map<String, Object> response = new HashMap<>();
        try{
            // 1.获取参数
            String key = JSONObject.parseObject(param).getString("key");
//            System.out.println("72:" + key);

            // 2.查询并返回
            response.put("success", true);
            response.put("data", orderDetailService.getTempCart(key));
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllOrderByUserId(HttpSession session){
        Map<String, Object> response = new HashMap<>();
        try{
            FakeUtil.fakeSessionUser(session);  // 测试

            // 1.获取当前登录用户
            User curUser = (User) session.getAttribute("curuser");

            List<OrderDetailDto> orderDetail = orderDetailService.getAllOrderDetailByUserId(curUser.getUserId());

            response.put("success", true);
            response.put("data", orderDetail);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    /**
     * 确定购买
     * @param session
     * @param jsonString
     * @return
     */
    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> commitOrder(HttpSession session, @RequestBody String jsonString){
        Map<String, Object> response = new HashMap<>();
        try{
            // 1. 获取并解析参数
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            Integer addrId = jsonObject.getInteger("addr_id");
            Integer payMethodCode = jsonObject.getInteger("pay_method");
            String[] skuIds = jsonObject.getString("sku_ids").split(",");
            String[] counts = jsonObject.getString("counts").split(",");
            String[] prices = jsonObject.getString("prices").split(",");
            User curUser = (User) session.getAttribute("curuser");

            // 1*验证
            if (!(skuIds.length == counts.length && skuIds.length == prices.length))
                throw new RuntimeException("传参有误");

            // 2.数据库操作
            orderDetailService.commitOrder(addrId, curUser.getUserId(), payMethodCode, skuIds, counts, prices);

            // 3.返回
            response.put("success", true);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    @RequestMapping(value = "/order")
    @ResponseBody
    public Map<String, Object> getOrderByOrderId(HttpSession session, @RequestBody String jsonString){
        Map<String, Object> response = new HashMap<>();
        try{
            // 1.获取参数
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            String orderId = jsonObject.getString("orderid");

            // 2.数据库查询
            OrderDetailDto orderDetailDto = orderDetailService.getOrderDetailByOrderId(orderId);

            // 3.返回
            response.put("success", true);
            response.put("data", orderDetailDto);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }
}
