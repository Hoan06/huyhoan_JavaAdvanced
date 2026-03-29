package hoanhuy.business.service;

import hoanhuy.business.dao.*;
import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.Order;
import hoanhuy.model.entity.OrderItem;
import hoanhuy.model.entity.Table;
import hoanhuy.utils.Color;
import hoanhuy.utils.DBConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ITableServiceImpl implements ITableService {
    private static final ITableDao tableDao = new ITableDaoImpl();
    private static final IOrderDao orderDao = new IOrderDaoImpl();
    private static final IOrderItemsDao  orderItemsDao = new IOrderItemsDaoImpl();

    @Override
    public List<Table> getAllTables() {
        List<Table> tables = tableDao.findAllTable();
        return tables;
    }

    @Override
    public void insertTable() {
        Scanner sc = new Scanner(System.in);
        Table table = new Table();
        while (true) {
            boolean flag = true;
            System.out.print("Nhập sức chứa của bàn : ");
            int limit = Integer.parseInt(sc.nextLine());
            if (limit <= 0) {
                System.out.println(Color.YELLOW + "Sức chứa phải lớn hơn 0 !" + Color.RESET);
                flag = false;
            }
            if (flag) {
                table.setEmpty(true);
                table.setLimited(limit);
                break;
            }
        }
        boolean result = tableDao.insert(table);
        if (result) {
            System.out.println("Thêm bàn thành công .");
        } else {
            System.out.println("Thêm bàn thất bại !");
        }
    }

    @Override
    public void updateTable() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id bàn muốn cập nhật : ");
        int idInput = Integer.parseInt(sc.nextLine());
        if (idInput <= 0) {
            System.out.println(Color.YELLOW + "Id không hợp lệ !" + Color.RESET);
            return;
        }
        if (tableDao.findById(idInput) == null) {
            System.out.println(Color.YELLOW + "Bàn không tồn tại !" + Color.RESET);
            return;
        }
        System.out.print("Nhập sức chứa mới : ");
        int limit = Integer.parseInt(sc.nextLine());
        if (limit <= 0) {
            System.out.println(Color.YELLOW + "Sức chứa phải lớn hơn 0 !" + Color.RESET);
            return;
        }
        boolean result = tableDao.update(idInput, limit);
        if (result) {
            System.out.println("Cập nhật sức chứa bàn thành công .");
        } else {
            System.out.println("Cập nhật sức chứa bàn thất bại !");
        }
    }

    @Override
    public void deleteTable() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id bàn muốn xóa : ");
        int id = Integer.parseInt(sc.nextLine());
        if (id <= 0) {
            System.out.println(Color.YELLOW + "Id không hợp lệ !" + Color.RESET);
            return;
        }
        if (tableDao.findById(id) == null) {
            System.out.println(Color.YELLOW + "Bàn không tồn tại !" + Color.RESET);
            return;
        }
        boolean result = tableDao.delete(id);
        if (result) {
            System.out.println("Xóa bàn thành công .");
        } else {
            System.out.println("Xóa bàn thất bại !");
        }
    }

    @Override
    public void returnTable(Account account) {
        Order order = orderDao.findByCustomerId(account.getAccountId());
        List<OrderItem> orderItems = orderItemsDao.findAllByOrderId(order.getId());
        if (!order.isPay() && !orderItems.isEmpty()) {
            System.out.println(Color.YELLOW + "Bạn chưa thanh toán hóa đơn !" +  Color.RESET);
            return;
        }
        int idTable = order.getTableId();
        boolean result = tableDao.returnTable(idTable);
        if (result) {
            System.out.println("Trả bàn thành công .");
        } else {
            System.out.println("Trả bàn thất bại !");
        }
    }

    @Override
    public List<Table> getTableByPage(int page, int pagesize) {
        return tableDao.findByPage(page, pagesize);
    }

    @Override
    public int countTable() {
        return tableDao.countTable();
    }
}
