package btvn.bai2;

@FunctionalInterface
public interface PasswordValidator {
    boolean validate(String password);
}
