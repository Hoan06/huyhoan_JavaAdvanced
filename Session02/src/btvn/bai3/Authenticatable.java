package btvn.bai3;

@FunctionalInterface
public interface Authenticatable {
    void getPassword(User user);

    default boolean isAuthenticated(User user) {
        return user.getPassword() != null || !user.getPassword().isBlank();
    }

    static void  encrypt(String rawPassword){
        System.out.println("Mã hóa mật khẩu");
    }
}
