package cat.sell.service.Impl;

import cat.sell.dto.OrderDTO;
import cat.sell.service.OrderService;
import cat.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {


    private static final String s = "";
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    @Test
    public void create() {

        OrderDTO orderDTO = orderService.findOne(s);
        payService.create(orderDTO);
    }
}