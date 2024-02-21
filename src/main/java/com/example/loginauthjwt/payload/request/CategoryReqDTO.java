package com.example.loginauthjwt.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryReqDTO {
    private String createdBy;
    private String updateBy;
    private Long status;
    private String description;
    @NotBlank
    private String name;

}
