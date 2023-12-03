package pl.kj.m33t.m33tbackend.service.dto.response.modules;

import java.math.BigDecimal;
import java.util.Map;

public record PurchaseResponse(Long id, Long creditorId, Long debtorId, Map<String, BigDecimal> itemPriceMap) {}