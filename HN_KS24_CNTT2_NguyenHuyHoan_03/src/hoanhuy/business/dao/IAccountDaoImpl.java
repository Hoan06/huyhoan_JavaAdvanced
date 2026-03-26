package hoanhuy.business.dao;

import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.Role;
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
            throw new RuntimeException(e);
        }
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
                if (rs.getBoolean("isBan")) {
                    return null;
                }

                String storedPassword = rs.getString("password");
                boolean isValid = PasswordHasher.verifyPassword(password, storedPassword);

                if (isValid) {
                    return new Account(
                            rs.getInt("id"),
                            rs.getString("fullName"),
                            rs.getString("username"),
                            rs.getString("password"),
                            Role.valueOf(rs.getString("role")),
                            rs.getBoolean("isBan")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }


}
