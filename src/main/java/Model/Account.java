package Model;

public class Account {

    private String id;
    private String name;
    private double balance;


    public Account(String  id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public double getBalance() {return balance;}


    public void setBalance(double balance) {this.balance = balance;}

}
