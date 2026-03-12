package btvn.bai5;

public class PermissionService {

    public boolean canPerformAction(User user, Action action) {

        Role role = user.getRole();

        switch (role) {

            case ADMIN:
                return true;

            case MODERATOR:
                if (action == Action.DELETE_USER) {
                    return false;
                }
                return true;

            case USER:
                return action == Action.VIEW_PROFILE;

            default:
                return false;
        }
    }
}