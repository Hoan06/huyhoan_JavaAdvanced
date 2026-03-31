package hoanhuy.business.dao;

import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.MenuItem;
import hoanhuy.model.entity.Role;
import hoanhuy.utils.Color;
import hoanhuy.utils.DBConnection;
import hoanhuy.utils.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IAccountDaoImpl implements IAccountDao {
    List<Account> accounts = new ArrayList<>();
    private Account mapToAccount(ResultSet resultSet) throws SQLException {
        return new Account(
                resultSet.getInt("id"),
                resultSet.getString("fullName"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                Role.valueOf(resultSet.getString("role").toUpperCase()),
                resultSet.getBoolean("isBan")
        );
    }

    @Override
    public boolean findAccountByUsername(String username) {
        String sql = "select * from accounts where username = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public List<Account> findAll() {
        String sql = "select id , fullName , username , password , role , isBan from accounts where role not in (?) ";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1,"MANAGER");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Account account = mapToAccount(rs);
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
        return accounts;
    }

    @Override
    public Account findAccountLogin(String username, String password) {
        String sql = "select * from accounts where username = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String storedPassword = rs.getString("password");
                boolean isValid = PasswordHasher.verifyPassword(password, storedPassword);

                if (isValid) {
                    return mapToAccount(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
        return null;
    }

    @Override
    public List<Account> findByPage(int page, int pageSize) {
        String sql = "select id , fullName , username , password , role , isBan from accounts where role not in (?) limit ? offset ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            int offset = (page - 1) * pageSize;
            ps.setString(1, "MANAGER");
            ps.setInt(2, pageSize);
            ps.setInt(3, offset);

            ResultSet rs = ps.executeQuery();
            List<Account> list = new ArrayList<>();

            while (rs.next()) {
                list.add(mapToAccount(rs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
    }

    @Override
    public int countAccounts() {
        String sql = "select count(*) from accounts";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return 0;
        }
        return 0;
    }

    @Override
    public boolean banAccount(Account account) {
        String sql = "update  accounts set isBan = ? where id = ?";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setBoolean(1, true);
            ps.setInt(2, account.getAccountId());
            int count =  ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public Account findAccountById(int accountId) {
        String sql = "select * from accounts where id = ?";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapToAccount(rs);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
        return null;
    }


    @Override
    public List<Account> getAllAccounts() {
        String sql = "select * from accounts";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Account account = mapToAccount(rs);
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
        return accounts;
    }

    @Override
    public boolean insertAccount(Account account) {
        String sql = "insert into accounts(fullName , username , password , role , isBan) values ( ?, ?, ?, ?, ?)";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, account.getFullName());
            ps.setString(2, account.getUsername());
            ps.setString(3, PasswordHasher.hashPassword(account.getPassword()));
            ps.setString(4, account.getRole().name());
            ps.setBoolean(5, account.isBan());

            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }


}
