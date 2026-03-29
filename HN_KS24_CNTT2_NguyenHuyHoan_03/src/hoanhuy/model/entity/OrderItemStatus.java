package hoanhuy.model.entity;

public enum OrderItemStatus {
    PENDING_APPROVAL, // dùng để xem quản lí có duyệt đơn order không
    REJECTED,
    PENDING,
    COOKING,
    READY,
    SERVED
}
