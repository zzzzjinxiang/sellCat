package cat.sell.repository;

import cat.sell.dataobjct.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123128");
        orderDetail.setOrderId("11010133");
        orderDetail.setProductIcon("http://xxxx/jpg");
        orderDetail.setProductId("123125");
        orderDetail.setProductName("饼2");
        orderDetail.setProductPrice(new BigDecimal(20));
        orderDetail.setProductQuantity(20);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);

    }
    @Test
    public void findByOrderId(){

        List<OrderDetail> orderDetailList = repository.findByOrderId("11010133");
        assertNotEquals(0,orderDetailList.size());
    }

}