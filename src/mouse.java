
public class mouse extends product{
	private int btns;
	private deviceType dType;
	private int noItems; //Attribute that keeps track of how many of each item is added to the basket

	
	public mouse(int barcode, String brand, deviceType dType, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category, int btns) {
		super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
		this.btns = btns;
		this.dType = dType;
		this.noItems = 0;
	}
	
	public void setNoItems(int noItems) {
		this.noItems = noItems;
	}
	
	public int getNoItems() {
		return noItems;
	}
	
	public deviceType getdType() {
		return dType;
	}
	
	public void setdType(deviceType dType) {
		this.dType = dType;
	}
	
	public void setbtns(int num) {
		btns = num;
	}
	
	public int getBtns() {
		return btns;
	}
	
	public String toString() {
		return getBarcode() + "," + getBrand() + "," + getColor() + "," + getQuantityInStock() + "," + getOriginalCost() + "," + getRetailPrice() + "," + getcategory() + "," + getConnectivity() + "," + getBtns();
	}

}
