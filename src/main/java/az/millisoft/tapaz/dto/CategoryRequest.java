package az.millisoft.tapaz.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(

        @NotNull
        Integer id
) {
}
