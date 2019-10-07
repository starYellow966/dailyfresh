package hhx.controller.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hhx.entity.Comment;
import hhx.entity.User;
import hhx.service.CommentService;
import hhx.util.FakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommendController {
    @Autowired
    private CommentService commentService;

    /**
     * 插入评论
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addComment(HttpServletRequest request,
                                          @RequestBody String jsonString){
        Map<String, Object> response = new HashMap<>();
        try{
            // 0.测试
            FakeUtil.fakeSessionUser(request.getSession());

            // 1.接收并解析参数
            JSONObject params = JSONObject.parseObject(jsonString);
            String orderId = params.getString("orderId");
            JSONArray skuIds = params.getJSONArray("skuIds");
            JSONArray comments = params.getJSONArray("comments");
            User curUser = (User) request.getSession().getAttribute("curuser");

            // 2.验证参数
            if (orderId == null || skuIds == null || comments == null)
                throw new RuntimeException("数据不完整，请勿改动源代码");
            if (skuIds.size() != comments.size())
                throw new RuntimeException("数据有误，请勿改动源代码");

            // 3.数据库操作
            for (int i=0 ; i< skuIds.size() ; i++){
                Comment comment = new Comment();
                comment.setUserId(curUser.getUserId());
                comment.setOrderId(orderId);
                comment.setSkuId(skuIds.getInteger(i));
                comment.setComment(comments.getString(i));
                commentService.insertComment(comment);
            }

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
