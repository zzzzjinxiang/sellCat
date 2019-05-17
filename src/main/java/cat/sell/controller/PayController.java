package cat.sell.controller;

import cat.sell.dto.OrderDTO;
import cat.sell.enums.ResultEnum;
import cat.sell.exception.SellException;
import cat.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl){

        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null)
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

    }

}
