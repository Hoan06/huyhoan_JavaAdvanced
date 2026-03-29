package hoanhuy.business.service;

import hoanhuy.business.dao.IOrderDao;
import hoanhuy.business.dao.IOrderDaoImpl;
import hoanhuy.business.dao.IOrderItemsDao;
import hoanhuy.business.dao.IOrderItemsDaoImpl;
import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.Order;
import hoanhuy.model.entity.OrderItem;
import hoanhuy.model.entity.OrderItemStatus;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class IOrderItemServiceImpl implements IOrderItemsService{
    private static final IOrderItemsDao orderItemsDao = new IOrderItemsDaoImpl();
    private static final IOrderDao orderDao = new IOrderDaoImpl();

    @Override
    public List<OrderItem> getAllOrderItems(int orderId) {
        List<OrderItem> list = orderItemsDao.findAllByOrderId(orderId);
        return list;
    }

    @Override
    public void orderFood(int orderId, int foodId, int quantity) {
        OrderItem orderItem = new OrderItem(orderId,foodId,quantity, OrderItemStatus.PENDING_APPROVAL);
        boolean result = orderItemsDao.insert(orderItem);
        if (result) {
            System.out.println("Gọi món thành công .");
        } else {
            System.out.println("Gọi món thất bại !");
        }
    }

    @Override
    public int findOrderId(Account account) {
        Order order = orderDao.findByCustomerId(account.getAccountId());
        if (order == null) {
            return -1;
        }
        return order.getId();
    }

    @Override
    public void cancelOrderItems() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã món muốn hủy gọi : ");
        int idCancel = Integer.parseInt(sc.nextLine());
        if (Validator.isValidInt(idCancel)) {
            System.out.println(Color.YELLOW + "Mã món không hợp lệ !" + Color.RESET);
            return;
        }
        OrderItem orderItem = orderItemsDao.findOrderItemsById(idCancel);
        if (orderItem == null) {
            System.out.println(Color.YELLOW + "Bạn không đặt món nào có mã như này !" + Color.RESET);
            return;
        }
        if (!orderItem.getStatus().equals(OrderItemStatus.PENDING_APPROVAL)) {
            System.out.println(Color.YELLOW + "Món đã được duyệt không thể hủy món !" +  Color.RESET);
            return;
        }
        boolean result = orderItemsDao.delete(idCancel);
        if (result) {
            System.out.println("Hủy món thành công .");
        } else {
            System.out.println("Hủy món thất bại !");
        }
    }

}
