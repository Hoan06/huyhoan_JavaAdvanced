package ra.presentation;

import ra.business.EmployeeBusiness;

import java.util.Scanner;

public class EmployeeView {
    private static final EmployeeBusiness empBusiness = EmployeeBusiness.getInstance();

    public static void showMenuEmployee() {
       Scanner sc = new Scanner(System.in);
       int choice;

       do {
           System.out.print("""
                ========================QUẢN LÍ NHÂN VIÊN========================
                1. Hiển thị danh sách toàn bộ nhân viên .
                2. Thêm mới nhân viên .
                3. Cập nhật thông tin nhân viên theo mã nhân viên .
                4. Xóa nhân viên theo mã nhân viên .
                5. Tìm kiếm nhân viên theo tên .
                6. Lọc danh sách nhân viên xuất sắc .
                7. Sắp xếp danh sách nhân viên giảm dần theo lương .
                8. Thoát .
                """);
           System.out.print("Lựa chọn của bạn : ");
           choice = Integer.parseInt(sc.nextLine());

           switch (choice) {
               case 1:
                   displayALLEmployees();
                   break;
               case 2:
                   createEmployee();
                   break;
               case 3:
                   updateEmployeeById();
                   break;
               case 4:
                   delEmployeeById();
                   break;
               case 5:
                   searchEmployeesByName();
                   break;
               case 6:
                   filterEmployee();
                   break;
               case 7:
                   sortEmployeeSalaryDesc();
                   break;
               case 8:
                   System.out.println("Chương trình kết thúc");
                   break;
               default:
                   System.out.println("Lựa chọn không hợp lệ !");
           }
       } while (choice != 8);
    }

    private static void createEmployee() {
        empBusiness.addEmployee();
    }

    private static void displayALLEmployees() {
        empBusiness.displayAllEmployees();
    }

    private static void updateEmployeeById() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id nhân viên muốn cập nhật : ");
        String idUpdate = sc.nextLine();
        System.out.println("""
                Bạn muốn cập nhật : 
                1. Name
                2. Age
                3. Salary
                """);
        System.out.print("Lựa chọn của bạn : ");
        int choose =  Integer.parseInt(sc.nextLine());
        empBusiness.updateEmployee(idUpdate, choose);
    }

    private static void delEmployeeById() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập id nhân viên mới xóa : ");
            String idDel = sc.nextLine();
            empBusiness.deleteEmployee(idDel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void searchEmployeesByName() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập tên nhân viên muốn tìm : ");
            String nameSearch = sc.nextLine();
            empBusiness.searchEmployee(nameSearch);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void filterEmployee(){
        empBusiness.filterEmployeesGood();
    }

    private static void sortEmployeeSalaryDesc(){
        empBusiness.sortEmployeeSalary();
    }
}
