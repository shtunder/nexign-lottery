package com.nexign.lottery.service;

import com.nexign.lottery.dto.ParticipantDto;
import com.nexign.lottery.dto.WinnerDto;

import java.util.List;

/**
 * @author Andrey Shtunder
 */
public interface ParticipantService {

    ParticipantDto createParticipant(ParticipantDto participantDto);

    List<ParticipantDto> getAllParticipants();

    WinnerDto startLottery();

    void deleteAllParticipants();

}
