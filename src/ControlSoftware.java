import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.external.CardIssuer;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.Item;

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
    private double previousWeight = 0;
    private  final ArrayList<String> validMemNumbers = new ArrayList<>();
    private final ArrayList<CardIssuer> issuers = new ArrayList<>();
    private final ArrayList<Item> baggedItems = new ArrayList<>();


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
     * @param number The card number of the membership card
     */
    
    public boolean scanMembershipCard(String number) {
        for (String validNumber : validMemNumbers) {
            if (validNumber.equals(number)) {
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
		
		if((new BigDecimal(0).compareTo(total) < 0) && (new BigDecimal(9).compareTo(total) > 0)) {
			MathContext roundPrecision = new MathContext(3);
			total = total.round(roundPrecision);
		}
		
		if((new BigDecimal(10).compareTo(total) < 0) && (new BigDecimal(99).compareTo(total) > 0)) {
			MathContext roundPrecision = new MathContext(4);
			total = total.round(roundPrecision);
		}
		
		if((new BigDecimal(100).compareTo(total) < 0) && (new BigDecimal(999).compareTo(total) > 0)) {
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
	
    public ArrayList<Item> getBaggedList() {
    	return baggedItems;
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
     * Gets the last weight of the bagging area.
     * @return The weight of the bagging area.
     */
    public double getPreviousWeight() {
    	return this.previousWeight;
    }
	
	/**
     * Sets the weight of the bagging area.
     * @param weight The weight to set.
     */
    
    public void setPreviousWeight(double weight) {
    	this.previousWeight = weight;
    }
    
    /**
     * increases the weight of the bagging area.
     * @return The weight of the bagging area.
     */
	
    public void increaseWeight(double weight) {
    	setPreviousWeight(this.baggingAreaWeight);
    	this.baggingAreaWeight +=weight;
    }
    
    /**
     * decreases the weight of the bagging area.
     * @return The weight of the bagging area.
     */
    
    public void decreaseWeight(double weight) {
    	setPreviousWeight(this.baggingAreaWeight);
    	this.baggingAreaWeight -= weight;
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
	
	
	
    public void addOwnBag(double bagWeight) {
    	this.increaseWeight(bagWeight);    	
    	
    }
    
    public void addItem(Item baggedItem) {
    	this.increaseWeight(baggedItem.getWeight());
    	baggedItems.add(baggedItem);
    }
    
    public ArrayList<Item> getBaggedList() {
    	return baggedItems;
    }
    
    public Boolean verifyBagging() {
    	if (previousWeight == baggingAreaWeight) {
    		return false;
    	}
    	return true;
    }
}
