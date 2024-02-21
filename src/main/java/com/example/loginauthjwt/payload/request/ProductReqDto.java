package com.example.loginauthjwt.payload.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductReqDto {
    private String createdBy;
    private String updateBy;
    private Long status;
    @NotNull(message = "Field cannot be null")
    @Min(value = 0, message = "Value must be greater than or equal to 0")
    @Max(value = Long.MAX_VALUE, message = "Value must be less than or equal to " + Long.MAX_VALUE)
    private Long categoryId;
    private String description;
    @NotBlank
    private String name;
    @NotNull(message = "Field cannot be null")
    @Min(value = 0, message = "Value must be greater than or equal to 0")
    @Max(value = Long.MAX_VALUE, message = "Value must be less than or equal to " + Long.MAX_VALUE)
    private Long price;
}
