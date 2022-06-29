package com.nexign.lottery.service.impl;

import com.nexign.lottery.dto.WinnerDto;
import com.nexign.lottery.model.WinnerEntity;
import com.nexign.lottery.repository.WinnerRepository;
import com.nexign.lottery.service.WinnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Shtunder
 */
@Service
@RequiredArgsConstructor
public class WinnerServiceImpl implements WinnerService {

    private final WinnerRepository winnerRepository;
    private final ConversionService conversionService;

    @Override
    public List<WinnerDto> getAllWinners() {
        return winnerRepository.findAll().stream()
                .map(winnerEntity -> conversionService.convert(winnerEntity, WinnerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public WinnerDto createWinner(WinnerDto winnerDto){
        winnerRepository.save(conversionService.convert(winnerDto, WinnerEntity.class));
        return winnerDto;
    }
}
