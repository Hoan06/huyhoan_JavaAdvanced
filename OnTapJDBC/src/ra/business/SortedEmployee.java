package ra.business;

import ra.entity.Employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedEmployee {
    private SortEmployee sortEmployee;

    public SortedEmployee(SortEmployee employee) {
        this.sortEmployee = employee;
    }

    public SortEmployee getEmployee() {
        return sortEmployee;
    }

    public void setEmployee(SortEmployee employee) {
        this.sortEmployee = employee;
    }

    public void sort(List<Employee> employees) {
        sortEmployee.sort(employees);
    }
}
