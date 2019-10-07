package hhx.dao;

import hhx.BaseTest;
import hhx.entity.Address;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddressDaoTest extends BaseTest {

    @Autowired
    private AddressDao addressDao;

    @Test
    public void testQueryAddressByUserId(){
        System.out.println(addressDao.queryAddressByUserId(1));
    }

    @Test
    public void testInsertAddress(){
        Address address = new Address();
        address.setUserId(1);
        address.setAddress("测试地址");
        address.setAddressee("测试收件人");
        address.setPhone("测试手机号");

        System.out.println(address);

        int num = addressDao.insertAddress(address);
        assert (num == 1);
    }

    @Test
    public void deleteAddressByAddrId(){
        assert addressDao.deleteAddressByAddrId(0) >= 0 ;
    }

    @Test
    public void testGetAddressList(){
        System.out.println(addressDao.queryAddressByUserId(1).size());
        assert addressDao.queryAddressByUserId(1) != null;
    }
}
