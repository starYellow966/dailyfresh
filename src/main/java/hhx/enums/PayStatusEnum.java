package hhx.enums;

public enum PayStatusEnum {
    TOPAY("待支付", 0), PAID("待评价", 1), FINISH("已完成", 2),
    CANCEL("取消", 3), OTHERS("其他", -1);

    private String statusName;
    private Integer statusCode;

    private PayStatusEnum(){}
    private PayStatusEnum(String statusName, Integer statusCode){
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public static PayStatusEnum indexOf(int code){
        switch (code){
            case 0:
                return PayStatusEnum.TOPAY;
            case 1:
                return PayStatusEnum.PAID;
            case 2:
                return CANCEL;
            default:
                return OTHERS;
        }
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }
}
