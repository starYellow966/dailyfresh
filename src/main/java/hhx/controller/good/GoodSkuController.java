package hhx.controller.good;

import com.alibaba.fastjson.JSONObject;
import hhx.entity.GoodSku;
import hhx.service.GoodSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sku")
public class GoodSkuController {
    @Autowired
    private GoodSkuService goodSkuService;

    /**
     * 返回商品（spu）详情页的数据
     * @return
     */
    @RequestMapping(value = "/detail/{spuid}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSkusBySpuId(@PathVariable("spuid")String spuId){
        Map<String, Object> response = new HashMap<>();
        try {
            // 1.查询
            List<GoodSku> goodSkus = goodSkuService.querySkuBySpuId(Integer.valueOf(spuId));

            // 2.修改数据格式
            for (GoodSku sku:goodSkus){
                String spec = sku.getSpec();
                JSONObject specJson = JSONObject.parseObject(spec);
                sku.setSpec(specJson.values().toString());
            }

            // 3.返回
            response.put("success", true);
            response.put("data", goodSkus);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }
}
