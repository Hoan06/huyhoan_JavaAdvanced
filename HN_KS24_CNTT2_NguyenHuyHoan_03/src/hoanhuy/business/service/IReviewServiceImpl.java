package hoanhuy.business.service;

import hoanhuy.business.dao.IReviewDao;
import hoanhuy.business.dao.IReviewDaoImpl;
import hoanhuy.model.entity.Review;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class IReviewServiceImpl implements IReviewService {
    private static final IReviewDao reviewDao = new IReviewDaoImpl();

    @Override
    public List<Review> getAllReviews() {
        return reviewDao.findAll();
    }

    @Override
    public List<Review> getReviewsByPage(int page, int pageSize) {
        return reviewDao.findByPage(page, pageSize);
    }

    @Override
    public int countReviews() {
        return reviewDao.countReviews();
    }

    @Override
    public void insertReview(int idCus) {
        Scanner sc = new Scanner(System.in);
        int star = Validator.getInt(sc , "Lựa chọn số sao ( 1 - 5 ) : ");
        if (Validator.isValidInt(star) || star > 5){
            System.out.println(Color.YELLOW + "Số sao đánh giá không hợp lệ !" + Color.RESET);
            return;
        }
        System.out.print("Bình luận : ");
        String comment =  sc.nextLine();
        Review review = new Review();
        review.setCustomerId(idCus);
        review.setStars(star);
        review.setComment(comment);
        boolean result = reviewDao.insert(review);
        if (result) {
            System.out.println("Đăng đánh giá thành công .");
        } else {
            System.out.println("Đăng đánh giá thất bại !");
        }
    }
}
