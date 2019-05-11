package cat.sell.repository;

import cat.sell.dataobjct.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> { //继承JpaRepository
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
