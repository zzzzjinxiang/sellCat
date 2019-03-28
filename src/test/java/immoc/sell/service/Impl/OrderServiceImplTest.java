package immoc.sell.service.Impl;

import immoc.sell.dataobjct.OrderDetail;
import immoc.sell.dto.OrderDTO;
import immoc.sell.enums.OrderStatusEnum;
import immoc.sell.enums.PayStatusEnum;
import immoc.sell.repository.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String BUYER_OPENID= "1003134";
    private final String ORDER_ID = "155280234963772052";

    @Test
    public void Create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("廖师兄");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("1324567890");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123155");
        o1.setProductQuantity(8);
        orderDetailList.add(o1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.Create(orderDTO);
        log.info("【创建订单】 result ={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result = "+result);
        assertEquals("155310384900216039",result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDTO> result = orderService.findList(BUYER_OPENID,request);
        assertNotEquals(0,result.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());

    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus() );
    }
}