package az.millisoft.tapaz.dto;

import java.util.List;

public record ProductPageResponse (
        List<ProductResponse> elements,
        long totalElements,
        int totalPages,
        boolean hasNext
){


}
