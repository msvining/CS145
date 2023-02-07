import java.util.Scanner; //Import scanner class for user input

public class MVLinkedList { //main class
	
	private static Scanner scanner = new Scanner(System.in); //creates a scanner to read user input
	
	private static List<List<Person>> phoneBook = new List<List<Person>>(); //phonebook list
	private static List<String> names = new List<String>(); //name list
	
	public static void main(String args[]) { //main method
		
		help(); //print the help message
		
		while (true) { //main loop
			
			String cmd = input("Please input a command:"); //get a command from the user
			
			if (cmd.equals("help")) { 
				
				help(); //if the command is help, print the help message
				
			} else if (cmd.equals("view")) { //if the command is view
				
				for (int i = 0; i < names.Size(); i++) { //loop through the names
					
					System.out.println(names.GetValue(i)); //and display their values
					
				}
				
			} else if (cmd.equals("create")) { 
				
				create(); //if the command is create, run the create method
				
			} else if (cmd.equals("delete")) {
				
				delete(); //if the command is delete, run the delete method
				
			} else if (cmd.equals("edit")) {
				
				edit(); //if the command is edit, run the edit method
				
			}else if (cmd.equals("exit")) {  
				
				break; //if the command is exit, quit the loop
				
			} else {
				
				System.out.println("Please input a valid command"); //if none, tell the user
				
			}
			
		}
		
	}
	
	private static int search(String name) { //method to find an index
		
		int index = -1; //set the index to -1
		for (int i = 0; i < names.Size(); i++) { //loop through the names
			
			if (names.GetValue(i).equals(name)) {
				
				index = i; //if found, set the index to i
				
			}
			
		}
		
		return index; //return the index
		
	}
	
	private static void edit() { //method to edit a phonebook
		
		String entry = input("which entry would you like to edit:"); //get the name of the book to edit
		int index = search(entry); //find the index
		
		if (index > -1) { //if the book exists
			editHelp(); //print the edit help message
			List<Person> book = phoneBook.GetValue(index); //get the book list
			
			while (true) { //edit loop
			
				String editCmd = input("please input a command (editing):"); //get a command from the user
				
				if (editCmd.equals("help")) {
					
					editHelp(); //if help, print the edit help message
					
				} else if (editCmd.equals("view")) { //if the command is view

					for (int i = 0; i < book.Size(); i++) { //loop through the entries
						
						book.GetValue(i).Display(); //and display each one
						
					}
					
				} else if (editCmd.equals("create")) { 
					
					addPerson(book); //if the command is create, call the add person method
					
				} else if (editCmd.equals("delete")) {
					
					removePerson(book); //if the command is delete, call the remove person method
					
				} else if (editCmd.equals("edit")) {
					
					editPerson(book);
					
				}else if (editCmd.equals("exit")) {  
					
					break; //if the command is exit, exit the loop
					
				} else {
					
					System.out.println("Please input a valid command"); //if none, tell the user
					
				}
				
			}
		
		}
		
	}
	
	private static void editPerson(List<Person> book) { //method to edit a person
		
		String name[] = input("Please input a name to edit (first last):").split(" "); //get the name of the person
		
		for (int i = 0; i < book.Size(); i++) { //loop through the list
			
			boolean found = book.GetValue(i).checkName(name[0], name[1]); //check if the name matches
			if (found) { //if found
				
				System.out.println("found"); //tell the user
				System.out.println("Input new values (Blank for none)"); //tell the user how to input
				String newName[] = input("Please input a new name (first last):").split(" "); //get new name
				String address = input("Please input a new address:"); //get new address
				String number = input("Please input a new phone number"); //get new phone number
				
				String data[] = book.GetValue(i).getData(); //get old data
				
				if (newName.length != 2) {
					
					newName = new String[] {data[0], data[1]}; //if invalid name, keep old
					
				}
				
				if (address.equals("")) {
					
					address = data[2]; //if empty address, keep old
					
				}
				
				if (number.equals("")) {
					
					number = data[3]; //if empty number, keep old
					
				}
				
				Person newPerson = new Person(newName[0], newName[1], address, number); //create new person
				
				book.Set(newPerson, i); //overwrite old data
				
			}
			
		}
		
	}
	
	private static void addPerson(List<Person> book) { //method to add a person
		
		String name[] = input("Please input a name (first last):").split(" "); //create an array for the first and last name
		if (name.length <= 2) { //if the name is valid
			
			String first = name[0]; //set the first name to index 0
			String last = name[1]; //and the last name to index 1
			
			String address = input("What is this person's address:"); //get the address
			String number = input("What is this person's phone number"); //get the phone number
			
			Person entry = new Person(first, last, address, number); //create a new person
			book.Append(entry); //add them to the book
			
		} else {
			
			System.out.println("invalid name"); //if not, tell the user
			
		}
		
	}
	
	private static void removePerson(List<Person> book) { //method to remove a person
		
		String name[] = input("Please input a name to remove (first last):").split(" "); //get the name of the person
		
		for (int i = 0; i < book.Size(); i++) { //loop through the list
			
			boolean found = book.GetValue(i).checkName(name[0], name[1]); //check if the name matches
			if (found) {
				
				book.Remove(i); //if yes, remove the value
				System.out.println("Found and removed entry"); //tell the user
				
			}
			
		}
		
	}
	
	private static void create() { //method to create a phonebook
		
		String name = input("Input a name for the phonebook:"); //get the name for the book
		
		int index = search(name); //check if the name exists
		
		if (index > -1) { 
			
			System.out.println("this phonebook already exists"); //if it does, tell the user
			
		} else {
			
			names.Append(name); //add the name to the name list
			List<Person> book = new List<>(); //create a new book
			phoneBook.Append(book); //add the book to the phonebook list
			
		}
		
	}
	
	private static void delete() { //method to delete a phonebook
		
		String name = input("What phonebook do you want to delete:"); //get the name of the book to delete
		
		int index = search(name); //search for the name
		if (index > -1) { //if found
			
			names.Remove(index); //remove the name
			phoneBook.Remove(index); //remove the list
			System.out.println("found and removed the entry"); //tell the user
			
		} else {
			
			System.out.println("entry not found"); //if not found, tell the user
			
		}
		
	}
	
	private static void help() { //method to print a help message
		
		System.out.println("Command list:");
		System.out.println("help: display this list");
		System.out.println("create: add a new phonebook");
		System.out.println("view: view all phonebooks");
		System.out.println("edit: edit a phonebook");
		System.out.println("delete: delete a phonebook");
		System.out.println("exit: quit the program");
		
	}
	
	private static void editHelp() { //method to print a help message for edit commands
		
		System.out.println("Edit command list:");
		System.out.println("help: display this list");
		System.out.println("create: add a new value to the phonebook");
		System.out.println("view: view all entries");
		System.out.println("edit: edit an entry");
		System.out.println("delete: delete an entry");
		System.out.println("exit: exit to main");
		
	}
	
	static String input(String message) { //create an easy way to get user input
		System.out.println(message); //print prompt
		return scanner.nextLine(); // return user input
	}
	
}
