package cat.sell.controller;

import cat.sell.VO.ResultVO;
import cat.sell.converter.OrderForm2OrderDTOConvert;
import cat.sell.dto.OrderDTO;
import cat.sell.enums.ResultEnum;
import cat.sell.exception.SellException;
import cat.sell.form.OrderFrom;
import cat.sell.form.OrderList;
import cat.sell.service.OrderService;
import cat.sell.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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

    @PostMapping("/list")
    public ResultVO<List<OrderDTO>> orderList(@RequestParam("openid") String openid,
                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【订单查询列表】");
            throw new SellException(ResultEnum.PARAMETER_ERROR);
        }

        PageRequest request = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOS = orderService.findList(openid,request);
        return ResultUtil.success(orderDTOS.getContent());
    }

    //订单详情
    @PostMapping("/detail")
    public ResultVO<List<OrderDTO>> orderDetail(@RequestParam("openid") String openid,
                                                @RequestParam("orderId") String orderId){

        //TODO SQL注入不安全
        OrderDTO orderDTO = orderService.findOne(orderId);
        return ResultUtil.success(orderDTO);
    }


    //取消订单

}
