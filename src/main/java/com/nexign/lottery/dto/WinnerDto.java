package com.nexign.lottery.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andrey Shtunder
 */
@Getter
@Setter
public class WinnerDto extends ParticipantDto {

    private int prize;

    public WinnerDto(String name, int age, String city, int prize) {
        super(name, age, city);
        this.prize = prize;
    }

}
