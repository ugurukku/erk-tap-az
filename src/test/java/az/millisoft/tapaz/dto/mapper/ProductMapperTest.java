package az.millisoft.tapaz.dto.mapper;

import az.millisoft.tapaz.dto.CategoryRequest;
import az.millisoft.tapaz.dto.ProductRequest;
import az.millisoft.tapaz.dto.ProductResponse;
import az.millisoft.tapaz.entity.Category;
import az.millisoft.tapaz.entity.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void toProduct() {

        String NAME = "NAME1";
        String DESCRIPTION = "DESCRIPTION1";
        Double PRICE = Double.MAX_VALUE;
        Integer CATEGORY_ID = Integer.MAX_VALUE;

        ProductRequest productRequest = new ProductRequest(
                NAME,
                DESCRIPTION,
                PRICE,
                new CategoryRequest(CATEGORY_ID)
        );


        Product expected = Product
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .price(PRICE)
                .category(new Category(CATEGORY_ID,null))
                .build();

        Product actual = productMapper.toProduct(productRequest);

        assertEquals(expected,actual);
    }

    @Test
    void toProductResponse() {

        Integer ID = Integer.MIN_VALUE;
        String NAME = "NAME1";
        String DESCRIPTION = "DESCRIPTION1";
        Double PRICE = Double.MAX_VALUE;
        Integer CATEGORY_ID = Integer.MAX_VALUE;

        Product product = Product
                .builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .price(PRICE)
                .category(new Category(CATEGORY_ID,null))
                .addedDate(LocalDate.now())
                .build();

        ProductResponse expected = new ProductResponse(ID,PRICE,NAME);

        ProductResponse actual = productMapper.toProductResponse(product);

        assertEquals(expected,actual);

    }
}