package hoanhuy.business.service;

import hoanhuy.business.dao.IFoodDao;
import hoanhuy.business.dao.IFoodDaoImpl;
import hoanhuy.model.entity.MenuItem;
import hoanhuy.model.entity.MenuItemType;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class IFoodServiceImpl implements IFoodService {
    IFoodDao foodDao = new IFoodDaoImpl();

    @Override
    public List<MenuItem> getAllFood() {
        return foodDao.findAll();
    }

    @Override
    public void addFood() {
        Scanner sc = new Scanner(System.in);
        MenuItem menuItem = new MenuItem();

        while (true) {
            boolean flag = true;

            System.out.print("Nhập tên món ăn : ");
            String name = sc.nextLine();

            BigDecimal price;
            try {
                System.out.print("Nhập giá món ăn : ");
                price = new BigDecimal(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Color.YELLOW + "Giá món ăn phải là số hợp lệ !" + Color.RESET);
                continue;
            }

            System.out.print("Nhập loại (FOOD , DRINK) : ");
            String type = sc.nextLine().toUpperCase();

            int stock;
            try {
                stock = Validator.getInt(sc , "Nhập số lượng : ");
            } catch (NumberFormatException e) {
                System.out.println(Color.YELLOW + "Số lượng phải là số nguyên hợp lệ !" + Color.RESET);
                continue;
            }

            if (Validator.isValidate(name)) {
                System.out.println(Color.YELLOW + "Tên món ăn không được để trống !" + Color.RESET);
                flag = false;
            }

            if (Validator.isValidate(type) || (!type.equals("FOOD") && !type.equals("DRINK"))) {
                System.out.println(Color.YELLOW + "Loại món ăn không hợp lệ !" + Color.RESET);
                flag = false;
            }

            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println(Color.YELLOW + "Giá món ăn phải lớn hơn 0 !" + Color.RESET);
                flag = false;
            }

            if (stock <= 0) {
                System.out.println(Color.YELLOW + "Số lượng phải lớn hơn 0 !" + Color.RESET);
                flag = false;
            }

            if (flag) {
                menuItem.setName(name);
                menuItem.setPrice(price);
                menuItem.setType(MenuItemType.valueOf(type));
                menuItem.setStock(stock);
                break;
            }
        }

        boolean result = foodDao.insert(menuItem);
        if (result) {
            System.out.println("Thêm món ăn thành công.");
        } else {
            System.out.println("Thêm món ăn thất bại!");
        }
    }


    @Override
    public void updateFood() {
        Scanner sc = new Scanner(System.in);
        int id = Validator.getInt(sc , "Nhập id món ăn cập nhật : ");
        if (foodDao.findById(id) == null){
            System.out.println(Color.YELLOW + "Món ăn không tồn tại !" + Color.RESET);
            return;
        }
        BigDecimal price;
        try {
            System.out.print("Nhập giá mới cho món ăn : ");
            price = new BigDecimal(sc.nextLine());
            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println(Color.YELLOW + "Giá phải lớn hơn 0 !" + Color.RESET);
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(Color.YELLOW + "Giá món ăn phải là số hợp lệ !" + Color.RESET);
            return;
        }

        boolean result = foodDao.update(id,price);
        if  (result) {
            System.out.println("Cập nhật giá món ăn thành công .");
        } else {
            System.out.println("Cập nhật giá món ăn thất bại !");
        }
    }

    @Override
    public void deleteFood() {
        Scanner sc = new Scanner(System.in);
        int id = Validator.getInt(sc , "Nhập id món ăn muốn xóa : ");
        if (foodDao.findById(id) == null){
            System.out.println(Color.YELLOW + "Món ăn không tồn tại !" + Color.RESET);
            return;
        }
        boolean result = foodDao.delete(id);
        if (result) {
            System.out.println("Xóa món ăn thành công .");
        } else {
            System.out.println("Xóa món ăn thất bại !");
        }
    }

    @Override
    public void findByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên món ăn muốn tìm : ");
        String name = sc.nextLine();
        if (Validator.isValidate(name)) {
            System.out.println(Color.YELLOW + "Tên không được để trống !" + Color.RESET);
            return;
        }
        List<MenuItem> menuItems = foodDao.findByName(name);
        if (menuItems.isEmpty()){
            System.out.println("Không tìm thấy món ăn nào !");
            return;
        }
        MenuItem.printHeader();
        for (MenuItem menuItem : menuItems) {
            menuItem.displayData();
        }
        MenuItem.printFooter();
    }

    @Override
    public MenuItem findFoodById(int id) {
        MenuItem menuItem =  foodDao.findById(id);
        return menuItem;
    }

    @Override
    public List<MenuItem> getFoodsByPage(int page, int pageSize) {
        return foodDao.findByPage(page, pageSize);
    }

    @Override
    public int countFoods() {
        return foodDao.countFoods();
    }

}
