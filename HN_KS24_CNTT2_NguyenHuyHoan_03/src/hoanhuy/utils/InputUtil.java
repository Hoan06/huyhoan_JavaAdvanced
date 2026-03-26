//package hoanhuy.utils;
//
//import org.jline.reader.LineReader;
//import org.jline.reader.LineReaderBuilder;
//import org.jline.terminal.Terminal;
//import org.jline.terminal.TerminalBuilder;
//
//
//public class InputUtil {
//    public static String inputPassword(String message) {
//        try {
//            Terminal terminal = TerminalBuilder.builder()
//                    .system(true)
//                    .build();
//
//            LineReader reader = LineReaderBuilder.builder()
//                    .terminal(terminal)
//                    .build();
//
//            return reader.readLine(message, '*');
//        } catch (Exception e) {
//            throw new RuntimeException("Không thể nhập mật khẩu", e);
//        }
//    }
//}
