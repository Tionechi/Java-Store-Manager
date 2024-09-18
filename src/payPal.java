import java.util.Date;

public class payPal  implements PaymentMethod {
	private String email;
	private Date date;
	
	public payPal( String email, Date date) {
		this.email = email;
		this.date = date;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Date getDate() {
		return date;
	}
	
	public receipt processPayment(double amount, address fullAddress) {
		return new receipt(amount, fullAddress);	
	}
	
	
	
	// Method to check if a string resembles an email address
	public boolean isValidEmail(String email) {
	    // Check if email is null or empty
	    if (email == null || email.isEmpty()) {
	        return false;
	    }

	    // Check if "@" symbol exists and occurs only once
	    int atIndex = email.indexOf('@');
	    if (atIndex == -1 || email.indexOf('@', atIndex + 1) != -1) {
	        return false;
	    }

	    // Check if "." symbol exists after "@" symbol
	    int dotIndex = email.indexOf('.', atIndex);
	    if (dotIndex == -1 || dotIndex == atIndex + 1) {
	        return false;
	    }

	    // Check if "." symbol exists after last "@"
	    dotIndex = email.lastIndexOf('.');
	    if (dotIndex == -1 || dotIndex <= atIndex) {
	        return false;
	    }

	    // Check if "." symbol occurs only once after "@" symbol
	    if (email.indexOf('.', atIndex + 1) != dotIndex) {
	        return false;
	    }

	    // Check if the last segment after "." symbol is at least 2 characters long
	    if (email.length() - dotIndex < 3) {
	        return false;
	    }

	    // Email address passed all checks
	    return true;
	}
}
