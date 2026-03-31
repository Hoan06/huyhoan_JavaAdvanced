package hoanhuy.business.service;

import hoanhuy.business.dao.*;
import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.Order;
import hoanhuy.model.entity.OrderItem;
import hoanhuy.model.entity.OrderItemStatus;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOrderItemServiceImpl implements IOrderItemsService{
    private static final IOrderItemsDao orderItemsDao = new IOrderItemsDaoImpl();
    private static final IOrderDao orderDao = new IOrderDaoImpl();
    private static final IFoodDao foodDao = new IFoodDaoImpl();

    @Override
    public List<OrderItem> getAllOrderItems(int orderId) {
        List<OrderItem> list = orderItemsDao.findAllByOrderId(orderId);
        return list;
    }

    @Override
    public List<OrderItem> findAllOfChef() {
        List<OrderItem> list = orderItemsDao.findAllOfChef();
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
        Order order = orderDao.findActiveOrderByCustomerId(account.getAccountId());
        if (order == null) {
            return -1;
        }
        return order.getId();
    }


    @Override
    public void cancelOrderItems() {
        Scanner sc = new Scanner(System.in);
        int idCancel = Validator.getInt(sc , "Nhập mã món muốn hủy gọi : ");
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

    @Override
    public void updateStatusTrue(int idOrderItems , int idFood , int quantity) {
        boolean result = orderItemsDao.update(idOrderItems , "PENDING");
        if (result) {
            boolean resultUpdate = foodDao.updateStock(idFood, quantity);
            if (resultUpdate) {
                System.out.println("Duyệt đơn thành công .");
            } else {
                System.out.println("Duyệt đơn thất bại !");
            }
        } else {
            System.out.println("Duyệt đơn thất bại !");
        }
    }

    @Override
    public void updateStatusFalse(int idOrderItems) {
        boolean result = orderItemsDao.update(idOrderItems , "REJECTED");
        if (result) {
            System.out.println("Từ chối đơn thành công .");
        } else {
            System.out.println("Từ chối đơn thất bại !");
        }
    }

    @Override
    public OrderItem findOrderItemById(int idOrderItems) {
        return orderItemsDao.findOrderItemsById(idOrderItems);
    }

    @Override
    public List<OrderItem> findAllByOrderIdManager(int idOrderItems) {
        List<OrderItem> list = orderItemsDao.findAllByOrderIdManager(idOrderItems);
        return list;
    }

    @Override
    public void updateStatusByChef() {
        Scanner sc = new Scanner(System.in);
        int idFoodUpdate = Validator.getInt(sc , "Nhập mã thực đơn muốn cập nhật trạng thái lên : ");
        if (Validator.isValidInt(idFoodUpdate)) {
            System.out.println(Color.YELLOW + "Id nhập không hợp lệ !" + Color.RESET);
            return;
        }
        OrderItem orderItem = orderItemsDao.findOrderItemsById(idFoodUpdate);
        if (orderItem == null) {
            System.out.println(Color.YELLOW + "Thực đơn không tồn tại !" + Color.RESET);
            return;
        }
        if (orderItem.getStatus().equals(OrderItemStatus.PENDING_APPROVAL) || orderItem.getStatus().equals(OrderItemStatus.REJECTED)) {
            System.out.println(Color.YELLOW + "Thực đơn chưa được duyệt !" + Color.RESET);
            return;
        }

        switch (orderItem.getStatus()) {
            case PENDING :
                boolean result1 = orderItemsDao.update(orderItem.getId() , "COOKING");
                if (result1) {
                    System.out.println("Cập nhật tiến độ thành công .");
                } else {
                    System.out.println("Cập nhật tiến độ thất bại !");
                }
                break;
            case COOKING:
                boolean result2 = orderItemsDao.update(orderItem.getId() , "READY");
                if (result2) {
                    System.out.println("Cập nhật tiến độ thành công .");
                } else {
                    System.out.println("Cập nhật tiến độ thất bại !");
                }
                break;
            case READY:
                boolean result3 = orderItemsDao.update(orderItem.getId() , "SERVED");
                if (result3) {
                    System.out.println("Cập nhật tiến độ thành công .");
                } else {
                    System.out.println("Cập nhật tiến độ thất bại !");
                }
                break;
            case SERVED:
                System.out.println(Color.YELLOW + "Món ăn đã được phục vụ cho khách , không thể cập nhật tiến độ nữa !" +  Color.RESET);
                break;
        }
    }

    @Override
    public void deleteOrderItemById(int idOrderItems) {
        orderItemsDao.delete(idOrderItems);
    }
}
