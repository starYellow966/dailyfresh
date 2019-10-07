package hhx.service;

import hhx.entity.Address;

import java.util.List;

public interface AddressService {

    public int insertAddress(Address address);
    public List<Address> queryAddressByUserId(int userId);
    public int deleteAddressByAddrId(int addrId);
}
