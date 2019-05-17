package cat.sell.service;

import cat.sell.dto.OrderDTO;

public interface PayService {
    void create(OrderDTO orderDTO);
}
