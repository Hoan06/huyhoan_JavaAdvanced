package hoanhuy.business.dao;

import hoanhuy.model.entity.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> getAllAccounts();
    boolean insertAccount(Account account);
    boolean findAccountByUsername(String username);

    Account findAccountLogin(String username, String password);

}
