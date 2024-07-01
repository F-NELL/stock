package manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.stock.pojo.LogStock;
import com.example.stock.service.LogStockService;
import com.example.stock.service.ProduitService;

@Component
public class LogStockManager {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private LogStockService logStockService;

    public void processLog(LogStock logStock) {
        // 1. intégration du log
        this.logStockService.createLogStock(logStock);

        // 2. mise en place de la modification
        switch ((logStock.getAction())) {
            case MODIFICATION -> {
                this.produitService.updateProduitQuantity(
                        logStock.getProduit_id(), logStock.getQuantite());
                break;
            }
            case SUPPRESSION -> {
                this.produitService.deleteProduitById(logStock.getProduit_id());
                break;
            }
        }

    }
}
