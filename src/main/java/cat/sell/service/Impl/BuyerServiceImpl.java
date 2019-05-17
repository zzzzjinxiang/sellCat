package cat.sell.service.Impl;

import cat.sell.dto.OrderDTO;
import cat.sell.service.BuyerService;
import cat.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return checkOrderOwner(openId,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openId,orderId);
        if(orderDTO == null){
            log.error("【查询订单】订单不存在，错误.openId={}",openId);
            throw new IllegalArgumentException("error param");
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openId, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null)
            return null;
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)){
            log.error("【查询订单】订单openId不一致，错误.openId={}",openId);
            throw new IllegalArgumentException("error param");
        }
        return orderDTO;
    }
}
