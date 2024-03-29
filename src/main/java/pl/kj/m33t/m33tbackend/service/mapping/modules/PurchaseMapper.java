package pl.kj.m33t.m33tbackend.service.mapping.modules;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.kj.m33t.m33tbackend.model.entity.modules.Purchase;
import pl.kj.m33t.m33tbackend.service.dto.request.modules.PurchaseRequest;
import pl.kj.m33t.m33tbackend.service.dto.response.modules.PurchaseResponse;

@Mapper
public interface PurchaseMapper {
    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "creditorId", source = "creditorId")
    @Mapping(target = "debtorId", source = "debtorId")
    @Mapping(target = "itemPriceMap", source = "itemPriceMap")
    PurchaseResponse fromPurchaseToPurchaseResponse(Purchase purchase);

    @Mapping(target = "creditorId", source = "creditorId")
    @Mapping(target = "debtorId", source = "debtorId")
    Purchase fromPurchaseRequestToPurchase(PurchaseRequest purchaseRequest);

}