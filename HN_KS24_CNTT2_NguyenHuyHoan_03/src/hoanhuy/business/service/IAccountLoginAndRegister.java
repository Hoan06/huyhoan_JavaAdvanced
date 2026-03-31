package hoanhuy.business.service;

import hoanhuy.model.entity.Account;

import java.util.List;

public interface IAccountLoginAndRegister {
    Account login();
    void register();
    void addAccountChef();
    List<Account> findByPage(int page, int pageSize);
    int countAccounts();
}
