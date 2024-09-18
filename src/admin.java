import java.util.List;

public class admin extends user {
	
	public admin(int userID, String username,String name, int houseNumber, String postcode, String city, userType type) {
		super(userID, username, name, houseNumber, postcode, city, type);
		
	}
	
	
	
	//Method to add new keyboard to the inventory
	public List<product> newKeyboard (List<product> list,int barcode, String brand,deviceType dType, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double rPrice, ProductCategory category,layout layout){
		if(category ==  ProductCategory.keyboard ) {
			keyboard item = new keyboard( barcode, brand,dType, color, connectivity, quantityInStock,  originalCost,  rPrice, category, layout);
			list.add(item);
		} 
		
		return list;
		
	}
	
	//Method to add new Mouse to the inventory
	public List<product> newMouse (List<product> list,int barcode, String brand,deviceType dType, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double rPrice, ProductCategory category,int btns){
		if(category == ProductCategory.mouse) {
			mouse item = new mouse( barcode, brand,dType, color, connectivity, quantityInStock,  originalCost,  rPrice, category, btns);
			list.add(item);
		}
		
		return list;
		
	}
	
	
	//Method to increase the quantity of a product
	public void increaseQuant(List<product> invent,product item, int num) {
		for(product p : invent) {
			if(item.getBarcode() == p.getBarcode()) {
				p.setQuantityInStock(p.getQuantityInStock()+ num);
			}
			
		}
		
	}

}
