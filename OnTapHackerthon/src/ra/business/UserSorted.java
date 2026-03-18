package ra.business;

import ra.entity.User;

import java.util.List;

public class UserSorted {
    private SortStrategy sortStrategy;

    public UserSorted() {
        this.sortStrategy = sortStrategy;
    }

    public SortStrategy getSortStrategy() {
        return sortStrategy;
    }

    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort(List<User> users) {
        sortStrategy.sort(users);
    }
}
