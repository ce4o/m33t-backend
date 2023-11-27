package pl.kj.m33t.m33tbackend.service.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kj.m33t.m33tbackend.entity.Event;
import pl.kj.m33t.m33tbackend.entity.modules.Lottery;
import pl.kj.m33t.m33tbackend.exception.NotFoundException;
import pl.kj.m33t.m33tbackend.mapping.modules.LotteryMapper;
import pl.kj.m33t.m33tbackend.service.EventService;
import pl.kj.m33t.m33tbackend.dao.modules.LotteryRepository;
import pl.kj.m33t.m33tbackend.dto.request.modules.LotteryRequest;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LotteryService {

    private final LotteryRepository lotteryRepository;
    private final EventService eventService;

    public Lottery findById(Long id){
        return lotteryRepository.findById(id).orElse(null);
    }

    public Lottery createLottery(Long eventId, LotteryRequest lotteryRequest) {
        Event event = eventService.findById(eventId);
        Lottery newLottery = LotteryMapper.INSTANCE.fromLotteryRequestToLottery(lotteryRequest);
        newLottery.setEvent(event);
        return lotteryRepository.save(newLottery);
    }

    public Lottery save(Lottery lottery) {
        return lotteryRepository.save(lottery);
    }
    public String drawWinner(Long lotteryId) {
        Lottery lottery = lotteryRepository.findById(lotteryId)
                .orElseThrow(() -> new NotFoundException("Lottery not found"));

        List<String> options = lottery.getOptions();

        if (options.isEmpty()) {
            throw new IllegalArgumentException("No options available for the draw");
        }

        Random random = new Random();

        int winnerIndex = random.nextInt(options.size());
        String winner = options.get(winnerIndex);

        lottery.setResult(winner);
        lotteryRepository.save(lottery);

        return winner;
    }

    public void deleteById(Long id){
        lotteryRepository.deleteById(id);
    }
}