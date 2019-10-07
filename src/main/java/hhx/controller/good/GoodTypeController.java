package hhx.controller.good;

import hhx.entity.GoodType;
import hhx.service.GoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goodtype")
public class GoodTypeController {

    @Autowired
    private GoodTypeService goodTypeService;

    /**
     * 获取所有商品类别
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listAllType(){
        Map<String, Object> response = new HashMap<>();
        try {
            List<GoodType> typeList = goodTypeService.queryAllType();
            response.put("success", true);
            response.put("data", typeList);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }


}
