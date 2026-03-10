package datetime;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Trước java 8
        Date date = new Date(); // trả về thời gian của hệ thống theo mili giây
        System.out.println("Mili giây : " + date.getTime());
        System.out.println(date);

        // Tạo 1 ngày 02-02-2007


//        DateTimeAPI
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        LocalDate bornIn = LocalDate.of(2006 , 3 , 13);

        Period age = Period.between(bornIn, today);
        System.out.println("Age : " + age);

        LocalDate nextDate = bornIn.plusDays(100);
        System.out.println(nextDate);

        // Optional
        Optional<Integer> op1 = Optional.empty();
        Optional<Integer> op2 = Optional.of(1);
        Optional<Integer> op3 = Optional.ofNullable(null); // Truyền null hoặc không null đều được

        if (op3.isPresent()) {
            // có giá trị khác null
            // Lấy ra giá trị
            System.out.println(op3.get());
        }

        // Lấy trực tiếp giá trị mà k cần kiểm tra đk nếu nó tồn tại hoặc 1 giá trị mặc định
        Integer value = op3.orElse(100);
        System.out.println(value);

        // Lấy hoặc ném ngoại lệ
        Integer val = op3.orElseThrow(() -> new RuntimeException("Lỗi gì đó ..."));

        // Ví dụ :
        List<Integer> randomList = Stream.generate(() -> new Random().nextInt(10)).limit(10).toList();

        // Tìm giá trị lớn nhất trong ds
        // Tìm giá trị đầu tiên chia hết cho 3 trong ds
        Integer max = randomList.stream().max(Integer::compareTo).orElse(Integer.MIN_VALUE);

        Integer result = randomList.stream().filter(i -> i % 3 == 0).findFirst().orElseThrow(() -> new RuntimeException("Dũng bị gay"));
    }
}
