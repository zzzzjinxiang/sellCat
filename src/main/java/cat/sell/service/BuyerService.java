package cat.sell.service;

import cat.sell.dto.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOne(String openId,String orderId);
    OrderDTO cancelOrder(String openId,String orderId);
}
