public class Node {

  private int value; //create data vars
  private Person data;
  
  protected Node low; //create lower nodes
  protected Node high;

  public Node(int value, Person data) {

    this.value = value; //create a new node
    this.data = data;

  }

  public void insertNode(Node node) { //method to insert a node
    
    if (node.value > value) { //if higher, insert to high


      if (high == null) {

        high = node; //if high is null, set it to node

      } else {

        high.insertNode(node); //if not, insert into high

      }

    } else if (node.value < value) {

      if (low == null) {

        low = node; //if high is null, set it to node

      } else {

        low.insertNode(node); //if not, insert into low

      }

    }

  }

  public Node getNode(int index) { //method to get a node at an index

    Node node = this; //set node to self

    while (node.value != index && node != null) { //while the index has not been reached

      if (index < node.value) {

        node = node.low; //continue on to either low or high

      } else {

        node = node.high;

      }

    }

    return node; //once reached, return node

  }

  public Node getParent(int index) { //method to get the parent of a node

    Node parent = null;
    Node node = this;

    while (node.value != index && node != null) {

      parent = node; //mostly the same as get node

      if (index < node.value) {

        node = node.low;

      } else {

        node = node.high;

      }

    }

    return parent; //return the parent node

  }

  public void deleteNode(int index) { //method to delete a node

    Node node = getNode(index); //get the node and parent
    Node parent = getParent(index);
    Node high = null;
    Node low = null; //create node for high and low

    if (index == 0) {

      System.out.println("cannot delete root"); //if index is 0, do nothing
      return;

    }

    if (node.low != null) {
      
      low = new Node(node.low.value, node.low.data); //if there is a high, create a copy
    
    }

    if (node.high != null) {
    
      high = new Node(node.high.value, node.high.data); //if there is a low, create a copy

    }

    if (parent.low == node) {

      parent.low = null; //remove the node from the parent

    } else if (parent.high == node) {

      parent.high = null;

    } 

    insertNode(low); //add lower nodes back into the tree
    insertNode(high);

  }

  public void inOrder(Node node) { //method to print in order

    if (node == null) {

      return;

    }

    inOrder(node.low);
    
    if (node.data != null) {
      System.out.println(node.data);
      System.out.printf("ID: %d\n", node.value);
    }
    
    inOrder(node.high);

  }

  public void preOrder(Node node) { //method to print pre order

    if (node == null) {

      return;

    }

    if (node.data != null) {
      System.out.println(node.data);
      System.out.printf("ID: %d\n", node.value);
    }

    preOrder(node.low);
    preOrder(node.high);

  }

  public void postOrder(Node node) { //method to print post order

    if (node == null) {

      return;

    }

    postOrder(node.low);
    postOrder(node.high);

    if (node.data != null) {
      System.out.println(node.data);
      System.out.printf("ID: %d\n", node.value);
    }

  }


  public void inOrder() { inOrder(this); } //set all order methods to run on self
  public void preOrder() { preOrder(this); }
  public void postOrder() { postOrder(this); }

  public int getValue() { //getters and setters

    return value;

  }

  public void setValue(int value) {

    this.value = value;

  }

  public Person getData() {

    return data;

  }

}
