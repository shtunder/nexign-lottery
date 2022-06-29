package com.nexign.lottery.converter;

import com.nexign.lottery.dto.ParticipantDto;
import com.nexign.lottery.model.ParticipantEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Shtunder
 */
@Service
public class ParticipantDtoToEntityConverter implements Converter<ParticipantDto, ParticipantEntity> {

    @Override
    public ParticipantEntity convert(ParticipantDto participantDto) {
        return ParticipantEntity.builder()
                .name(participantDto.getName())
                .age(participantDto.getAge())
                .city(participantDto.getCity())
                .build();
    }

}
