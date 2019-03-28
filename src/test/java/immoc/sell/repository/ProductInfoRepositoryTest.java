package immoc.sell.repository;

import immoc.sell.dataobjct.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123133");
        productInfo.setProductName("饼1");
        productInfo.setProductPrice(new BigDecimal(14));
        productInfo.setProductDescription("ok");
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("sssss");
        productInfo.setProductStock(30);
        productInfo.setCategoryType(3);

        ProductInfo results = repository.save(productInfo);
        Assert.assertNotNull(results);
    }
    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotNull(productInfoList);
    }
}