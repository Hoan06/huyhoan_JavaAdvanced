package hoanhuy.validate;

public class Validator {
    public static boolean isValidate(String str){
        return str.trim().isBlank();
    }

    public static boolean isValidInt(int input){
        return input <= 0;
    }
}
