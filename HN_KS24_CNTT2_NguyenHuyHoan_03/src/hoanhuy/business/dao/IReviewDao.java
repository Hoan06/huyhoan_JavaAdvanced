package hoanhuy.business.dao;

import hoanhuy.model.entity.Review;

import java.util.List;

public interface IReviewDao {
    List<Review> findAll();
    boolean insert(Review review);
    List<Review> findByPage(int page, int pageSize);
    int countReviews();
}
