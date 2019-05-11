package cat.sell.repository;

import cat.sell.dataobjct.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID ="1003123";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12345");
        orderMaster.setBuyerName("刘三");
        orderMaster.setBuyerPhone("12345677");
        orderMaster.setBuyerOpenid("1003133");
        orderMaster.setBuyerAddress("车站");
        orderMaster.setOrderAmount(new BigDecimal(3.5));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid(){
        PageRequest request = PageRequest.of(0,1);

        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID,request);
        Assert.assertNotNull(result);
//        System.out.println(result.getTotalElements());

    }

}