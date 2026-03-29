package hoanhuy.business.dao;

import hoanhuy.model.entity.Review;
import hoanhuy.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IReviewDaoImpl implements IReviewDao {
    public Review mapToReview(ResultSet resultSet) throws SQLException {
        return new Review(
                resultSet.getInt("id"),
                resultSet.getInt("customer_id"),
                resultSet.getInt("stars"),
                resultSet.getString("comment")
        );
    }

    @Override
    public List<Review> findAll() {
        String sql = "select id , customer_id , stars , comment from reviews";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                reviews.add(mapToReview(rs));
            }
            return reviews;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Review> findByPage(int page, int pageSize) {
        String sql = "select id, customer_id, stars, comment from reviews limit ? offset ?";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            int offset = (page - 1) * pageSize;
            ps.setInt(1, pageSize);
            ps.setInt(2, offset);

            ResultSet rs = ps.executeQuery();
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                reviews.add(mapToReview(rs));
            }
            return reviews;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countReviews() {
        String sql = "select count(*) from reviews";
        try (
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean insert(Review review) {
        String sql = "insert into reviews(customer_id, stars, comment) values(?, ?, ?)";
        try(
                Connection conn = DBConnection.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, review.getCustomerId());
            ps.setInt(2, review.getStars());
            ps.setString(3, review.getComment());
            int count =  ps.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
