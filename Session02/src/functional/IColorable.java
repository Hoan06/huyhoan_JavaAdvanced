package functional;

public interface IColorable {
    String RED = "abcdxyz";
    void printColor(String color);

    // Java 8
    default void draw(){ // không bắt buộc lớp con phải ghi đè
        System.out.println("Tô màu !!!");
    }

    default void fly(){

    }

    static void erase(){ // thuộc về interface , không truy cập được thông qua đối tượng
        System.out.println("Xóa");
    }

    private void toStr(){ // java 9

    }
}
