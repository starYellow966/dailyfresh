package hhx.controller.good;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/good")
public class GoodIndexController {

    /**
     * 商品详情页面
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detailIndex(){
        return "good/detail";
    }

    /**
     * 搜索结果列表页面
     * @return
     */
    @RequestMapping("/list")
    public String listIndex(){
        return "good/list";
    }
}
