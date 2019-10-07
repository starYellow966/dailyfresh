package hhx.controller.good;

import hhx.entity.GoodSPU;
import hhx.entity.GoodType;
import hhx.service.GoodSpuService;
import hhx.service.GoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/spu")
public class GoodSpuController {
    @Autowired
    private GoodSpuService goodSpuService;
    @Autowired
    private GoodTypeService goodTypeService;

    /**
     * 首页中会显示所有品类的前n个spu
     * @return
     */
    @RequestMapping(value = "/{num}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTopNSpu(@PathVariable("num")int n){
        Map<String, Object> response = new HashMap<>();
        try{
            Map<Object, Object> data = goodSpuService.queryTopNSpu(n);
            response.put("success", true);
            response.put("data", data);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    /**
     * 查询符合某种搜索条件的所有spu
     * 对应搜索结果列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSpuByTypeId(HttpServletRequest request){
        Map<String, Object> response = new HashMap<>();
        try{
            // 1.获取搜索条件
            String q = request.getParameter("q");
            if (q != null)
                q = new String(q.getBytes("ISO-8859-1"), "UTF-8");  // 中文转换

            // 2.分解搜索条件
            String[] conds = q.split(" "); // 查询框输入有空格，代表“或”

            // 3.执行搜索
            List<GoodSPU> goodSPUList = new LinkedList<>();
            if (conds.length > 0){
                GoodSPU goodSPU = new GoodSPU();
                for (String c : conds){  // 遍历每个搜索条件
                    goodSPU.setSpuName(c);
                    // 从spu表中查
                    goodSPUList.addAll(goodSpuService.querySpu(goodSPU));
                    // 从type表中查，然后从spu表找同类型的
                    GoodType goodType = goodTypeService.queryTypeByName(c);
                    if (goodType != null){
                        goodSPU.setGoodType(goodType);
                        goodSPU.setSpuName("");
                        goodSPUList.addAll(goodSpuService.querySpu(goodSPU));
                    }
                }
            }

            response.put("success", true);
            response.put("list", goodSPUList);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

    @RequestMapping("/new")
    @ResponseBody
    public Map<String, Object> listNewSpu(){
        Map<String, Object> response = new HashMap<>();
        try{

            response.put("success", true);
            response.put("list", goodSpuService.queryNewNSpu(3));
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }

}
