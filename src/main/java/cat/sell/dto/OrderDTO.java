package cat.sell.dto;

import cat.sell.dataobjct.OrderDetail;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTimes;

    private Date updateTimes;

    //@Transient 加入后可在数据库中忽略
    private List<OrderDetail> orderDetailList;

}
