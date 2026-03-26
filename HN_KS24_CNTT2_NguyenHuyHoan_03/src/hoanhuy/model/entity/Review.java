package hoanhuy.model.entity;

public class Review {
    private int id;
    private int customerId;
    private int stars;
    private String comment;

    public Review() {
    }

    public Review(int id, int customerId, int stars, String comment) {
        this.id = id;
        this.customerId = customerId;
        this.stars = stars;
        this.comment = comment;
    }

    public Review(int customerId, int stars, String comment) {
        this.customerId = customerId;
        this.stars = stars;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}