import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ControlSoftware {

    private final Map<Barcode, Integer> purchaseList = new HashMap<>();
    private BigDecimal total = BigDecimal.ZERO;

    public void updatePurchaseList(Barcode barcode) {
        Integer oldCount = purchaseList.get(barcode);
        Integer newCount = oldCount == null ? 1 : ++oldCount;
        purchaseList.put(barcode, newCount);
    }

    public void increaseTotal(Barcode barcode) {
        BarcodedProduct product = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
        total = total.add(product.getPrice());
    }

    public void decreaseTotal(BigDecimal value) {
        total = total.subtract(value);
    }

    public Map<Barcode, Integer> getPurchaseList() {
        return purchaseList;
    }

    public BigDecimal getTotal() {
        return this.total;
    }
}
