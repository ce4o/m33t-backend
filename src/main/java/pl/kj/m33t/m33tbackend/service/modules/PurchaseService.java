package pl.kj.m33t.m33tbackend.service.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kj.m33t.m33tbackend.service.dto.request.modules.PurchaseRequest;
import pl.kj.m33t.m33tbackend.model.entity.modules.Purchase;
import pl.kj.m33t.m33tbackend.service.mapping.modules.PurchaseMapper;
import pl.kj.m33t.m33tbackend.model.repository.modules.PurchaseRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final DebtCalculatorService debtCalculatorService;
    private final PurchaseRepository purchaseRepository;

    public Purchase findById(Long id){
        return purchaseRepository.findById(id).orElse(null);
    }

    public Purchase createPurchase(Long debtCalcualtorId, PurchaseRequest purchaseRequest){
        Purchase newPurchase = PurchaseMapper.INSTANCE.fromPurchaseRequestToPurchase(purchaseRequest);
        newPurchase.setDebtCalculator(debtCalculatorService.findById(debtCalcualtorId));
        return purchaseRepository.save(newPurchase);
    }

    public void addDebt(Long purchaseId, String itemName, BigDecimal price){
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow();
        purchase.getItemPriceMap().put(itemName, price);
        purchaseRepository.save(purchase);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public void deleteById(Long id){
        purchaseRepository.deleteById(id);
    }
}