package hhx.service;

import hhx.BaseTest;
import hhx.entity.Address;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressServiceTest extends BaseTest {
    @Autowired
    private AddressService addressService;

    @Test
    public void testInsertAddress(){
        Address address = new Address();
        address.setUserId(1);
        address.setAddress("测试地址");
        address.setAddressee("测试收件人");
        address.setPhone("测试手机号");

        System.out.println(address);

        int num = addressService.insertAddress(address);
        assert (num == 1);
    }
}
