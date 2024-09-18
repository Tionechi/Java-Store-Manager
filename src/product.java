
public abstract class product {
	private int barcode;
	private String brand;
	private String color;
	private int quantityInStock;
	private double originalCost;
	private double retailPrice;
	private ProductCategory category;
	private ConnectivityType connectivity;

	
	public product (int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category) {
		this.barcode = barcode;
		this.brand = brand;
		this.category = category;
		this.color = color;
		this.connectivity = connectivity;
		this.originalCost = originalCost;
		this.retailPrice = retailPrice;
		this.quantityInStock = quantityInStock;
	}
	
	public int getBarcode() {
		return barcode;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public ProductCategory getcategory() {
		return category;
	}
	
	public String getColor() {
		return color;
	}
	
	public ConnectivityType getConnectivity() {
		return connectivity;
	}
	
	public double getOriginalCost() {
		return originalCost;
	}
	
	public int getQuantityInStock() {
		return quantityInStock;
	}
	
	public double getRetailPrice() {
		return retailPrice;
	}
	
	public void setQuantityInStock(int newQuantity) {
		quantityInStock = newQuantity;
	}
	
	public abstract String toString();	
	
}
	
	
	


