package hoanhuy.business.service;

import hoanhuy.business.dao.IAccountDao;
import hoanhuy.business.dao.IAccountDaoImpl;
import hoanhuy.model.entity.Account;

import java.util.Scanner;

public class IAccountLoginAndRegisterImpl implements  IAccountLoginAndRegister{
    IAccountDao accountDao = new IAccountDaoImpl();

    @Override
    public Account login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String userName = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        Account account = accountDao.findAccountLogin(userName, password);
        if (account != null) {
            System.out.println("Đăng nhập thành công.");
            return account;
        }

        System.out.println("Đăng nhập thất bại.");
        return null;
    }


    @Override
    public void register() {
        Account account = Account.inputAccount();
        boolean result = accountDao.insertAccount(account);
        if (result) {
            System.out.println("Đăng kí thành công .");
        } else {
            System.out.println("Đăng kí thất bại .");
        }
    }
}
