package com.nexign.lottery.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Andrey Shtunder
 */
@Data
@Builder
public class ParticipantDto {

    private String name;

    private int age;

    private String city;

}
