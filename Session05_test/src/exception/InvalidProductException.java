package exception;

public class InvalidProductException extends Exception {

    public InvalidProductException() {
        super("Lỗi liên quan đến sản phẩm không hợp lệ");
    }

    public InvalidProductException(String message) {
        super(message);
    }

    public InvalidProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
