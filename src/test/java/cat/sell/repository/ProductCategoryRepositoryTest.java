package cat.sell.repository;

import cat.sell.dataobjct.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findByCategoryTypeInTest()
    {
        List<Integer> list = Arrays.asList(1,2);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list); //productcategory 中需要添加无参的构造对象
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    @Transactional //回滚数据，使得测试数据不在数据库中
    public void saveTest(){
        ProductCategory productCategory= new ProductCategory("testName",3);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findById(2).orElse(null);
        //repository.findById(productCategory.getCategoryId()).get(); 获取类，null时返回空指针
        productCategory.setCategoryType(2);
        repository.save(productCategory);
    }

}