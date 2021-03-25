import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.external.CardIssuer;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the control software for the self-checkout station.
 * It keeps track of what items the user has added for purchase, and also the
 * current total cost that they will have to pay.
 */
public class ControlSoftware {

    private final Map<Barcode, Integer> purchaseList = new HashMap<>();
    private BigDecimal total = BigDecimal.ZERO;
    private double baggingAreaWeight = 0;
    private final ArrayList<CardIssuer> issuers = new ArrayList<>();

    /**
     * Adds an barcode to the purchase list, as well as how many are being purchased.
     * @param barcode The barcode of the item to add.
     */
    public void updatePurchaseList(Barcode barcode) {
        Integer oldCount = purchaseList.get(barcode);
        Integer newCount = oldCount == null ? 1 : ++oldCount;
        purchaseList.put(barcode, newCount);
    }

    /**
     * Increases the total the user has to pay based on an item's barcode.
     * @param barcode The barcode of the item to use the price of.
     */
    public void increaseTotal(Barcode barcode) {
        BarcodedProduct product = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
        total = total.add(product.getPrice());
    }

    /**
     * Decreases the total the user has to pay by a certain amount. This should be used for coins.
     * @param value The value to subtract from the total.
     */
    public void decreaseTotal(BigDecimal value) {
        total = total.subtract(value);
    }

    /**
     * Decreases the total the user has to pay by a certain amount. This should be used for bills.
     * @param value The value to subtract from the total.
     */
    public void decreaseTotal(int value) {
        total = total.subtract(new BigDecimal(value));
    }

    /**
     * Gets the current list of items that the user wants to purchase.
     * @return The purchase list.
     */
    public Map<Barcode, Integer> getPurchaseList() {
        return purchaseList;
    }

    /**
     * Gets the current total that the user has to pay.
     * @return The total.
     */
    public BigDecimal getTotal() {
        return this.total;
    }

    /**
     * Sets the weight of the bagging area.
     * @param weight The weight to set.
     */
    public void setBaggingAreaWeight(double weight) {
        this.baggingAreaWeight = weight;
    }

    /**
     * Gets the weight of the bagging area.
     * @return The weight of the bagging area.
     */
    public double getBaggingAreaWeight() {
        return this.baggingAreaWeight;
    }

    /**
     * Registers the indicated card issuer with this control software so they can communicate.
     * @param issuer The card issuer to register.
     */
    public void registerCardIssuer(CardIssuer issuer) {
        this.issuers.add(issuer);
    }

    /**
     * Parses the data returned from when a card is read, updating the total if it is valid.
     * @param data The data to parse.
     */
    public void parseCardData(CardData data) {
        for (CardIssuer issuer : issuers) {
            int holdNumber = issuer.authorizeHold(data.getNumber(), total);
            if (holdNumber != 1) {
                if (issuer.postTransaction(data.getNumber(), holdNumber, total)) {
                    decreaseTotal(total);
                    return;
                }
            }
        }
        System.out.println("Transaction failed.");
    }
}
