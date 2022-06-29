package com.nexign.lottery.controller;

import com.nexign.lottery.dto.ParticipantDto;
import com.nexign.lottery.dto.ResponseBody;
import com.nexign.lottery.dto.WinnerDto;
import com.nexign.lottery.service.impl.ParticipantServiceImpl;
import com.nexign.lottery.service.impl.WinnerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Andrey Shtunder
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/lottery")
public class LotteryController {

    private final ParticipantServiceImpl participantService;
    private final WinnerServiceImpl winnerService;

    @PostMapping("/participant")
    public ResponseEntity<ResponseBody<ParticipantDto>> createParticipant(@RequestBody final ParticipantDto participantDto) {
        final ParticipantDto createdParticipant = participantService.createParticipant(participantDto);

        final ResponseBody<ParticipantDto> responseBody = ResponseBody
                .<ParticipantDto>builder()
                .message("Participant was created.")
                .path("lottery/participant")
                .data(createdParticipant)
                .status(HttpStatus.CREATED.value())
                .color(ResponseBody.Color.SUCCESS.getColor())
                .build();

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/participant")
    public ResponseEntity<ResponseBody<List<ParticipantDto>>> getAllParticipants() {
        final List<ParticipantDto> allParticipants = participantService.getAllParticipants();

        final ResponseBody<List<ParticipantDto>> responseBody = ResponseBody
                .<List<ParticipantDto>>builder()
                .message("All participants were found.")
                .path("lottery/participant")
                .data(allParticipants)
                .status(HttpStatus.OK.value())
                .color(ResponseBody.Color.SUCCESS.getColor())
                .build();

        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/participant")
    public ResponseEntity<ResponseBody<Void>> deleteAllParticipants() {
        participantService.deleteAllParticipants();

        final ResponseBody<Void> responseBody = ResponseBody
                .<Void>builder()
                .message("All participants were deleted.")
                .path("lottery/participant")
                .status(HttpStatus.NO_CONTENT.value())
                .color(ResponseBody.Color.SUCCESS.getColor())
                .build();

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/start")
    public ResponseEntity<ResponseBody<WinnerDto>> findWinner() {
        final WinnerDto winner = participantService.startLottery();

        final ResponseBody<WinnerDto> responseBody = ResponseBody
                .<WinnerDto>builder()
                .message("Winner was found.")
                .path("lottery/start")
                .data(winner)
                .status(HttpStatus.OK.value())
                .color(ResponseBody.Color.SUCCESS.getColor())
                .build();

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/winners")
    public ResponseEntity<ResponseBody<List<WinnerDto>>> getAllWinners() {
        final List<WinnerDto> allWinners = winnerService.getAllWinners();

        final ResponseBody<List<WinnerDto>> responseBody = ResponseBody
                .<List<WinnerDto>>builder()
                .message("All winners were found.")
                .path("lottery/winners")
                .data(allWinners)
                .status(HttpStatus.OK.value())
                .color(ResponseBody.Color.SUCCESS.getColor())
                .build();

        return ResponseEntity.ok(responseBody);
    }
}
