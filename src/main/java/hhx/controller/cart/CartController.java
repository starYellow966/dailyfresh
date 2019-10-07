package hhx.controller.cart;

import com.alibaba.fastjson.JSONObject;
import hhx.entity.Cart;
import hhx.entity.CartRecord;
import hhx.entity.GoodSku;
import hhx.entity.User;
import hhx.service.CartService;
import hhx.util.FakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 负责购物车模块的业务处理
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 添加购物车记录[用于商品详情页]
     * 与update不同，这里需要再原先的基础上增加。
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCart(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        Map<String, Object> response = new HashMap<>();
        try{
            // 测试
            FakeUtil.fakeSessionUser(request.getSession());

            // 1.获取参数
            User curUser = (User) request.getSession().getAttribute("curuser");
            Integer skuId = Integer.valueOf(jsonObject.getString("skuid"));
            Integer count = Integer.valueOf(jsonObject.getString("count"));

            // 2.验证数据并封装
            if (curUser == null || skuId == null || count == null)
                throw new RuntimeException("信息不完整");
            Cart cart = new Cart(curUser.getUserId(), skuId, count);
//            // 3.执行数据库操作
            cartService.addCart(cart);

            // 4.返回
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
     * 更新某项购物车记录[用于购物车页面]
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCart(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        Map<String, Object> response = new HashMap<>();
        try{
            // 测试
            FakeUtil.fakeSessionUser(request.getSession());

            // 1.获取参数
            User curUser = (User) request.getSession().getAttribute("curuser");
            Integer skuId = Integer.valueOf(jsonObject.getString("skuid"));
            Integer count = Integer.valueOf(jsonObject.getString("count"));

            // 2.验证数据并封装
            if (curUser == null || skuId == null || count == null)
                throw new RuntimeException("信息不完整");
            Cart cart = new Cart(curUser.getUserId(), skuId, count);
//
//            // 3.执行数据库操作
            cartService.updateCart(cart);

            // 4.返回
            response.put("success", true);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAllCartByUser(HttpSession session){
        Map<String, Object> response = new HashMap<>();
        try{
            // 测试
            FakeUtil.fakeSessionUser(session);

            // 1.获取登录用户信息
            User curUser = (User) session.getAttribute("curuser");

            if (curUser == null)
                throw new RuntimeException("请先登录"); // 以防万一

            List<CartRecord> allRecord = cartService.listAll(curUser.getUserId());

            // 2.修改数据格式
            for (CartRecord record:allRecord){
                GoodSku sku = record.getGoodSku();
                String spec = sku.getSpec();
                JSONObject specJson = JSONObject.parseObject(spec);
                sku.setSpec(specJson.values().toString());
            }

            response.put("success", true);
            response.put("data", allRecord);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCart(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        Map<String, Object> response = new HashMap<>();

        try{
            // 测试
            FakeUtil.fakeSessionUser(request.getSession());

            // 1.获取参数
            User curUser = (User) request.getSession().getAttribute("curuser");
            String skuId = jsonObject.getString("skuid");

            System.out.println(curUser + "," + skuId);

            // 2.校验数据
            if (curUser == null || skuId == null){
                response.put("success", false);
                response.put("errMsg", "数据不完整");
                return response;
            }

            // 3.更新数据库
            cartService.deleteCart(curUser.getUserId(), Integer.valueOf(skuId));

            // 4.返回
            response.put("success", true);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

}
