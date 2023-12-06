package pl.kj.m33t.m33tbackend.service.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kj.m33t.m33tbackend.model.entity.Event;
import pl.kj.m33t.m33tbackend.model.entity.modules.DebtCalculator;
import pl.kj.m33t.m33tbackend.model.repository.modules.DebtCalculatorRepository;
import pl.kj.m33t.m33tbackend.model.repository.modules.PurchaseRepository;
import pl.kj.m33t.m33tbackend.service.EventService;
import pl.kj.m33t.m33tbackend.service.UserService;
import pl.kj.m33t.m33tbackend.service.dto.request.modules.DebtCalculatorRequest;
import pl.kj.m33t.m33tbackend.service.mapping.modules.DebtCalculatorMapper;

@Service
@RequiredArgsConstructor
public class DebtCalculatorService {

    private final DebtCalculatorRepository debtCalculatorRepository;
    private final EventService eventService;
    private final PurchaseRepository purchaseRepository;
    private final UserService userService;

    public DebtCalculator findById(Long id){
        return debtCalculatorRepository.findById(id).orElse(null);
    }

    public DebtCalculator save(Long eventId, DebtCalculatorRequest debtCalculatorRequest){
        Event event = eventService.findById(eventId);
        DebtCalculator newDebtCalculator = DebtCalculatorMapper.INSTANCE.fromDebtCalculatorRequestToDebtCalculator(debtCalculatorRequest);
        newDebtCalculator.setEvent(event);
        return debtCalculatorRepository.save(newDebtCalculator);
    }

//    public List<Purchase> calculateTotalDebtForUser(Long id){
//        DebtCalculator debtCalculator = findById(id);
//        User loggedInUser = userService.getLoggedUser(SecurityContextHolder.getContext().getAuthentication());
//        List<Purchase> purchases;
//        for (Purchase purchase : debtCalculator.getPurchases()) {
//            debtCalculator.addDebt(purchase.getPrice().toBigInteger().intValue());
//            purchase.getPurchaser();
//        }
//        List<Purchase> purchases = purchaseRepository.findByDebtorIdContains(loggedInUser);
//        for (Purchase purchase : debtCalculator.getPurchases()) {
//            totalDebts.add(purchase.getPrice());
//            purchase
//        }
//
//        return purchases;
//    }

    public DebtCalculator save(DebtCalculator debtCalculator) {
        return debtCalculatorRepository.save(debtCalculator);
    }

    public void deleteById(Long id){
        debtCalculatorRepository.deleteById(id);
    }
}