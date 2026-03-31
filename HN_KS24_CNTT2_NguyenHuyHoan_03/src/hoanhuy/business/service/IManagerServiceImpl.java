package hoanhuy.business.service;

import hoanhuy.business.dao.IAccountDao;
import hoanhuy.business.dao.IAccountDaoImpl;
import hoanhuy.model.entity.*;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class IManagerServiceImpl implements IManagerService {
    private static final ITableService tableService = new ITableServiceImpl();
    private static final IOrderItemsService orderItemsService = new IOrderItemServiceImpl();
    private static final IOrderService orderService = new IOrderServiceImpl();
    private static final IFoodService foodService = new IFoodServiceImpl();
    private static final IAccountDao accountDao = new IAccountDaoImpl();

    @Override
    public void browseOrderItems(){
        Scanner sc = new Scanner(System.in);
        try {
            int idTable = Validator.getInt(sc , "Nhập id bàn muốn duyệt đơn : ");
            if (Validator.isValidInt(idTable)) {
                System.out.println(Color.YELLOW + "Id bàn không hợp lệ !" + Color.RESET);
                return;
            }
            Order order = orderService.findByTable(idTable);
            if (order == null) {
                System.out.println(Color.YELLOW + "Không tìm thấy bàn nào !" + Color.RESET);
                return;
            }
            List<OrderItem> lists = orderItemsService.findAllByOrderIdManager(order.getId());
            if (lists.isEmpty()) {
                System.out.println("Bàn chưa có món nào cần duyệt !");
                return;
            }
            System.out.println("+------+----------+-------------+----------+--------------------+");
            System.out.printf("| %-4s | %-8s | %-11s | %-8s | %-18s |%n",
                    "ID", "Order ID", "Id món", "Số lượng", "Trạng thái");
            System.out.println("+------+----------+-------------+----------+--------------------+");

            for (OrderItem orderItem : lists) {
                System.out.printf("| %-4d | %-8d | %-11d | %-8d | %-18s |%n",
                        orderItem.getId(),
                        orderItem.getOrderId(),
                        orderItem.getMenuItemId(),
                        orderItem.getQuantity(),
                        orderItem.getStatus());
            }

            System.out.println("+------+----------+-------------+----------+--------------------+");
            int orderItemsId = Validator.getInt(sc , "Nhập mã thực đơn : ");
            if (Validator.isValidInt(orderItemsId)) {
                System.out.println(Color.YELLOW + "Mã thực đơn không hợp lệ !" + Color.RESET);
                return;
            }
            OrderItem orderItem = orderItemsService.findOrderItemById(orderItemsId);
            if (orderItem.getStatus() != OrderItemStatus.PENDING_APPROVAL) {
                System.out.println(Color.YELLOW + "Bạn đã duyệt đơn này rồi !" + Color.RESET);
                return;
            }
            System.out.println("""
                    1. Duyệt đơn
                    2. Từ chối đơn
                    """);
            int choose =  Validator.getInt(sc , "Lựa chọn của bạn : ");
            if (Validator.isValidInt(choose) || choose > 2) {
                System.out.println(Color.YELLOW + "Lựa chọn không hợp lệ !" + Color.RESET);
                return;
            }
            if (choose == 1) {
                MenuItem menuItems = foodService.findFoodById(orderItem.getMenuItemId());
                if (menuItems.getStock() < orderItem.getQuantity()) {
                    System.out.println(Color.YELLOW + "Số lượng trong kho không đủ , hãy từ chối đơn !" + Color.RESET);
                    return;
                }
                orderItemsService.updateStatusTrue(orderItemsId , menuItems.getId() , orderItem.getQuantity());
            } else if (choose == 2) {
                orderItemsService.updateStatusFalse(orderItemsId);
            }
        } catch (NumberFormatException e) {
            System.out.println(Color.RED + "Lỗi : " + e.getMessage() + Color.RESET);
            return;
        }
    }

    @Override
    public void banAccount() {
        Scanner sc = new Scanner(System.in);
        int idAccountBan = Validator.getInt(sc , "Nhập id tài khoản bạn muốn khóa : ");
        if (Validator.isValidInt(idAccountBan)) {
            System.out.println(Color.YELLOW + "Id không hợp lệ !" + Color.RESET);
            return;
        }
        Account account = accountDao.findAccountById(idAccountBan);
        if (account == null) {
            System.out.println(Color.YELLOW + "Tài khoản không tồn tại !" + Color.RESET);
            return;
        }
        if (idAccountBan == 1){
            System.out.println(Color.YELLOW + "Không thể khóa tài khoản manager !" + Color.RESET);
            return;
        }
        if (account.isBan()){
            System.out.println(Color.YELLOW + "Tài khoản đã được khóa rồi !" + Color.RESET);
            return;
        }
        boolean result = accountDao.banAccount(account);
        if (result) {
            System.out.println("Khóa tài khoản thành công .");
        } else {
            System.out.println("Khóa tài khoản thất bại .");
        }
    }

    @Override
    public void payOrder() {
        Scanner sc = new Scanner(System.in);
        int idTable = Validator.getInt(sc , "Nhập id bàn muốn thực hiện thanh toán ( Chỉ tính những món đang nấu/đã pv ) : ");
        if (Validator.isValidInt(idTable)) {
            System.out.println(Color.YELLOW + "Id bàn nhập không hợp lệ !" + Color.RESET);
            return;
        }
        Order order = orderService.findByTable(idTable);
        if (order == null) {
            System.out.println(Color.YELLOW + "Bàn chưa được ai đặt !" + Color.RESET);
            return;
        }
        Table table = tableService.findTableById(order.getTableId());
        if (table.isEmpty()) {
            System.out.println(Color.YELLOW + "Bàn chưa được ai đặt !" + Color.RESET);
            return;
        }
        List<OrderItem> orderItems = orderItemsService.getAllOrderItems(order.getId());
        if (orderItems == null) {
            System.out.println(Color.YELLOW + "Khách hàng chưa gọi món nào !" + Color.RESET);
            return;
        }
        BigDecimal totalPay = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getStatus() == OrderItemStatus.COOKING
                    ||  orderItem.getStatus() == OrderItemStatus.READY
                    || orderItem.getStatus() == OrderItemStatus.SERVED) {
                MenuItem menuItem = foodService.findFoodById(orderItem.getMenuItemId());
                BigDecimal quantity = BigDecimal.valueOf(orderItem.getQuantity());
                BigDecimal itemAmount = quantity.multiply(menuItem.getPrice());
                totalPay = totalPay.add(itemAmount);
            }
        }
        System.out.println("Tổng tiền bàn " + idTable + " cần thanh toán là : " + totalPay);
        System.out.println(""" 
                Chắc chắn muốn thanh toán ?
                 1. Sure
                 2. No
                """);
        int choose = Validator.getInt(sc , "Lựa chọn của bạn : ");
        switch (choose) {
            case 1:
                tableService.returnTable(idTable);
                orderService.updateIsPay(order.getId());
                removeOrderItems(orderItems);
                System.out.println("Thanh toán thành công .");
                break;
            case 2:
                System.out.println("Đã hủy thanh toán !");
                break;
            default:
                System.out.println(Color.YELLOW + "Lựa chọn không hợp lệ !" + Color.RESET);
        }
    }

    private static void removeOrderItems(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getStatus() == OrderItemStatus.PENDING_APPROVAL ||
            orderItem.getStatus() == OrderItemStatus.PENDING ||
            orderItem.getStatus() == OrderItemStatus.REJECTED)  {
                orderItemsService.deleteOrderItemById(orderItem.getId());
            }
        }
    }
}
