
public class keyboard extends product {
	private layout layout;
	private deviceType dType;
	private int noItems; //Attribute that keeps track of how many of each item is added to the basket

	
	public keyboard(int barcode, String brand,deviceType dType, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category, layout layout) {
		super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
		this.layout = layout;
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
	
	public void setLayout(layout type) {
		layout = type;
	}
	
	public layout getLayout() {
		return layout;
	}
	
	public String toString() {
		return getBarcode() + "," + getBrand() + "," + getColor() + "," + getQuantityInStock() + "," + getOriginalCost() + "," + getRetailPrice() + "," + getcategory() + "," + getConnectivity() + "," + getLayout();
	}
}
