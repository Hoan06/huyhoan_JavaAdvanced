package ra.entity;

import ra.business.EmployeeBusiness;
import ra.validate.Validator;

import java.util.Optional;
import java.util.Scanner;

public class Employee {
    private String empId;
    private String empName;
    private int age;
    private double salary;

    public Employee(String empId, String empName, int age, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.age = age;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee inputData(){
        Scanner sc = new Scanner(System.in);
        EmployeeBusiness employeeBusiness = EmployeeBusiness.getInstance();

        while (true){
            try {
                Employee employee = new Employee();
                System.out.print("Nhập id cho nhân viên : ");
                employee.setEmpId(sc.nextLine());
                Optional<Employee> optionalCheck = employeeBusiness.findEmployeeById(employee.getEmpId());
                System.out.print("Nhập tên cho nhân viên : ");
                employee.setEmpName(sc.nextLine());
                System.out.print("Nhập tuổi cho nhân viên : ");
                employee.setAge(Integer.parseInt(sc.nextLine()));
                System.out.print("Nhập lương cho nhân viên : ");
                employee.setSalary(Double.parseDouble(sc.nextLine()));
                if (optionalCheck.isPresent()) {
                    System.out.println("Mã nhân viên đã tồn tại !");
                }
                if (employee.getEmpId().trim().isBlank()){
                    System.out.println("Id không được để trống !");
                }
                if (Validator.isCheckNull(employee.getEmpName())){
                    System.out.println("Tên nhân viên không được để trống !");
                }
                if (employee.getAge() < 18){
                    System.out.println("Tuổi nhân viên phải >= 18 !");
                }
                if (employee.getSalary() <= 0){
                    System.out.println("Lương nhân viên phải > 0 !");
                }
                if (!checkValidate(employee)){
                    return employee;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static boolean checkValidate(Employee employee){
        EmployeeBusiness employeeBusiness = EmployeeBusiness.getInstance();
        Optional<Employee> optionalCheck = employeeBusiness.findEmployeeById(employee.getEmpId());
        return optionalCheck.isPresent() || Validator.isCheckNull(employee.getEmpName()) || employee.getAge() < 18 || employee.getSalary() <= 0 || employee.getEmpId().trim().isBlank();
    }

    public void displayData(){
        System.out.printf("| %-10s | %-20s | %-5d | %-20.1f | \n" , this.getEmpId(), this.getEmpName(), this.getAge(), this.getSalary());
    }
}
