package pl.kj.m33t.m33tbackend.rest.impl.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kj.m33t.m33tbackend.service.dto.request.modules.DebtCalculatorRequest;
import pl.kj.m33t.m33tbackend.service.dto.response.modules.DebtCalculatorResponse;
import pl.kj.m33t.m33tbackend.service.mapping.modules.DebtCalculatorMapper;
import pl.kj.m33t.m33tbackend.model.entity.modules.DebtCalculator;
import pl.kj.m33t.m33tbackend.service.modules.DebtCalculatorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events/{eventId}/debtcalculators")
public class DebtCalulatorController {
    private final DebtCalculatorService debtCalculatorService;

    @PostMapping
    public ResponseEntity<DebtCalculatorResponse> createDebtCalculator(@PathVariable Long eventId, DebtCalculatorRequest debtCalculatorRequest) {
        DebtCalculator createdDebtCalculator = debtCalculatorService.save(eventId, debtCalculatorRequest);
        return ResponseEntity.ok(DebtCalculatorMapper.INSTANCE.fromDebtCalculaotrToDebtCalculatorResponse(createdDebtCalculator));
    }

    @GetMapping("/{debtCalculatorId}")
    public ResponseEntity<DebtCalculatorResponse> getDebtCalculator(@PathVariable Long debtCalculatorId) {
        return ResponseEntity.ok(DebtCalculatorMapper.INSTANCE.fromDebtCalculaotrToDebtCalculatorResponse(debtCalculatorService.findById(debtCalculatorId)));
    }

    @PutMapping("/{debtCalculatorId}")
    public ResponseEntity<DebtCalculatorResponse> updateDebtCalculator(@PathVariable Long debtCalculatorId, @RequestBody DebtCalculatorRequest debtCalculatorRequest) {
        DebtCalculator debtCalculator = debtCalculatorService.save(DebtCalculatorMapper.INSTANCE.fromDebtCalculatorRequestToDebtCalculator(debtCalculatorRequest));
        return ResponseEntity.ok(DebtCalculatorMapper.INSTANCE.fromDebtCalculaotrToDebtCalculatorResponse(debtCalculator));
    }

    @DeleteMapping("/{debtCalculatorId}")
    public ResponseEntity<Void> deleteDebtCalculator(@PathVariable Long debtCalculatorId) {
        debtCalculatorService.deleteById(debtCalculatorId);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/{debtCalculatorId}/calculate")
//    public ResponseEntity<DebtCalculatorResponse> calculateDebts(@PathVariable Long debtCalculatorId) {
//        debtCalculatorService.calculateDebts(debtCalculatorId);
//        return ResponseEntity.ok();
//    }
}