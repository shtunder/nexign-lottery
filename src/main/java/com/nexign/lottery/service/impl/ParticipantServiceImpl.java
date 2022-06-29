package com.nexign.lottery.service.impl;

import com.nexign.lottery.dto.ParticipantDto;
import com.nexign.lottery.dto.WinnerDto;
import com.nexign.lottery.exception.NotEnoughParticipantsException;
import com.nexign.lottery.exception.ServerSideErrorException;
import com.nexign.lottery.model.ParticipantEntity;
import com.nexign.lottery.repository.ParticipantRepository;
import com.nexign.lottery.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Shtunder
 */
@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ConversionService conversionService;
    private final WinnerServiceImpl winnerService;
    private final ParticipantRepository participantRepository;
    private final WebClient webClient;

    @Override
    public ParticipantDto createParticipant(ParticipantDto participantDto) {
        if (participantDto == null) {
            throw new ServerSideErrorException("Participant is absent.");
        }

        final ParticipantEntity participantEntity = conversionService.convert(participantDto, ParticipantEntity.class);
        participantRepository.save(participantEntity);

        return participantDto;
    }

    @Override
    public List<ParticipantDto> getAllParticipants() {

        return participantRepository.findAll().stream()
                .map(participantEntity -> conversionService.convert(participantEntity, ParticipantDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public WinnerDto startLottery() {

//        int prize = 1 + (int) (Math.random() * 1000);
        int prize = Integer.parseInt(getRandomNumber(1, 1000).split("\n")[0]);

        List<ParticipantEntity> allParticipants = participantRepository.findAll();
        if (allParticipants.size() < 2) {
            throw new NotEnoughParticipantsException(NotEnoughParticipantsException.NOT_ENOUGH_PARTICIPANTS_MESSAGE);
        }
        deleteAllParticipants();
        
//        int winnerIndex = (int) (Math.random() * allParticipants.size());
        int winnerIndex = Integer.parseInt(getRandomNumber(0, allParticipants.size() - 1).split("\n")[0]);
        ParticipantEntity winner = allParticipants.get(winnerIndex);
        WinnerDto winnerDto = new WinnerDto(winner.getName(), winner.getAge(), winner.getCity(), prize);

        return winnerService.createWinner(winnerDto);
    }

    @Override
    public void deleteAllParticipants() {
        participantRepository.deleteAll();
    }

    private String getRandomNumber(int min, int max) {
        return webClient
                .get()
                .uri(String.format("https://www.random.org/integers/?num=1&min=%d&max=%d&col=1&base=10" +
                        "&format=plain&rnd=new", min, max))
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(1000)))
                .block();
    }

}
