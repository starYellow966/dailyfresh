package hhx.service.impl;

import hhx.dao.AddressDao;
import hhx.entity.Address;
import hhx.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public int insertAddress(Address address) {
        try {
            int effectNum = addressDao.insertAddress(address);
            return effectNum;
        }catch (Exception e){
            throw new RuntimeException("新增地址的Service层异常," + e.getMessage());
        }
    }

    @Override
    public List<Address> queryAddressByUserId(int userId) {
        try{
            List<Address> addressList = addressDao.queryAddressByUserId(userId);
            return addressList;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int deleteAddressByAddrId(int addrId) {
        try{
            return addressDao.deleteAddressByAddrId(addrId);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
