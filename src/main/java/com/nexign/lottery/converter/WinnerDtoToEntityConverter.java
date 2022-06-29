package com.nexign.lottery.converter;

import com.nexign.lottery.dto.WinnerDto;
import com.nexign.lottery.model.WinnerEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Shtunder
 */
@Service
public class WinnerDtoToEntityConverter implements Converter<WinnerDto, WinnerEntity> {

    @Override
    public WinnerEntity convert(WinnerDto winnerDto) {
        return WinnerEntity.builder()
                .name(winnerDto.getName())
                .age(winnerDto.getAge())
                .city(winnerDto.getCity())
                .prize(winnerDto.getPrize())
                .build();
    }

}
