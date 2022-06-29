package com.nexign.lottery.converter;

import com.nexign.lottery.dto.ParticipantDto;
import com.nexign.lottery.model.ParticipantEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Shtunder
 */
@Service
public class ParticipantEntityToDtoConverter implements Converter<ParticipantEntity, ParticipantDto> {

    @Override
    public ParticipantDto convert(ParticipantEntity participantEntity) {
        return ParticipantDto.builder()
                .name(participantEntity.getName())
                .age(participantEntity.getAge())
                .city(participantEntity.getCity())
                .build();
    }

}
