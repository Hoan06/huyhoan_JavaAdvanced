public class Practice {
    // Kiểm tra số nguyên tố
    public static boolean checkElement(int a){
        if (a < 0){
            throw new IllegalArgumentException("");
        }
        if (a <= 1){
            return false;
        }
        for(int i = 2 ; i < a ; i++ ){
            if (a % i == 0){
                return false;
            }
        }
        return true;
    }

    // Phương thức tính số fibonaci thứ n
    public static int fibonaci(int n){
        if (n < 0) {
            throw new IllegalArgumentException("n phải là số > 0");
        };

        if (n == 0) return 0;
        if (n == 1) return 1;

        int a = 0 , b = 1;
        for (int i = 2 ; i <= n ; i++){
            int next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    // Xây dựng các test case cho tất cả các trường hợp có thể xảy ra của 2 phương thức
}
