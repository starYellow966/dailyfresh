package hhx.controller.user;

import hhx.entity.Address;
import hhx.entity.User;
import hhx.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addAddress(HttpSession session, @RequestBody Address address){
        Map<String, Object> response = new HashMap<>();
        try {
            // 1.验证传参，并增加userId
            if (session.getAttribute("curuser") != null){
                User curUser = (User)session.getAttribute("curuser");
                address.setUserId(curUser.getUserId());
            }
//            address.setUserId(1);

            // 2.插入数据库
            int effectNum = addressService.insertAddress(address);
            if (effectNum == 1){
                response.put("success", true);
                return response;
            }else {
                response.put("success", false);
                response.put("errMsg", "新增地址失败");
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
     * 返回当前登录用户的所有收货地址
     * @param session
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAddressList(HttpSession session){
        Map<String, Object> response = new HashMap<>();
        try{
            User curUser = (User) session.getAttribute("curuser");
//            if (curUser == null)
//                throw new RuntimeException("请先登录");
            List<Address> addressList = addressService.queryAddressByUserId(curUser.getUserId());
            response.put("success", true);
            response.put("list", addressList);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    /**
     * 删除某个地址
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteAddressByAddrId(HttpServletRequest request){
        Map<String, Object> response = new HashMap<>();

        try {
            Integer addrId = Integer.valueOf(request.getParameter("addrid"));
            if (addrId == null)
                throw new RuntimeException("缺少addrId参数");
            int effectNum = addressService.deleteAddressByAddrId(addrId);
            response.put("success", true);
            return response;
        }catch (Exception e){
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

}
