package functional;

@FunctionalInterface
public interface IFunctional {
    // khái niệm functional interface : có duy nhất 1 phương thức trừu tượng
    // tất cả functional interface trong java 8 đều nằm ở gói : import java.util.functional;
    void save();
}
