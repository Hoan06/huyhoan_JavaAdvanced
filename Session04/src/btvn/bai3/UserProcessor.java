package btvn.bai3;

public class UserProcessor {
    public String checkEmail(String email){
        if (email.toLowerCase().endsWith("@gmail.com")){
            return email.toLowerCase();
        }
        throw new IllegalArgumentException("Thiếu @ hoặc gmail.com");
    }
}
