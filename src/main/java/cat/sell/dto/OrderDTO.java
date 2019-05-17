package cat.sell.dto;

import cat.sell.dataobjct.OrderDetail;
import cat.sell.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;
    //private String orderId; = "";添加初始值以强制返回null

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTimes;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTimes;

    //@Transient 加入后可在数据库中忽略
    private List<OrderDetail> orderDetailList;

}
