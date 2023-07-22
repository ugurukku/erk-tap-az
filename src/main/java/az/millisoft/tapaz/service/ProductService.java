package az.millisoft.tapaz.service;

import az.millisoft.tapaz.dto.ProductPageResponse;
import az.millisoft.tapaz.dto.ProductRequest;
import az.millisoft.tapaz.entity.Product;

public interface ProductService {

    ProductPageResponse getAll(int page, int count);

    ProductPageResponse getByCategoryId(Integer id, int page, int count);

    Product getById(Integer id);

    void save(ProductRequest productRequest);

    void delete(Integer id);

    void update(Integer id, ProductRequest request);

}
