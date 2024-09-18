import java.util.ArrayList;

public class customer extends user {
	// Each new customer object has it's own basket
	private ArrayList<product> basket;
	
	
	public customer(int userID, String username,String name, int houseNumber, String postcode, String city, userType type) {
		super(userID, username, name, houseNumber, postcode, city, type);
		this.basket = new ArrayList<product>();
	}
	
	public ArrayList<product> getBasket() {
		return basket;
	}
	
	//method to add items to the basket
	public void addBasket(product item) {
		basket.add(item);
	}
	
	//method to clear the basket of all its contents 
	public void clearBasket() {
		basket.clear();
	}
	
	
	//method to calculate the basket total for each user
	public double basketTotal() {
		double total = 0.0;
	    	for (product item : basket) {
	    		if (item instanceof keyboard) {
	    			 keyboard keyboardItem = (keyboard) item;
	    			 total += keyboardItem.getRetailPrice() * keyboardItem.getNoItems();
	    		} else if (item instanceof mouse) {
	    			mouse mouseItem = (mouse) item;
	    			total += mouseItem.getRetailPrice() * mouseItem.getNoItems();
	    		}
	    		 
	    	}
	    	return total;
	}

}
 