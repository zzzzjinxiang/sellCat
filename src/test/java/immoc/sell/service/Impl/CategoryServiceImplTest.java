package immoc.sell.service.Impl;

import immoc.sell.dataobjct.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(2);
        assertEquals(new Integer(2),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findCategoryTypeIn() {

        List<ProductCategory> productCategoryList = categoryService.findCategoryTypeIn(Arrays.asList(1,2,3));
        assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("test4", 4);
        ProductCategory result = categoryService.save(productCategory);
        assertNotNull(result);
    }
}