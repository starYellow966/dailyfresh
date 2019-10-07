package hhx.controller.headline;

import hhx.entity.HeadLine;
import hhx.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/headline")
public class HeadLineController {
    @Autowired
    private HeadLineService headLineService;

    /**
     * 返回所有存活的headline
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAll(){
        Map<String, Object> response = new HashMap<>();
        try{
            List<HeadLine> headLineList = headLineService.queryAll();
            response.put("success", true);
            response.put("data", headLineList);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.put("success", false);
            response.put("errMsg", e.getMessage());
            return response;
        }
    }
}
