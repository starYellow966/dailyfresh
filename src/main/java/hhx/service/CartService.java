package hhx.service;

import hhx.entity.Cart;
import hhx.entity.CartRecord;

import java.util.List;

public interface CartService {
    /**
     * 插入或更新一条购物车记录
     * @param cart
     */
    public void updateCart(Cart cart);

    public void addCart(Cart cart);

    /**
     * 获取某个用户所有购物车记录
     */
    public List<CartRecord> listAll(Integer userId);

    public void deleteCart(Integer userId, Integer skuId);
}
