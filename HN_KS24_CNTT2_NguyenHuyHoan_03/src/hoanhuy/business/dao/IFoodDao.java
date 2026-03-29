package hoanhuy.business.dao;

import hoanhuy.model.entity.MenuItem;

import java.math.BigDecimal;
import java.util.List;

public interface IFoodDao {
    List<MenuItem> findAll();
    MenuItem findById(int id);
    boolean insert(MenuItem menuItem);
    boolean update(int id , BigDecimal newPrice);
    boolean delete(int id);
    List<MenuItem> findByName(String name);
    List<MenuItem> findByPage(int page, int pageSize);
    int countFoods();
}
