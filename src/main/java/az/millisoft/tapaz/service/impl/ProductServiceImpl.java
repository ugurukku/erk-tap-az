package az.millisoft.tapaz.service.impl;

import az.millisoft.tapaz.dto.ProductPageResponse;
import az.millisoft.tapaz.dto.ProductRequest;
import az.millisoft.tapaz.dto.ProductResponse;
import az.millisoft.tapaz.dto.mapper.ProductMapper;
import az.millisoft.tapaz.entity.Product;
import az.millisoft.tapaz.exception.ProductNotFound;
import az.millisoft.tapaz.repository.ProductRepository;
import az.millisoft.tapaz.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final ProductMapper mapper;


    @Override
    public ProductPageResponse getAll(int page, int count) {

        Page<Product> products = repository.findAll(PageRequest.of(page, count));

        List<ProductResponse> productResponses = products
                .getContent()
                .stream()
                .map(mapper::toProductResponse)
                .toList();

        ProductPageResponse productPageResponse =
                new ProductPageResponse(
                        productResponses,
                        products.getTotalElements(),
                        products.getTotalPages(),
                        products.hasNext());

        return productPageResponse;
    }

    @Override
    public ProductPageResponse getByCategoryId(Integer categoryId, int page, int count) {
        Page<Product> productPage = repository
                .findProductByCategoryId(
                        categoryId,
                        PageRequest.of(page, count));

        List<ProductResponse> productResponses = productPage
                .getContent()
                .stream()
                .map(mapper::toProductResponse)
                .toList();

        ProductPageResponse productPageResponse =
                new ProductPageResponse(
                        productResponses,
                        productPage.getTotalElements(),
                        productPage.getTotalPages(),
                        productPage.hasNext());

        return productPageResponse;
    }

    @Override
    public Product getById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ProductNotFound("mehsul tapilmadi"));
    }

    @Override
    public void save(ProductRequest productRequest) {
        Product product = mapper.toProduct(productRequest);
        product.setAddedDate(LocalDate.now());
        repository.save(product);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Integer id, ProductRequest request) {

    }
}
