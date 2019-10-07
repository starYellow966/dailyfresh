package hhx.dao;

import hhx.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDao {

    public List<Address> queryAddressByUserId(@Param("userId") int userId);
    public int insertAddress(Address address);
    public int deleteAddressByAddrId(@Param("addrId")int addrId);
}
