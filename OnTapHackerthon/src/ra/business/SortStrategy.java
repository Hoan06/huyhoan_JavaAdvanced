package ra.business;

import ra.entity.User;

import java.util.List;

public interface SortStrategy {
    void sort(List<User> users);
}
