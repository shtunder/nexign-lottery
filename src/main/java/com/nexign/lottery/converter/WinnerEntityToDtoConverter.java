package com.nexign.lottery.converter;

import com.nexign.lottery.dto.WinnerDto;
import com.nexign.lottery.model.WinnerEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Shtunder
 */
@Service
public class WinnerEntityToDtoConverter implements Converter<WinnerEntity, WinnerDto> {

    @Override
    public WinnerDto convert(WinnerEntity winnerEntity) {
        return new WinnerDto(winnerEntity.getName(), winnerEntity.getAge(),
                winnerEntity.getCity(), winnerEntity.getPrize());
    }

}
