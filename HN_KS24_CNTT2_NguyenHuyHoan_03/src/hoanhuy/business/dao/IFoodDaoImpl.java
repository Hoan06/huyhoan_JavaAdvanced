package hoanhuy.business.dao;

import hoanhuy.model.entity.MenuItem;
import hoanhuy.model.entity.MenuItemType;
import hoanhuy.utils.Color;
import hoanhuy.utils.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IFoodDaoImpl implements IFoodDao {
    private MenuItem mapToFood(ResultSet resultSet) throws SQLException {
        return new MenuItem(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getBigDecimal("price"),
                MenuItemType.valueOf(resultSet.getString("type").toUpperCase()),
                resultSet.getInt("stock")
        );
    }


    @Override
    public List<MenuItem> findAll() {
        String sql = "select id , name , price , type , stock from menu_items";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            List<MenuItem> list = new ArrayList<>();
            while (rs.next()) {
                MenuItem menuItem = mapToFood(rs);
                list.add(menuItem);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
    }

    @Override
    public MenuItem findById(int id) {
        String sql = "select id , name , price , type , stock from menu_items where id = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if  (rs.next()) {
                return mapToFood(rs);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
        return null;
    }

    @Override
    public boolean insert(MenuItem menuItem) {
        String sql = "insert into menu_items(name, price, type, stock) values (?,?,?,?)";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, menuItem.getName());
            ps.setBigDecimal(2, menuItem.getPrice());
            ps.setString(3, menuItem.getType().name());
            ps.setInt(4, menuItem.getStock());
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public boolean update(int id , BigDecimal newPrice) {
        String sql = "update menu_items set price = ? where id = ?";
        try (
            Connection conn = DBConnection.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setBigDecimal(1, newPrice);
            ps.setInt(2, id);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from menu_items where id = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public List<MenuItem> findByName(String name) {
        String sql = "select id , name , price , type , stock from menu_items where name = ?";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<MenuItem> list = new ArrayList<>();
            while(rs.next()) {
                MenuItem menuItem = mapToFood(rs);
                list.add(menuItem);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
    }

    @Override
    public List<MenuItem> findByPage(int page, int pageSize) {
        String sql = "select id, name, price, type, stock from menu_items limit ? offset ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            int offset = (page - 1) * pageSize;
            ps.setInt(1, pageSize);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();
            List<MenuItem> list = new ArrayList<>();

            while (rs.next()) {
                list.add(mapToFood(rs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
    }

    @Override
    public int countFoods() {
        String sql = "select count(*) from menu_items";
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
    public boolean updateStock(int idFoods, int quantity) {
        String sql = "update menu_items set stock = stock - ? where id = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, quantity);
            ps.setInt(2, idFoods);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

}
