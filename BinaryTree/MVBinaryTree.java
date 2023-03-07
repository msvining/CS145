// Marlen Vining
// 1/11/22
// Lab #6
//
//This program will do the following:
//Store data in a binary search tree
//

import java.util.Scanner; //Import scanner class for user input

public class MVBinaryTree {

  private static Scanner scanner = new Scanner(System.in); //creates a scanner to read user input

  static Node tree = new Node(0, null); //create root node

  public static void main(String args[]) {

    help(); //display help message

    while (true) { //main loop

      String cmd = input("Please input a command"); //get a command from the user

      if (cmd.equals("add")) {

        createNode(); //if add, add a node

      } else if (cmd.equals("remove")) {

        deleteNode(); //if remove, remove a node

      } else if (cmd.equals("modify")) {

        editValue(); //if edit, edit a node

      } else if (cmd.equals("lookup")) {

        lookUp(); //if lookup, get a node

      } else if (cmd.equals("list")) {

        list(); //if list, list nodes

      } else if (cmd.equals("help")) {

        help(); //if help, print help message

      } else { //if none, tell the user

        System.out.println("Invalid command. Type help for list of commands.");

      }

    }
    
  }
  
  static void help() { //method to print a help message

    System.out.println("list of commands:");
    System.out.println("add: add a new value");
    System.out.println("remove: delete a value");
    System.out.println("modify: change a value");
    System.out.println("lookup: display a value");
    System.out.println("list: shows all values");
    System.out.println("help: show this menu");

  }

  static void editValue() { //method to edit a value

    Node node = getIndex(); //get the node at an index
    System.out.println("Leave values you dont want to change blank"); 

    System.out.println(node.getData()); //print the node data

    String data[] = node.getData().getData(); //get the node data
    String newValues[] = newPerson().getData(); //get new data
    
    for (int i = 0; i < newValues.length; i++) {

      if (!newValues[i].equals("")) {

        data[i] = newValues[i]; //replace old values with new values

      }

    }

    node.getData().setData(data); //set the old data to the new data

  }

  static Person newPerson() { //method to create a new data entry

    String data[] = new String[9];
    String values[] = {"First name:", "Last name:", "Street:",
                        "Address:", "City:", "State:",
                        "Zip Code:", "Email:", "Phone Number:"}; //all prompts

    System.out.println("Please input the following values");

    for (int i = 0; i < values.length; i++) {

      data[i] = input(values[i]); //get all values

    }

    Person person = new Person(data); //return the new value
    return person;

  }

  static void createNode() { //method to create a new node

    Person person = newPerson(); //get data for node

    String strKey = input("Please input an integer key"); //get a key

    int key = Integer.parseInt(strKey); //create and add node to the tree
    Node node = new Node(key, person);
    tree.insertNode(node); 

  }

  static void deleteNode() { //method to delete node

    String stringIndex = input("Input the ID of the value to remove"); //get the ID to remove
    int index = Integer.parseInt(stringIndex); 

    Node node = tree.getNode(index); //get node

    System.out.println(node.getData()); //print data

    String confirm = input("type Yes to delete value");
    if (confirm.equals("Yes")) {

      tree.deleteNode(index); //if confirmed, delete node

    }

  }

  static void lookUp() { // method to look up a value

    Node node = getIndex(); // get and print data
    System.out.println(node.getData());

  }

  static void list() { //method to list values

    String cmd = input("What order would you like to view your entries in: (in, pre, post)"); //get type of order

    if (cmd.equals("in")) {

      tree.inOrder(); //if in print in order

    } else if (cmd.equals("pre")) {

      tree.preOrder(); //if pre, print pre order

    } else if (cmd.equals("post")) {

      tree.postOrder(); // if post, print post order

    } else {

      System.out.println("Invalid answer"); 

    }

  }

  static Node getIndex() { //method to get node at index

    String stringIndex = input("Input the ID of the value to remove");
    int index = Integer.parseInt(stringIndex); //get the index 

    Node node = tree.getNode(index);
    return node; //return node at index

  }

  static String input(String message) { //create an easy way to get user input
		System.out.println(message); //print prompt
		return scanner.nextLine(); // return user input
	}

}
