import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

import java.util.HashMap;
import java.util.Map;

public class ControlSoftware {

    private final Map<Barcode, Integer> purchaseList = new HashMap<>();

    public void updatePurchaseList(Barcode barcode) {
        Integer oldCount = purchaseList.get(barcode);
        Integer newCount = oldCount == null ? 1 : ++oldCount;
        purchaseList.put(barcode, newCount);
    }
}
