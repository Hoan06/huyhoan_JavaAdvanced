package hoanhuy.business.service;

import hoanhuy.model.entity.MenuItem;

import java.util.List;

public interface IFoodService {
    List<MenuItem> getAllFood();
    void addFood();
    void updateFood();
    void deleteFood();
    void findByName();
    boolean findFoodById(int id);
    List<MenuItem> getFoodsByPage(int page, int pageSize);
    int countFoods();
}
