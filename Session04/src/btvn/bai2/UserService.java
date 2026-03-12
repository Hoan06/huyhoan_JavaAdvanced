package btvn.bai2;

public class UserService {
    public boolean checkRegistrationAge(int age){
        if (age < 18 && age >= 0){
            return false;
        }
        if (age < 0){
            throw new IllegalArgumentException("Tuổi không được âm !");
        }
        return true;
    }
}
