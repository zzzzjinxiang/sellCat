package cat.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderList {

    @NotEmpty(message = "订单必填")
    private String orderId;

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "电话必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
