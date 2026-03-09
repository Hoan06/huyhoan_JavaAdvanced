package btvn.bai2;

public class Main implements PasswordValidator {
    public static void main(String[] args) {

    }

    @Override
    public boolean validate(String password) {
        return password.length() >= 8;
    }

    PasswordValidator isValid = (String password) -> password.length() >= 8;
}
