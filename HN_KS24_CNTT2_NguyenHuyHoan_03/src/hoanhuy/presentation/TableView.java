package hoanhuy.presentation;

import hoanhuy.business.service.ITableService;
import hoanhuy.business.service.ITableServiceImpl;
import hoanhuy.model.entity.MenuItem;
import hoanhuy.model.entity.Table;
import hoanhuy.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class TableView {
    private static final ITableService tableService = new ITableServiceImpl();

    public static void showTableManagement() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("""
                ┌────────────────────────────────────────────────────────────┐
                │                    QUẢN LÝ BÀN ĂN                          │
                ├──────────────────────────────┬─────────────────────────────┤
                │     1. Thêm bàn              │     2. Hiển thị ds bàn      │
                ├──────────────────────────────┼─────────────────────────────┤
                │     3. Cập nhật số lượng     │     4. Xóa bàn              │
                ├──────────────────────────────┼─────────────────────────────┤
                │     5. Quay lại              │                             │
                └──────────────────────────────┴─────────────────────────────┘
                """);

            choice = Validator.getInt(sc,"Lựa chọn của bạn : ");

            switch (choice) {
                case 1:
                    tableService.insertTable();
                    break;
                case 2:
                    displayAllTable();
                    break;
                case 3:
                    tableService.updateTable();
                    break;
                case 4:
                    tableService.deleteTable();
                    break;
                case 5:
                    System.out.println("Quay lại menu quản lý.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 5);
    }

    private static void displayAllTable() {
        Scanner sc = new Scanner(System.in);
        int pageSize = 3;
        int currentPage = 1;

        int totalTable = tableService.countTable();
        if (totalTable == 0) {
            System.out.println("Danh sách bàn trống !");
            return;
        }

        int totalPage = (int) Math.ceil((double) totalTable /pageSize);

        while (true) {
            List<Table> listTables = tableService.getTableByPage(currentPage, pageSize);

            System.out.println("┌────────────────────────────────────────────────┐");
            System.out.println("│               DANH SÁCH BÀN ĂN                 │");
            System.out.println("├──────┬──────────────────────────┬──────────────┤");
            System.out.println("│ ID   │ Trạng thái bàn           │ Sức chứa     │");
            System.out.println("├──────┼──────────────────────────┼──────────────┤");

            for (Table item : listTables) {
                System.out.printf("│ %-4d │ %-24s │ %-12s │%n",
                        item.getId(),
                        item.isEmpty() ? "Còn trống" : "Đã được đặt",
                        item.getLimited());
            }

            int emptyRows = pageSize - listTables.size();
            for (int i = 0; i < emptyRows; i++) {
                System.out.printf("│ %-4s │ %-24s │ %-12s │%n", "", "", "");
            }

            System.out.println("├────────────────────────────────────────────────┤");
            System.out.printf("│                Trang %d / %d                     │%n" , currentPage, totalPage, "");
            System.out.println("├────────────────────────────────────────────────┤");
            System.out.println("│   P. Trang trước N. Trang sau  0. Thoát        │");
            System.out.println("└────────────────────────────────────────────────┘");

            System.out.print("Lựa chọn của bạn: ");
            String choice = sc.nextLine().trim().toUpperCase();

            switch (choice) {
                case "P":
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println("Bạn đang ở trang đầu tiên!");
                    }
                    break;
                case "N":
                    if (currentPage < totalPage) {
                        currentPage++;
                    } else {
                        System.out.println("Bạn đang ở trang cuối cùng!");
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
