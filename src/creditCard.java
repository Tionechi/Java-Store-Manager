import java.util.Date;

public class creditCard implements PaymentMethod {
	private int cardNo;
	private int securityNo;
	private Date date;
	
	public creditCard(int cardNo, int securityNo, Date date) {
		this.cardNo = cardNo;
		this.securityNo = securityNo;
		this.date = date;
	}
	
	public int getCardNo() {
		return cardNo;
	}
	public int getSecurityNo() {
		return securityNo;
	}
	
	public Date getDate() {
		return date;
	}
	
	public receipt processPayment(double amount, address fullAddress) {
		return new receipt(amount, fullAddress);	
	}
	
	

}
