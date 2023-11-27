package pl.kj.m33t.m33tbackend.mapping.modules;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.kj.m33t.m33tbackend.entity.modules.Lottery;
import pl.kj.m33t.m33tbackend.dto.request.modules.LotteryRequest;
import pl.kj.m33t.m33tbackend.dto.response.modules.LotteryResponse;

@Mapper
public interface LotteryMapper {
    LotteryMapper INSTANCE = Mappers.getMapper(LotteryMapper.class);

    @Mapping(target = "lotteryId", source = "id")
    @Mapping(target = "eventId", source = "event.eventId")
    @Mapping(target = "sentence", source = "sentence")
    @Mapping(target = "options", source = "options")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "date", source = "date")
    LotteryResponse fromLotterytoLotteryResponse(Lottery lottery);

    @Mapping(target = "sentence", source = "sentence")
    @Mapping(target = "options", source = "options")
    Lottery fromLotteryRequestToLottery(LotteryRequest lotteryRequest);

}