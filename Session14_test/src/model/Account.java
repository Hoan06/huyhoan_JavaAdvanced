package model;

public class Account {
    private String accountId;
    private String fullName;
    private double balance;

    public Account() {
    }

    public Account(String accountId, String fullName, double balance) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
