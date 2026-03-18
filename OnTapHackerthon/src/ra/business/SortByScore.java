package ra.business;

import ra.entity.User;

import java.util.Comparator;
import java.util.List;

public class SortByScore implements SortStrategy {

    @Override
    public void sort(List<User> users) {
        users.sort(Comparator.comparingDouble(User::getScore).reversed());
    }
}
