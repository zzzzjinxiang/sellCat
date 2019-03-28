package immoc.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import immoc.sell.dataobjct.OrderDetail;
import immoc.sell.dto.OrderDTO;
import immoc.sell.enums.ResultEnum;
import immoc.sell.exception.SellException;
import immoc.sell.form.OrderFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

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
