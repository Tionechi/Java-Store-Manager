import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class loadUsers {
	
	public loadUsers() {
	
	}
	
	 //method to load in users from a file
    public List<user> loadUsersFromFile(String fileName) throws FileNotFoundException {
        List<user> users = new ArrayList<>();
        File inputFile = new File(fileName);
        Scanner fileScanner = new Scanner(inputFile);
        while (fileScanner.hasNextLine()) {
            String[] details = fileScanner.nextLine().split(",");
            if (userType.valueOf(details[6].trim())== userType.customer) {
                customer cus = new customer(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(),
                        Integer.parseInt(details[3].trim()), details[4].trim(), details[5].trim(),
                        userType.valueOf(details[6].trim()));
                users.add(cus);
            } else {
                admin ad = new admin(Integer.parseInt(details[0].trim()), details[1].trim(), details[2].trim(),
                        Integer.parseInt(details[3].trim()), details[4].trim(), details[5].trim(),
                        userType.valueOf(details[6].trim()));
                users.add(ad);
            }
        }
        fileScanner.close();
        return users;
    }
	
	
}
