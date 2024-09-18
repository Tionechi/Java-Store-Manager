
public abstract class user {
	private int userID;
	private String username;
	private String name;
	private int houseNumber;
	private String postcode;
	private String city;
	private userType type;
	
	public user (int userID, String username,String name, int houseNumber, String postcode, String city, userType type) {
		this.userID = userID;
		this.username = username;
		this.name = name;
		this.houseNumber = houseNumber;
		this.postcode = postcode;
		this.city = city;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public String getUsername() {
		return username;
	}
	
	public String getCity() {
		return city;
	}
	
	public int getHouseNumber() {
		return houseNumber;
	}
	
	public String getPostcode() {
		return postcode;
	}
	public userType getType() {
		return type;
	}
	public int getUserID() {
		return userID;
	}
	
}
