package hoanhuy.business.dao;

import hoanhuy.model.dto.Revenue;
import hoanhuy.utils.Color;
import hoanhuy.utils.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IRevenueDaoImpl implements IRevenueDao {
    private Revenue mapToRevenue(ResultSet rs) throws SQLException {
        BigDecimal totalRevenue = rs.getBigDecimal("total_revenue");
        if (totalRevenue == null) {
            totalRevenue = BigDecimal.ZERO;
        }

        return new Revenue(
                rs.getString("time_label"),
                rs.getInt("total_orders"),
                totalRevenue
        );
    }

    @Override
    public List<Revenue> getRevenuesByMonth(int month, int year) {
        String sql = """
            select
                DATE_FORMAT(o.created_at, '%d/%m/%Y') as time_label,
                count(distinct o.id) as total_orders,
                sum(oi.quantity * mi.price) as total_revenue
            from orders o
            join order_items oi on o.id = oi.order_id
            join menu_items mi on oi.menu_item_id = mi.id
            where o.isPay = true
              and month(o.created_at) = ?
              and year(o.created_at) = ?
            group by DATE_FORMAT(o.created_at, '%d/%m/%Y')
            order by min(o.created_at)
            """;

        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, month);
            ps.setInt(2, year);

            ResultSet rs = ps.executeQuery();
            List<Revenue> list = new ArrayList<>();

            while (rs.next()) {
                list.add(mapToRevenue(rs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn doanh thu: " + e.getMessage() + Color.RESET);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Revenue> getRevenuesByYear(int year) {
        String sql = """
                select month(o.created_at) as time_label,
                count(distinct o.id) as total_orders,
                sum(oi.quantity * mi.price) as total_revenue
                from orders o
                join order_items oi on o.id = oi.order_id
                join menu_items mi on oi.menu_item_id = mi.id
                where o.isPay = true and year(o.created_at) = ?
                group by month(o.created_at)
                order by month(o.created_at)
                """;
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, year);

            ResultSet rs = ps.executeQuery();
            List<Revenue> list = new ArrayList<>();

            while (rs.next()) {
                list.add(mapToRevenue(rs));
            }

            return list;
        } catch (SQLException e) {
            System.out.println(Color.RED + "Lỗi truy vấn doanh thu: " + e.getMessage() + Color.RESET);
            return new ArrayList<>();
        }
    }
}
