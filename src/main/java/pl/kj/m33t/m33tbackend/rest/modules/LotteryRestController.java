package pl.kj.m33t.m33tbackend.rest.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kj.m33t.m33tbackend.entity.modules.Lottery;
import pl.kj.m33t.m33tbackend.dto.request.modules.LotteryRequest;
import pl.kj.m33t.m33tbackend.dto.response.modules.LotteryResponse;
import pl.kj.m33t.m33tbackend.mapping.modules.LotteryMapper;
import pl.kj.m33t.m33tbackend.service.modules.LotteryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events/{eventId}/lotteries")
public class LotteryRestController {

    private final LotteryService lotteryService;

    @PostMapping
    public ResponseEntity<LotteryResponse> createLottery(@PathVariable Long eventId, @RequestBody LotteryRequest lotteryRequest) {
        Lottery createdLottery = lotteryService.createLottery(eventId, lotteryRequest);
        LotteryResponse lotteryResponse = LotteryMapper.INSTANCE.fromLotterytoLotteryResponse(createdLottery);
        return ResponseEntity.ok(lotteryResponse);
    }

    @GetMapping("/{lotteryId}")
    public ResponseEntity<LotteryResponse> getLottery(@PathVariable Long lotteryId) {
        return ResponseEntity.ok(LotteryMapper.INSTANCE.fromLotterytoLotteryResponse(lotteryService.findById(lotteryId)));
    }

    @PutMapping("/{lotteryId}")
    public ResponseEntity<LotteryResponse> updateLottery(@PathVariable Long lotteryId, @RequestBody LotteryRequest lotteryRequest) {
        Lottery lottery = lotteryService.save(LotteryMapper.INSTANCE.fromLotteryRequestToLottery(lotteryRequest));
        return ResponseEntity.ok(LotteryMapper.INSTANCE.fromLotterytoLotteryResponse(lottery));
    }

    @DeleteMapping("/{lotteryId}")
    public ResponseEntity<Void> deleteLottery(@PathVariable Long lotteryId) {
        lotteryService.deleteById(lotteryId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{lotteryId}/draw")
    public ResponseEntity<String> draw(@PathVariable Long lotteryId) {
        String winner = lotteryService.drawWinner(lotteryId);
        return ResponseEntity.ok(winner);
    }
}