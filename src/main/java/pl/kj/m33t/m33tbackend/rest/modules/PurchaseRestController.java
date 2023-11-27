package pl.kj.m33t.m33tbackend.rest.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kj.m33t.m33tbackend.dto.request.modules.PurchaseRequest;
import pl.kj.m33t.m33tbackend.dto.response.modules.PurchaseResponse;
import pl.kj.m33t.m33tbackend.entity.modules.Purchase;
import pl.kj.m33t.m33tbackend.mapping.modules.PurchaseMapper;
import pl.kj.m33t.m33tbackend.service.modules.PurchaseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events/{eventId}/debtCalculators/{debtCalculatorId}/purchases")
public class PurchaseRestController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponse> createPurchase(@PathVariable Long debtCalculatorId, @RequestBody PurchaseRequest purchaseRequest) {
        Purchase createdPurchase = purchaseService.createPurchase(debtCalculatorId, purchaseRequest);
        PurchaseResponse purchaseResponse = PurchaseMapper.INSTANCE.fromPurchaseToPurchaseResponse(createdPurchase);
        return ResponseEntity.ok(purchaseResponse);
    }
}
