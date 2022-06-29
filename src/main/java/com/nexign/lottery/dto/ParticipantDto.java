package com.nexign.lottery.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Andrey Shtunder
 */
@Data
@Builder
public class ParticipantDto {

    @NotEmpty(message = "Name cannot be null or empty.")
    @Size(max = 20)
    private String name;

    @NotNull(message = "Age cannot be null.")
    @Max(150)
    @Min(0)
    private Integer age;

    @NotEmpty(message = "City cannot be null or empty.")
    @Size(max = 30)
    private String city;

}
