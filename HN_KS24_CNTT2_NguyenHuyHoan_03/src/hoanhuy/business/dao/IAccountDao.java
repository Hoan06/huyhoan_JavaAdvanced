package hoanhuy.business.dao;

import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.MenuItem;

import java.util.List;

public interface IAccountDao {
    List<Account> getAllAccounts();
    boolean insertAccount(Account account);
    boolean findAccountByUsername(String username);
    List<Account> findAll();
    Account findAccountLogin(String username, String password);
    List<Account> findByPage(int page, int pageSize);
    int countAccounts();
    boolean banAccount(Account account);
    Account findAccountById(int accountId);
}
