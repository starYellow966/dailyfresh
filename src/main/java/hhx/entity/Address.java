package hhx.entity;

public class Address {
    private Integer addrId;
    private String addressee;
    private String address;
    private String phone;
    private Integer priority;
    private Integer userId;
    private Integer is_delete;

    @Override
    public String toString() {
        return "Address{" +
                "addrId=" + addrId +
                ", addressee='" + addressee + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", priority=" + priority +
                ", userId=" + userId +
                ", is_delete=" + is_delete +
                '}';
    }

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }
}
