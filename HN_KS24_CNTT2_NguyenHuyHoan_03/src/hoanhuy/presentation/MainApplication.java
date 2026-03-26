package hoanhuy.presentation;

import hoanhuy.business.dao.IAccountDao;
import hoanhuy.business.dao.IAccountDaoImpl;
import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.Role;

import java.util.Scanner;

public class MainApplication {
    static void main(String[] args) {
        seedAdminAccount();
        runMenuRestaurant();
    }

    private static void runMenuRestaurant(){
        Scanner sc = new Scanner(System.in);
        RestaurantView.showMenuRestaurant();
    }

    public static void seedAdminAccount() {
        IAccountDao accountDao = new IAccountDaoImpl();
        if (!accountDao.findAccountByUsername("admin")) {
            Account admin = new Account();
            admin.setFullName("Administrator");
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRole(Role.MANAGER);
            admin.setBan(false);
            accountDao.insertAccount(admin);
        }
    }
}
