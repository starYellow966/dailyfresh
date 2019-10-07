package hhx.enums;

public enum  PayMethodEnum {

    ALIPAY("支付宝", 0),WECHAT("微信支付",1),
    OTHERS("未知支付方式", 2);

    private String methodName;
    private Integer methodCode;

    private PayMethodEnum(String methodName, Integer methodCode){
        this.methodCode = methodCode;
        this.methodName = methodName;
    }

    public static PayMethodEnum indexOf(int code){
        switch (code){
            case 0:
                return PayMethodEnum.ALIPAY;
            case 1:
                return PayMethodEnum.WECHAT;
            default:
                return PayMethodEnum.OTHERS;
        }
    }

    public Integer getMethodCode() {
        return methodCode;
    }

}
