package hhx.service;

import hhx.BaseTest;
import hhx.entity.Cart;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CartServiceTest extends BaseTest {
    @Autowired
    private CartService cartService;

    @Test
    public void testUpdateCart(){
        Cart cart = new Cart(2,1,-1);
        cartService.updateCart(cart);
    }
}
