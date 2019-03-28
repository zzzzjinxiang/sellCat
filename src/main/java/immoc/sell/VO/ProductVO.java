package immoc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import immoc.sell.dataobjct.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("food")
    private List<ProductInfoVO> productInfoVOList;
}
