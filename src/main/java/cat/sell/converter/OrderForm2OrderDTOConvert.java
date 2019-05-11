package cat.sell.converter;

import cat.sell.dataobjct.OrderDetail;
import cat.sell.exception.SellException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cat.sell.dto.OrderDTO;
import cat.sell.enums.ResultEnum;
import cat.sell.form.OrderFrom;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTOConvert {

    public static OrderDTO convert(OrderFrom orderFrom) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderFrom.getName());
        orderDTO.setBuyerPhone(orderFrom.getPhone());
        orderDTO.setBuyerAddress(orderFrom.getAddress());
        orderDTO.setBuyerOpenid(orderFrom.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderFrom.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.error("【对象转换】  错误，String = {}",orderFrom.getItems());
            throw new SellException(ResultEnum.PARAMETER_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

}
