package btvn.bai6;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<String>> list = List.of(
          List.of("java", "backend"),
          List.of("python", "data")
        );

        List<String> result = list.stream().flatMap(i -> i.stream()).toList();
        System.out.println(result);
    }
}
