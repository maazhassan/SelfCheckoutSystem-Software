import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import java.math.BigDecimal;
import java.math.MathContext;
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
    private ArrayList<String> validMemNumbers = new ArrayList<String>();

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
     * Creating a database of valid membership card numbers
     */
    
    public void registerMembershipNumber() {
    	for(int i = 0; i <= 10; i++) {
    		validMemNumbers.add(String.valueOf(i));
    	}
    }
    
    /**
     * Sees if the card is a membership card and if it is the customer gets a discount
     * @param card Data of the card that is being used
     */
    
    public boolean scanMembershipCard(String number) {
    	for (int i = 0; i < validMemNumbers.size(); i++){
    		String validNumber = validMemNumbers.get(i);
    		if(validNumber.equals(number)) {
    			discountForMember();
    			return true;
    		}
    		
    	}
    	return false;
    }
    
    /**
     *  Give the member a 10% discount
     */
    
    public void discountForMember() {
    	double discount = 0.1;
		BigDecimal roughNumber = total.multiply(new BigDecimal(discount));
		double subFromTotal = roughNumber.doubleValue();
		total = total.subtract(new BigDecimal(subFromTotal));
		
		if((new BigDecimal(0).compareTo(total) == -1) && (new BigDecimal(9).compareTo(total) == 1)) {
			MathContext roundPrecision = new MathContext(3);
			total = total.round(roundPrecision);
		}
		
		if((new BigDecimal(10).compareTo(total) == -1) && (new BigDecimal(99).compareTo(total) == 1)) {
			MathContext roundPrecision = new MathContext(4);
			total = total.round(roundPrecision);
		}
		
		if((new BigDecimal(100).compareTo(total) == -1) && (new BigDecimal(999).compareTo(total) == 1)) {
			MathContext roundPrecision = new MathContext(5);
			total = total.round(roundPrecision);
		}
		
    }
    
    
    /**
     * The customer is finished adding items and the total is displayed
     */
    
    public String finishAddingItems() {
    	return "The total for all of your items are " + getTotal();
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

    public void setBaggingAreaWeight(double weight) {
        this.baggingAreaWeight = weight;
    }

    public double getBaggingAreaWeight() {
        return this.baggingAreaWeight;
    }
}
