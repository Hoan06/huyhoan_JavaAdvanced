package hoanhuy.business.service;

import hoanhuy.model.entity.Review;

import java.util.List;

public interface IReviewService {
    List<Review> getAllReviews();
    void insertReview(int idCus);
    List<Review> getReviewsByPage(int page, int pageSize);
    int countReviews();
}
