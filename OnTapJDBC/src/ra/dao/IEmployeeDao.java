package ra.dao;

import ra.entity.Employee;

import java.util.List;

public interface IEmployeeDao {
    List<Employee> findAll();
    boolean insert(Employee e);
    boolean delete(String idInput);
    boolean updateNameById(String id , String newName);
    boolean updateAgeById(String id , int newAge);
    boolean updateSalaryById(String id , Double newSalary);
    List<Employee> searchByName(String name);
    List<Employee> filterEmployeesBySalary();
    List<Employee> sortBySalary();
}
