package pl.kj.m33t.m33tbackend.mapping.modules;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.kj.m33t.m33tbackend.dto.request.modules.DebtCalculatorRequest;
import pl.kj.m33t.m33tbackend.dto.response.modules.DebtCalculatorResponse;
import pl.kj.m33t.m33tbackend.entity.modules.DebtCalculator;

@Mapper
public interface DebtCalculatorMapper {
    DebtCalculatorMapper INSTANCE = Mappers.getMapper(DebtCalculatorMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "eventId", source = "event.eventId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "purchases", source = "purchases")
    @Mapping(target = "date", source = "date")
    DebtCalculatorResponse fromDebtCalculaotrToDebtCalculatorResponse(DebtCalculator debtCalculator);

    @Mapping(target = "title", source = "title")
    DebtCalculator fromDebtCalculatorRequestToDebtCalculator(DebtCalculatorRequest debtCalculatorRequest);
}