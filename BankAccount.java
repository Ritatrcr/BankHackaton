public class BankAccount {
    private String name;
    private double balance;
    private String document;
    private String accountType;
    private String password;

    public BankAccount(String name, double balance, String document, String accountType, String password) {
        this.name = name;
        this.balance = balance;
        this.document = document;
        this.accountType = accountType;
        this.password = password;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}