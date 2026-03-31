package hoanhuy.business.service;

import hoanhuy.business.dao.IAccountDao;
import hoanhuy.business.dao.IAccountDaoImpl;
import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.MenuItem;
import hoanhuy.model.entity.Role;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.util.List;
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

        return accountDao.findAccountLogin(userName, password);
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

    @Override
    public void addAccountChef() {
        Scanner sc = new Scanner(System.in);
        Account account = new Account();
        IAccountDao accountDao = new IAccountDaoImpl();

        while (true) {
            boolean check = true;

            System.out.print("Nhập tên của đầu bếp: ");
            String name = sc.nextLine();

            System.out.print("Nhập tên tài khoản đăng nhập: ");
            String username = sc.nextLine();

            System.out.print("Nhập mật khẩu cho tài khoản: ");
            String password = sc.nextLine();

            if (Validator.isValidate(name)) {
                System.out.println(Color.YELLOW + "Tên không được để trống!" + Color.RESET);
                check = false;
            }

            if (Validator.isValidate(username)) {
                System.out.println(Color.YELLOW + "Tên tài khoản không được để trống!" + Color.RESET);
                check = false;
            }

            if (Validator.isValidate(password)) {
                System.out.println(Color.YELLOW + "Mật khẩu không được để trống!" + Color.RESET);
                check = false;
            }

            if (accountDao.findAccountByUsername(username)) {
                System.out.println(Color.YELLOW + "Username đã tồn tại!" + Color.RESET);
                check = false;
            }

            if (check) {
                account.setFullName(name);
                account.setUsername(username);
                account.setPassword(password);
                account.setRole(Role.CHEF);
                account.setBan(false);
                break;
            }
        }
        boolean result = accountDao.insertAccount(account);
        if (result) {
            System.out.println("Tạo tài khoản thành công .");
        } else {
            System.out.println("Tạo tài khoản thất bại !");
        }
    }

    @Override
    public List<Account> findByPage(int page, int pageSize) {
        return accountDao.findByPage(page, pageSize);
    }

    @Override
    public int countAccounts() {
        return accountDao.countAccounts();
    }
}
