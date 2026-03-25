package ra.dao;

import ra.entity.Employee;
import ra.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {
    private Employee mapToEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getString("empId"),
                rs.getString("empName"),
                rs.getInt("age"),
                rs.getDouble("salary")
        );
    }


    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "select * from employee";

        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee e = mapToEmployee(rs);
                list.add(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean insert(Employee e) {
        String sql = String.format(
                "insert into employee(empId, empName, age, salary) values (?, ?, ?, ?)");


        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, e.getEmpId());
            stmt.setString(2, e.getEmpName());
            stmt.setInt(3, e.getAge());
            stmt.setDouble(4, e.getSalary());
            int count = stmt.executeUpdate();
            return count > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean delete(String idInput) {
        String sql = String.format("delete from employee where empId = ?");

        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,idInput);
            int count = stmt.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateNameById(String id , String newName) {
        String sql = String.format("update employee set empName = ? where empId = ?");

        try (
                Connection conn = DBConnection.openConnection();
                Statement stmt = conn.createStatement();
        ) {
            int count = stmt.executeUpdate(sql);
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateAgeById(String id, int newAge) {
        String sql = String.format("update employee set age = '%d' where empId = '%s'", newAge, id);

        try (
                Connection conn = DBConnection.openConnection();
                Statement stmt = conn.createStatement();
        ) {
            int count = stmt.executeUpdate(sql);
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateSalaryById(String id, Double newSalary) {
        String sql = String.format("update employee set salary = '%s' where empId = '%s'", Double.toString(newSalary).replace(",", "."), id);

        try (
                Connection conn = DBConnection.openConnection();
                Statement stmt = conn.createStatement();
        ) {
            int count = stmt.executeUpdate(sql);
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> searchByName(String name) {
        List<Employee> list = new ArrayList<>();
        String sql = String.format("select * from employee where lower(empName) like lower('%%%s%%')", name);

        try (
                Connection conn = DBConnection.openConnection();
                Statement stmt = conn.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Employee e = mapToEmployee(rs);
                list.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Employee> filterEmployeesBySalary() {
        List<Employee> list = new ArrayList<>();
        String sql = String.format("select * from employee where salary > 15000000");

        try (
                Connection conn = DBConnection.openConnection();
                Statement stmt = conn.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Employee e = mapToEmployee(rs);
                list.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Employee> sortBySalary() {
        List<Employee> list = new ArrayList<>();
        String sql = String.format("select * from employee order by salary desc");

        try (
             Connection conn = DBConnection.openConnection();
             Statement stmt = conn.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Employee e = mapToEmployee(rs);
                list.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
