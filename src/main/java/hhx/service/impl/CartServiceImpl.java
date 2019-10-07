package hhx.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhx.entity.Cart;
import hhx.entity.CartRecord;
import hhx.entity.GoodSku;
import hhx.service.CartService;
import hhx.service.GoodSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private GoodSkuService goodSkuService;


    /**
     * 更新购物车
     * @param cart
     * @return
     */
    @Override
    public void updateCart(final Cart cart) {
        redisTemplate.opsForHash().put("cart_"+cart.getUserId(), cart.getSkuId()+"", cart.getNum()+"");
    }

    /**
     * 添加到购物车
     * @param cart
     */
    public void addCart(Cart cart){
        // 1.先查询
        String count = (String) redisTemplate.opsForHash().get("cart_" + cart.getUserId(), cart.getSkuId() + "");
        if (count != null){
            count = String.valueOf(Integer.valueOf(count) + cart.getNum());
        }else
            count = String.valueOf(cart.getNum());

        // 2.后更新
        redisTemplate.opsForHash().put("cart_" + cart.getUserId(), cart.getSkuId()+"", count);
    }

    @Override
    public List<CartRecord> listAll(Integer userId) {
        // 1.查询redis
        Map<Object, Object> result = redisTemplate.opsForHash().entries("cart_" + userId);
        List<CartRecord> allRecord = new ArrayList<>(result.size());

        // 2.查询mysql
        for (Object key:result.keySet()){
            Integer skuId = Integer.valueOf((String)key);
            GoodSku goodSku = goodSkuService.querySkuBySkuId(skuId);
            CartRecord record = new CartRecord(goodSku, Integer.valueOf((String)result.get(key)));
//            System.out.println(record);
            allRecord.add(record);
        }

        return allRecord;
    }

    @Override
    public void deleteCart(Integer userId, Integer skuId) {
        redisTemplate.opsForHash().delete("cart_"+userId, skuId+"");
    }
}
