import java.math.BigDecimal;

public class ReceivesChange {

	ControlSoftware control = new ControlSoftware();
	CoinTrayController tray = new CoinTrayController();

	private BigDecimal total = control.getTotal();
	private BigDecimal change;
	
	private BigDecimal hundred = new BigDecimal("100");
	private BigDecimal fifty = new BigDecimal("50");
	private BigDecimal twenty = new BigDecimal("20");
	private BigDecimal ten = new BigDecimal("10");
	private BigDecimal five = new BigDecimal("5");
	private BigDecimal two = new BigDecimal("2.00");
	private BigDecimal one = new BigDecimal("1.00");
	private BigDecimal half = new BigDecimal("0.50");
	private BigDecimal quarter = new BigDecimal("0.25");
	private BigDecimal dime = new BigDecimal("0.10");
	private BigDecimal nickel = new BigDecimal("0.05");
	private BigDecimal penny = new BigDecimal("0.01");

	public BigDecimal[] divisor = {hundred,fifty, twenty, ten, five, two, one, half, quarter, dime, nickel, penny};
	
	private BigDecimal numBanknote;

	//smallest to largest denomination, each number in the index is the number of the denomination add up to total change
	private int[] numDenomination = new int [12];
	
	public ReceivesChange() {
	}
	
	public ReceivesChange(BigDecimal total) {
		this.total = total;
	}
	
	/**
	 * Returns the minimum amount of denomination needed for change returned to the customer
	 * 
	 * @return array of number of denomination needed from smallest to largest
	 * @throws Exception 
	 */
	public int[] calculateChange(BigDecimal payAmount) throws Exception {
		
		change = total.subtract(payAmount);
		if(change.compareTo(BigDecimal.ZERO) < 0) {
			throw new Exception("Customer have not paid the full amount");
		}
		int denominationIndex = 11;
		BigDecimal target = new BigDecimal("0.00");

		 for(int x=0; x < divisor.length; x++) {
			 numBanknote = change.divide(divisor[x], 0, 1);
			 BigDecimal subtractTotal = divisor[x].multiply(numBanknote);

			 change = change.subtract(subtractTotal);
			 numDenomination[denominationIndex] = numBanknote.intValue();
			 denominationIndex--;

		 }		 
		return numDenomination;	 
	}
}
