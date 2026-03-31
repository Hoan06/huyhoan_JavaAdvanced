package hoanhuy.business.service;

import hoanhuy.business.dao.IOrderDao;
import hoanhuy.business.dao.IOrderDaoImpl;
import hoanhuy.business.dao.ITableDao;
import hoanhuy.business.dao.ITableDaoImpl;
import hoanhuy.model.entity.Order;
import hoanhuy.model.entity.Table;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class IOrderServiceImpl implements IOrderService {
    private static final IOrderDao orderDao = new IOrderDaoImpl();
    private static final ITableDao tableDao = new ITableDaoImpl();

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public void orderTable(int idCus) {
        Scanner sc = new Scanner(System.in);
        Order currentOrder = orderDao.findActiveOrderByCustomerId(idCus);
        if (currentOrder != null) {
            System.out.println(Color.YELLOW + "Bạn đang có bàn chưa thanh toán, không thể đặt bàn mới !" + Color.RESET);
            return;
        }
        Order order = new Order();
        int idTable;
        List<Table> tables = tableDao.findAllTable();
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│               DANH SÁCH BÀN ĂN                 │");
        System.out.println("├──────┬──────────────────────────┬──────────────┤");
        System.out.println("│ ID   │ Trạng thái bàn           │ Sức chứa     │");
        System.out.println("├──────┼──────────────────────────┼──────────────┤");

        for (Table item : tables) {
            System.out.printf("│ %-4d │ %-24s │ %-12s │%n",
                    item.getId(),
                    item.isEmpty() ? "Còn trống" : "Đã được đặt",
                    item.getLimited());
        }
        System.out.println("└────────────────────────────────────────────────┘");
        while (true) {
            try {
                idTable = Validator.getInt(sc , "Nhập id bàn muốn đặt : ");
                break;
            } catch (NumberFormatException e) {
                System.out.println(Color.YELLOW + "Id bàn phải là số nguyên hợp lệ !" + Color.RESET);
            }
        }
        if (idTable <= 0) {
            System.out.println(Color.YELLOW + "Id bàn không hợp lệ !" + Color.RESET);
            return;
        }
        if (tableDao.findById(idTable) == null) {
            System.out.println(Color.YELLOW + "Bàn không tồn tại !" + Color.RESET);
            return;
        }
        Table table = tableDao.findById(idTable);
        if (!table.isEmpty()){
            System.out.println(Color.YELLOW + "Bàn đã được người khác đặt vui lòng chọn bàn khác !" + Color.RESET);
            return;
        }
        order.setCustomerId(idCus);
        order.setTableId(idTable);
        order.setPay(false);
        boolean result = orderDao.insert(order);
        if (result) {
            tableDao.updateIsEmpty(idTable);
            System.out.println("Đặt bàn thành công .");
        } else {
            System.out.println("Đặt bàn thất bại !");
        }
    }

    @Override
    public Order findByTable(int idTable) {
        return orderDao.findByTable(idTable);
    }

    @Override
    public void updateIsPay(int orderId) {
        orderDao.updateIsPay(orderId);
    }


}

