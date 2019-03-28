package immoc.sell.service.Impl;

import immoc.sell.dataobjct.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("123133");
        Assert.assertEquals("12312",productInfo.getProductId());

    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage=productInfoService.findAll(request);
//        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage);

    }

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12345");
        productInfo.setProductName("面");
        productInfo.setProductPrice(new BigDecimal(1));
        productInfo.setProductDescription("ok");
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("sssss1");
        productInfo.setProductStock(100);
        productInfo.setCategoryType(1);

        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }
}