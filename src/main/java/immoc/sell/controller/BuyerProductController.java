package immoc.sell.controller;

import immoc.sell.VO.ProductInfoVO;
import immoc.sell.VO.ProductVO;
import immoc.sell.VO.ResultVO;
import immoc.sell.dataobjct.ProductCategory;
import immoc.sell.dataobjct.ProductInfo;
import immoc.sell.service.CategoryService;
import immoc.sell.service.ProductInfoService;
import immoc.sell.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询在架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2.类目查询
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for(ProductInfo productInfo:productInfoList){
//            categoryList.add(productInfo.getCategoryType());
//        }
        //lambda表达式
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findCategoryTypeIn(categoryTypeList);
        //3.查询所有商品
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);

        //4.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

/*      测试代码
        resultVO.setCode(0);;
        resultVO.setMsg("成功");
        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();
       productInfoVO.setProductId("123144");
        productInfoVO.setProductName("饼1");
        productInfoVO.setProductPrice(new BigDecimal(4));
        productInfoVO.setProductDescription("ok");
        productInfoVO.setProductIcon("sssss");
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        resultVO.setData(Arrays.asList(productVO));*/



        return ResultUtil.success(productVOList);
    }
}
