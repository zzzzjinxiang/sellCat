package cat.sell.controller;

import cat.sell.VO.ResultVO;
import cat.sell.converter.OrderForm2OrderDTOConvert;
import cat.sell.dto.OrderDTO;
import cat.sell.enums.ResultEnum;
import cat.sell.exception.SellException;
import cat.sell.form.OrderFrom;
import cat.sell.service.OrderService;
import cat.sell.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String ,String>> create(@Valid OrderFrom orderFrom,
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确, orderForm ={}",orderFrom);
            throw new SellException(ResultEnum.PARAMETER_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(orderFrom);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.Create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultUtil.success(map);

    }

    //订单列表

    //订单详情

    //取消订单

}
