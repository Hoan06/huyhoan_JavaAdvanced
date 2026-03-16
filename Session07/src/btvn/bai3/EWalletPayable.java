package btvn.bai3;

public interface EWalletPayable extends PaymentMethod {
    void pay(double amount);
}
