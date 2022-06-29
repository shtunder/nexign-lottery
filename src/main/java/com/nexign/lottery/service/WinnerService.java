package com.nexign.lottery.service;

import com.nexign.lottery.dto.WinnerDto;

import java.util.List;

/**
 * @author Andrey Shtunder
 */
public interface WinnerService {

    List<WinnerDto> getAllWinners();

    WinnerDto createWinner(WinnerDto winnerDto);
}
