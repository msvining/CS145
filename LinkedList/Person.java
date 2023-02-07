//class for a person's info

public class Person {
	
	private String firstName; //create variables for info
	private String lastName;
	private String address;
	private String phoneNumber;
	
	public Person(String firstName, String lastName, String address, String phoneNumber) {
		
		this.firstName = firstName; //set variables to input
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		
	}
	
	public boolean checkName(String first, String last) { //method to check if a name is the same
		
		if (first.equals(firstName) && last.equals(lastName)) {
			
			return true; //if the first and last name match, return true
			
		}
		return false; //if not, return false
		
	}
	
	public void Display() { //method to display the value
		
		System.out.printf("Name: %s %s\n", firstName, lastName); //print the name
		System.out.printf("Address: %s\n", address); //print the address
		System.out.printf("Phone Nuber: %s\n", phoneNumber); //print the phone number
		
	}
	
	public String[] getData() { //method to get data
		
		String data[] = new String[] {firstName, lastName, address, phoneNumber}; //put all values in an array
		return data; //return array
		
	}
	
}
