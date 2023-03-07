public class Person {

  String data[];

  public Person(String data[]) {

    this.data = data; //create new person

  }

  public String[] getData() {

    return data; //get data

  }

  public void setData(String data[]) {

    this.data = data; //set data

  }

  public String toString() {

    String value = "+--------( " + data[0] + " " + data[1] + " )--------+\n";
    value += "Street: " + data[2] + "\n";
    value += "Address: " + data[3] + "\n";
    value += "City: " + data[4] + "\n";
    value += "State: " + data[5] + "\n";
    value += "Zip code: " + data[6] + "\n\n";
    value += "Email: " + data[7] + "\n";
    value += "Phone number: " + data[8] + "\n\n";

    return value; //return string for printing

  }

}
