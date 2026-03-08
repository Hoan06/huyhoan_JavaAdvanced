package btvn.bai6;
import java.io.IOException;

public class UserService {

    public static void saveToFile(User user) throws IOException {
        throw new IOException("Không thể ghi dữ liệu người dùng vào file!");
    }

    public static void processUserData(User user) throws IOException {
        saveToFile(user);
    }
}
