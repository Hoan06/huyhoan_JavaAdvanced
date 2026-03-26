package hoanhuy.model.entity;

import hoanhuy.business.dao.IAccountDao;
import hoanhuy.business.dao.IAccountDaoImpl;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.util.Scanner;

public class Account {
    private int accountId;
    private String fullName;
    private String username;
    private String password;
    private Role role;
    private boolean isBan;

    public Account() {
    }

    public Account(int accountId, String fullName, String username, String password, Role role, boolean isBan) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isBan = isBan;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBan() {
        return isBan;
    }

    public void setBan(boolean ban) {
        isBan = ban;
    }

    public static Account inputAccount() {
        Scanner sc = new Scanner(System.in);
        Account account = new Account();
        IAccountDao accountDao = new IAccountDaoImpl();

        while (true) {
            boolean check = true;

            System.out.print("Nhập tên của bạn: ");
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
                account.setRole(Role.CUSTOMER);
                account.setBan(false);
                break;
            }
        }

        return account;
    }
}

