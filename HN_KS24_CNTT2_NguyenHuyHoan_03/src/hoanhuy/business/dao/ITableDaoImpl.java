package hoanhuy.business.dao;

import hoanhuy.model.entity.Table;
import hoanhuy.utils.Color;
import hoanhuy.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ITableDaoImpl implements ITableDao {
    private Table mapToTable(ResultSet resultSet) throws SQLException {
        return new Table(
                resultSet.getInt("id"),
                resultSet.getBoolean("isEmpty"),
                resultSet.getInt("limited")
        );
    }

    @Override
    public List<Table> findAllTable() {
        String sql = "select id , number , isEmpty , limited from tables";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ResultSet rs = ps.executeQuery();
            List<Table> list = new ArrayList<>();
            while(rs.next()){
                Table table = mapToTable(rs);
                list.add(table);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return  null;
        }
    }

    @Override
    public boolean insert(Table table) {
        String sql = "insert into tables ( isEmpty, limited) values ( ?, ?)";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setBoolean(1, table.isEmpty());
            ps.setInt(2, table.getLimited());
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public boolean update(int id, int numberUpdate) {
        String sql = "update tables set limited = ? where id = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, numberUpdate);
            ps.setInt(2, id);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public boolean updateIsEmpty(int idTable) {
        String sql = "update tables set isEmpty = ? where id = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setBoolean(1, false);
            ps.setInt(2, idTable);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from tables where id = ?";
        try(
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
    public boolean returnTable(int idTable) {
        String sql = "update tables set isEmpty = ? where id = ?";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setBoolean(1, true);
            ps.setInt(2, idTable);
            int count = ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return false;
        }
    }

    @Override
    public Table findById(int id) {
        String sql = "select id , isEmpty , limited from tables where id = ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapToTable(rs);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
        return null;
    }

    @Override
    public List<Table> findByPage(int page, int pageSize) {
        String sql = "select id, isEmpty , limited from tables limit ? offset ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            int offset = (page - 1) * pageSize;
            ps.setInt(1,pageSize);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            List<Table> list = new ArrayList<>();
            while(rs.next()){
                Table table = mapToTable(rs);
                list.add(table);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return null;
        }
    }

    @Override
    public int countTable() {
        String sql = "select count(*) from tables";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn dữ liệu" + e.getMessage() + Color.RESET);
            return 0;
        }
        return 0;
    }
}
