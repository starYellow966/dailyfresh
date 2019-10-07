package hhx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Or;
import hhx.dao.GoodSkuDao;
import hhx.dao.OrderDetailDao;
import hhx.dao.OrderGoodDao;
import hhx.dto.OrderDetailDto;
import hhx.dto.OrderGoodDto;
import hhx.entity.GoodSku;
import hhx.entity.OrderDetail;
import hhx.entity.OrderGood;
import hhx.enums.PayMethodEnum;
import hhx.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private GoodSkuDao skuDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OrderGoodDao orderGoodDao;

    @Override
    public List<OrderDetailDto> getAllOrderDetailByUserId(int userId) {
        try {
            // 验证参数
            if (userId < 0){
                throw new RuntimeException("userId有问题");
            }

            // 1.获取数据
            // 1.1获取订单数据
            List<OrderDetail> orderDetails = orderDetailDao.queryAllOrderDetailByUserId(userId);
            List<OrderDetailDto> result = new ArrayList<>(orderDetails.size());
            // 遍历每个订单
            for (OrderDetail orderDetail:orderDetails){
                OrderDetailDto detailDto = new OrderDetailDto(orderDetail);
                List<OrderGoodDto> goodDtos = new ArrayList<>(orderDetail.getOrderGoodList().size());
                // 1.2获取订单中每个sku
                for (OrderGood good:orderDetail.getOrderGoodList()){
                    OrderGoodDto goodDto = new OrderGoodDto(good);
                    GoodSku sku = skuDao.querySkuBySkuId(good.getSkuId());
                    goodDto.setSkuName(sku.getSkuName());
                    goodDto.setImgAddr(sku.getSkuImgAddr());
                    goodDtos.add(goodDto);
                }
                detailDto.setOrderGoodDtos(goodDtos);
                result.add(detailDto);
            }

            // 返回
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDetailDto getOrderDetailByOrderId(String orderId) {
        OrderDetail orderDetail = orderDetailDao.queryOrderDetailByOrderId(orderId);
        OrderDetailDto orderDetailDto = new OrderDetailDto(orderDetail);
        List<OrderGoodDto> orderGoodDtos = new ArrayList<>(orderDetail.getOrderGoodList().size());
        for (OrderGood orderGood : orderDetail.getOrderGoodList()){
            GoodSku sku = skuDao.querySkuBySkuId(orderGood.getSkuId());
            OrderGoodDto orderGoodDto = new OrderGoodDto();
            orderGoodDto.setSkuId(orderGood.getSkuId());
            orderGoodDto.setSkuName(sku.getSkuName());
            orderGoodDto.setSpec(sku.getSpec());
            orderGoodDto.setPrice(sku.getPrice());
            orderGoodDto.setCount(orderGood.getNum());
            orderGoodDto.setImgAddr(sku.getSkuImgAddr());
            orderGoodDto.setOrderId(orderDetail.getOrderId());
            orderGoodDtos.add(orderGoodDto);
        }
        orderDetailDto.setOrderGoodDtos(orderGoodDtos);
        return orderDetailDto;
    }

    /**
     * 当点击结算时，将选中的购物车信息临时存放到redis中
     * @param userId
     * @param data
     */
    @Override
    public String addTempCart(int userId, String data) {
        String key = userId + "_" + new Date().getTime() + "_";
        redisTemplate.opsForValue().set(key, data);
        return key;
    }

    /**
     * 从redis取出之前的临时数据，同时返回完整的数据
     * @param key
     * @return
     */
    public List<OrderGoodDto> getTempCart(String key){
        // 1.取出临时数据
        String jsonStr = redisTemplate.opsForValue().get(key);
//        redisTemplate.delete(key);

        // 2.解析并查询
        List<OrderGoodDto> goodDtos = new LinkedList<>();
        try{
            List<HashMap> params = JSONObject.parseArray(jsonStr, HashMap.class);
            for (HashMap param: params){
                Integer skuId = Integer.valueOf(param.get("skuId") + "");
                GoodSku sku = skuDao.querySkuBySkuId(skuId);
                OrderGoodDto good = new OrderGoodDto();
                good.setSkuId(skuId);
                good.setImgAddr(sku.getSkuImgAddr());
                good.setSkuName(sku.getSkuName());
                good.setCount(Integer.valueOf(param.get("count") + ""));
                good.setPrice(sku.getPrice());
                good.setSpec(sku.getSpec());
                goodDtos.add(good);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return goodDtos;
    }

    /**
     * 提交订单
     */
    @Override
    @Transactional
    public void commitOrder(int addrId, int userId, int payMethodCode, String[] skus, String[] counts, String[] prices){
        try{
            // 1. 生成订单（当前版本先不做是否支付的判断了）
            OrderDetail orderDetail = OrderDetail.createOrder(addrId, userId, payMethodCode, 10, 0,0, 1);
            double totalPrice = 0.0;
            int totalCount = 0;
            List<OrderGood> goodList = new ArrayList<>(skus.length);
            for (int i=0; i<skus.length; i++){
                int sku = Integer.valueOf(skus[i]);
                int count = Integer.valueOf(counts[i]);
                double price = Double.valueOf(prices[i]);
                totalCount += count;
                totalPrice += price;
                OrderGood orderGood = OrderGood.createGood(orderDetail.getOrderId(), sku, count, price, "");
                goodList.add(orderGood);
            }
            orderDetail.setOrderGoodList(goodList);
            orderDetail.setTotalCount(totalCount);
            orderDetail.setTotalAmount(totalPrice);

            // 2.mysql插入一条orderdetail和多条ordergood
            orderDetailDao.insertOrderDetail(orderDetail);
            for (OrderGood orderGood: orderDetail.getOrderGoodList())
                orderGoodDao.insertOrderGood(orderGood);

            // 3.删除购物车记录
            for (String skuId: skus)
                redisTemplate.opsForHash().delete("cart_"+userId, skuId);

            // 4.插入redis支付时限(当前版本不做)

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
