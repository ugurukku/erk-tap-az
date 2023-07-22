package az.millisoft.tapaz.repository;

import az.millisoft.tapaz.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Page<Product> findProductByCategoryId(Integer categoryId, Pageable pageable);
}
