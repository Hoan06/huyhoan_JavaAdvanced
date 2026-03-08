package btvn.bai7;
import java.io.FileNotFoundException;

public class UserService {

    public static String registerUser(String name, String ageInput, String email)
            throws InvalidAgeException, InvalidEmailException {

        int age;

        try {
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            throw e;
        }

        if (age < 18) {
            throw new InvalidAgeException("Tuổi phải từ 18 trở lên.");
        }

        if (!email.contains("@")) {
            throw new InvalidEmailException("Email không hợp lệ (thiếu ký tự @).");
        }

        return "Name: " + name + ", Age: " + age + ", Email: " + email;
    }

    public static void saveUserToFile(String userData) throws FileNotFoundException {
        throw new FileNotFoundException("Không tìm thấy file để lưu dữ liệu.");
    }
}
