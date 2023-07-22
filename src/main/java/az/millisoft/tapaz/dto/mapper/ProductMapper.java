package az.millisoft.tapaz.dto.mapper;

import az.millisoft.tapaz.dto.ProductRequest;
import az.millisoft.tapaz.dto.ProductResponse;
import az.millisoft.tapaz.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);

}
