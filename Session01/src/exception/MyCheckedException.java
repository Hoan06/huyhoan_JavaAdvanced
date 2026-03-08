package exception;

public class MyCheckedException extends Exception{
    // phương thức và thuộc tính

    private int code;
    public MyCheckedException(String message){
        super(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
