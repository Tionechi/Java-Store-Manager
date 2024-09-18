
public class receipt {
	private double amount;
	private address fullAddress;
	
	
	
	public receipt(double amount, address fullAddress) {
		this.amount = amount;
		this.fullAddress = fullAddress;
	}

	public double getAmount() {
		return amount;
	}
	
	public String getFullAddress() {
		return fullAddress.toString();
	}
	
	
}
