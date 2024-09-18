
public class address {
	private int houseNumber;
	private String postcode;
	private String city;
	
	
	public address(int houseNumber, String postcode, String city) {
		this.houseNumber = houseNumber;
		this.postcode = postcode;
		this.city = city;
	}
	
	public int getHouseNumber() {
		return houseNumber;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public String getCity() {
		return city;
	}
	
	
	public  String toString() {
		return getHouseNumber() + "," + getPostcode() + "," + getCity();
		
	}

}
