import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class inventory {
	private ArrayList<product> stock;
	
	public inventory() {
		this.stock = new ArrayList<product>();
	}
	
	public ArrayList<product> getStock() {
		return stock;
	}
	
	public void addItem(product item) {
		stock.add(item);
	}
	
	// Method to display products that are in stock
	 public ArrayList<product> customerView() {
	        ArrayList<product> available = new ArrayList<product>();
	        for (product product : stock) {
	            if (product.getQuantityInStock() > 0) {
	                available.add(product);
	            }
	        }
	        // Sort the available products by retail price
	        Collections.sort(available, new Comparator<product>() {
	           
	            public int compare(product p1, product p2) {
	                // Compare the retail prices of the two products
	                return Double.compare(p1.getRetailPrice(), p2.getRetailPrice());
	            }
	        });
	        return available;
	    }
	 
	 public ArrayList<product> adminView() {
		// Sort the available products by retail price
	        Collections.sort(stock, new Comparator<product>() {
	           
	            public int compare(product p1, product p2) {
	                // Compare the retail prices of the two products
	                return Double.compare(p1.getRetailPrice(), p2.getRetailPrice());
	            }
	        });
		 return stock;
	 }
	 
	 // Method to update product quantity upon purchase
	    public void updateProductQuantity(product product, int quantity) {
	        // Find the product in the list and update its quantity
	        for (product p : stock) {
	            if (p.getBarcode() == product.getBarcode()) {
	                product.setQuantityInStock(product.getQuantityInStock() - quantity);
	                break;
	            }
	        }
	    }
	    
	    // Method that searches the inventory for a product that matches the barcode provide
	    public ArrayList<product> barcodeSearch(int barcode){
	    	 ArrayList<product> results = new ArrayList<product>();
	    	 for (product p: stock) {
	    		 if(p.getBarcode() == barcode) {
	    			 results.add(p);
	    		 }
	    	 }
	    	 return results;
	    }
	    
	    //method that accesses a file and adds products to it
	    public  void writeIntoFile(List<product> data, String fileName) {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(fileName,false)); 
				for(product item : data){
					if (item instanceof keyboard) {
       	             keyboard keyboardItem = (keyboard) item;
       	          bw.write( keyboardItem.getBarcode()+ ","+ keyboardItem.getcategory()+","+ keyboardItem.getdType()+ ","+ keyboardItem.getBrand()+","+ keyboardItem.getColor()+","+keyboardItem.getConnectivity()+","+keyboardItem.getQuantityInStock()+","+keyboardItem.getOriginalCost()+","+keyboardItem.getRetailPrice()+","+keyboardItem.getLayout()+ "\n");
       	             
       	             
       	         } else if (item instanceof mouse) {
       	             mouse mouseItem = (mouse) item;
       	             bw.write( mouseItem.getBarcode()+ ","+ mouseItem.getcategory()+","+ mouseItem.getdType() + "," +  mouseItem.getBrand()+","+ mouseItem.getColor()+","+mouseItem.getConnectivity()+","+mouseItem.getQuantityInStock()+","+mouseItem.getOriginalCost()+","+mouseItem.getRetailPrice()+","+mouseItem.getBtns()+ "\n"); 
       	         }
					
				}

			} catch(IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			finally {
				try {
					if(bw != null) {
						bw.close();
					}
				} catch(IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	    
	    
	    //method that accesses the stock file and loads in the products 
	    public List<product> loadProducts(String fileName) throws FileNotFoundException {
	        List<product> products = new ArrayList<>();
	        File inputFile = new File(fileName);
	        Scanner fileScanner = new Scanner(inputFile);
	        while (fileScanner.hasNextLine()) {
	            String[] details = fileScanner.nextLine().split(",");
	            if (ProductCategory.valueOf(details[1].trim()) == ProductCategory.mouse) {
	                mouse mou = new mouse(Integer.parseInt(details[0].trim()), details[3].trim(),deviceType.valueOf((details[2].trim())) ,details[4].trim(),
	                        ConnectivityType.valueOf((details[5].trim())), Integer.parseInt(details[6].trim()), Double.parseDouble(details[7].trim()),
	                        Double.parseDouble(details[8].trim()),ProductCategory.valueOf(details[1].trim()),Integer.parseInt(details[9].trim()) );
	                products.add(mou);
	            } else {
	                keyboard board = new keyboard(Integer.parseInt(details[0].trim()), details[3].trim(),deviceType.valueOf((details[2].trim())),details[4].trim(),
	                        ConnectivityType.valueOf((details[5].trim())), Integer.parseInt(details[6].trim()), Double.parseDouble(details[7].trim()),
	                        Double.parseDouble(details[8].trim()),ProductCategory.valueOf(details[1].trim()),layout.valueOf(details[9].trim()) );
	                products.add(board);
	            }
	        }
	        fileScanner.close();
	        return products;
	    }
	    
	    

}
