package pl.kj.m33t.m33tbackend.dto.response.modules;

import pl.kj.m33t.m33tbackend.entity.modules.Purchase;

import java.util.Date;
import java.util.List;

public record DebtCalculatorResponse(Long id, Long eventId, String title, List<Purchase> purchases, Date date) {}