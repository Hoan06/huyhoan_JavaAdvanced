package ra.business;

import ra.entity.Employee;

import java.util.Comparator;
import java.util.List;

public class SortEmployeeBySalary implements SortEmployee {
    @Override
    public void sort(List<Employee> employees) {
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
    }
}
