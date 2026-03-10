package stream;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer i : list) {
            if (i == 2) {
                list.remove(i);
            }
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 2) {
                iterator.remove();
            }
        }

        // Tạo stream : Array và Collection
        int[] arr = {1, 2, 3, 4, 5};
        IntStream streamInt = Arrays.stream(arr);

        Stream<Integer> stream2 = list.stream();

        // Thao tác trung gian : sorted , limit , distinct , filter , map , ...
        IntStream stream3 = streamInt.filter(value -> value % 2 == 0);

        // Thao tác đầu cuối : sum , foreach , reduce , count , findFirst , Findany , anyMatch , allMatch , min , max
        OptionalDouble avg = stream3.average();
        System.out.println(avg);

        // Ví dụ : tạo 1 list 1000 số nguyên ngẫu nhiên từ -200 đến 200
        List<Integer> randomList = Stream.generate(() -> new Random().nextInt(400) - 200)
                .limit(1000).toList();
        // 1. Lọc và in các số nguyên dương ra màn hình
        System.out.println("==================================");
        randomList.stream().filter(value -> value > 0).forEach(System.out::println);
        // 2. Loại bỏ các số trùng lặp
        System.out.println("==================================");
        List<Integer> listDistinct = randomList.stream().distinct().toList();
        System.out.println(listDistinct);
        // 3. Sắp xếp các số theo thứ tự từ lớn -> bé
        System.out.println("==================================");
        randomList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        // 4. Tính min , max
        System.out.println("==================================");
        Integer min = randomList.stream().min(Comparator.comparingInt(o -> o)).get();
        Integer max = randomList.stream().max(Comparator.comparingInt(o -> o)).get();
        System.out.println("Min : " + min);
        System.out.println("Max : " + max);
        // 5. Tính tổng tất cả phần tử
        System.out.println("==================================");
        Integer total = randomList.stream().reduce(0, Integer::sum);
        // 6. Kiểm tra giá trị n nhập vào có tồn tại trong ds không
        boolean isExist = randomList.stream().anyMatch(value -> value == 100);
        System.out.println("Số 100 : " + isExist);
        // 7. Chuyển các số âm thành số đối của nó
        List<Integer> mapInteger = randomList.stream().map(value -> value < 0 ? -value : value).toList();
        System.out.println("MapInteger : " + mapInteger);
    }
}
