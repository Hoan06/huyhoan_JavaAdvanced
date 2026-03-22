package ra.business;

import ra.dao.EmployeeDaoImpl;
import ra.dao.IEmployeeDao;
import ra.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeBusiness {
    private static EmployeeBusiness instance = new EmployeeBusiness();
    private static final IEmployeeDao employeeDao = new EmployeeDaoImpl();

    public static EmployeeBusiness getInstance() {
        if (instance == null) {
            instance = new EmployeeBusiness();
        }
        return instance;
    }

    public Optional<Employee> findEmployeeById(String idInput){
        return employeeDao.findAll().stream().filter(employee -> employee.getEmpId().equals(idInput)).findFirst();
    }

    public void addEmployee(){
        Scanner sc = new Scanner(System.in);
        int count = 0;
        while(true){
            System.out.printf("Thêm nhân viên lần %d : \n" , count + 1);
            System.out.print("Bạn có muốn tiếp tục thêm nhân viên không ( Yes / No ) : ");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("No")){
                System.out.println("Thoát thêm thành công !");
                break;
            }
            count++;
            try {
                Employee employee = new Employee();
                employee = employee.inputData();
                employeeDao.insert(employee);
                System.out.println("Thêm nhân viên thành công !");
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void displayAllEmployees(){
        List<Employee> employees = employeeDao.findAll();
        try {
            if (employees.isEmpty()){
                System.out.println("Danh sách trống !");
                return;
            }
            System.out.println("---------------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-5s | %-20s | \n" , "ID" , "Name" , "Age" , "Salary");
            System.out.println("---------------------------------------------------------------------");
            for (Employee employee : employees){
                employee.displayData();
            }
            System.out.println("---------------------------------------------------------------------");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateEmployee(String idUpdate , int type){
        Scanner sc = new Scanner(System.in);
        try {
            Employee employee = new Employee();
            boolean check = false;
            for (Employee em : employeeDao.findAll()){
                if (em.getEmpId().equals(idUpdate)){
                    employee = em;
                    check = true;
                    break;
                }
            }
            if (!check){
                System.out.println("Không tìm thấy nhân viên muốn cập nhật !");
                return;
            }

            switch (type){
                case 1:
                    System.out.print("Nhập tên mới : ");
                    String newName = sc.nextLine();
                    employeeDao.updateNameById(idUpdate, newName);
                    System.out.println("Cập nhật thông tin thành công !");
                    break;
                case 2:
                    System.out.print("Nhập tuổi mới : ");
                    int age = Integer.parseInt(sc.nextLine());
                    if (age < 18){
                        System.out.println("Tuổi phải lớn hơn 18 !");
                        return;
                    }
                    employeeDao.updateAgeById(idUpdate, age);
                    System.out.println("Cập nhật thông tin thành công !");
                    break;
                case 3:
                    System.out.print("Nhập lương mới : ");
                    double salary = Double.parseDouble(sc.nextLine());
                    if (salary <= 0){
                        System.out.println("Lương phải lớn hơn 0 !");
                        return;
                    }
                    employeeDao.updateSalaryById(idUpdate, salary);
                    System.out.println("Cập nhật thông tin thành công !");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ !");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteEmployee(String idDelete){
        try {
            boolean checkDel = employeeDao.delete(idDelete);
            if (checkDel){
                System.out.println("Xóa nhân viên thành công .");
            } else {
                System.out.println("Xóa nhân viên thất bại . Không thấy nhân viên !");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchEmployee(String nameSearch){
        try {
            List<Employee> searchEmployees = employeeDao.searchByName(nameSearch);
            if (searchEmployees.isEmpty()){
                System.out.println("Không tìm thấy nhân viên nào !");
                return;
            }
            System.out.println("---------------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-5s | %-20s | \n" , "ID" , "Name" , "Age" , "Salary");
            System.out.println("---------------------------------------------------------------------");
            for (Employee employee : searchEmployees){
                employee.displayData();
            }
            System.out.println("---------------------------------------------------------------------");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void filterEmployeesGood(){
        try {
            List<Employee> filterEmployees = employeeDao.filterEmployeesBySalary();
            if (filterEmployees.isEmpty()){
                System.out.println("Không có nhân viên xuất sắc nào !");
                return;
            }
            System.out.println("---------------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-5s | %-20s | \n" , "ID" , "Name" , "Age" , "Salary");
            System.out.println("---------------------------------------------------------------------");
            for (Employee employee : filterEmployees){
                employee.displayData();
            }
            System.out.println("---------------------------------------------------------------------");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sortEmployeeSalary(){
        List<Employee> employees = employeeDao.sortBySalary();
        if (employees.isEmpty()){
            System.out.println("Danh sách trống !");
            return;
        }
        System.out.println("Sắp xếp lương giảm dần thành công !");
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-5s | %-20s | \n", "ID", "Name", "Age", "Salary");
        System.out.println("---------------------------------------------------------------------");
        for (Employee employee : employees){
            employee.displayData();
        }
        System.out.println("---------------------------------------------------------------------");
    }

}
