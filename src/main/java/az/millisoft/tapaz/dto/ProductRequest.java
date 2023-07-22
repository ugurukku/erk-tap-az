package az.millisoft.tapaz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest (

        @NotBlank
        String name,

        @NotBlank
        String description,

        Double price,

        @NotNull(message = "Categoriya melumati null olmamalidir")
        CategoryRequest category
){
}
