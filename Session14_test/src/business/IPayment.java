package business;

public interface IPayment {
    void pay(String accountIdSend , String accountIdReceive , double amount);
}
