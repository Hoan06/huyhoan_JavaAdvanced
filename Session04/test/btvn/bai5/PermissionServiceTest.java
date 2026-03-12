package btvn.bai5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermissionServiceTest {

    PermissionService permissionService = new PermissionService();
    User user;

    @AfterEach
    void tearDown() {
        user = null; // dọn object giả
    }


    @Test
    void adminCanDeleteUser() {
        user = new User("admin", Role.ADMIN);

        boolean result = permissionService.canPerformAction(user, Action.DELETE_USER);

        assertTrue(result);
    }

    @Test
    void adminCanLockUser() {
        user = new User("admin", Role.ADMIN);

        boolean result = permissionService.canPerformAction(user, Action.LOCK_USER);

        assertTrue(result);
    }

    @Test
    void adminCanViewProfile() {
        user = new User("admin", Role.ADMIN);

        boolean result = permissionService.canPerformAction(user, Action.VIEW_PROFILE);

        assertTrue(result);
    }


    @Test
    void moderatorCannotDeleteUser() {
        user = new User("mod", Role.MODERATOR);

        boolean result = permissionService.canPerformAction(user, Action.DELETE_USER);

        assertFalse(result);
    }

    @Test
    void moderatorCanLockUser() {
        user = new User("mod", Role.MODERATOR);

        boolean result = permissionService.canPerformAction(user, Action.LOCK_USER);

        assertTrue(result);
    }

    @Test
    void moderatorCanViewProfile() {
        user = new User("mod", Role.MODERATOR);

        boolean result = permissionService.canPerformAction(user, Action.VIEW_PROFILE);

        assertTrue(result);
    }


    @Test
    void userCannotDeleteUser() {
        user = new User("user", Role.USER);

        boolean result = permissionService.canPerformAction(user, Action.DELETE_USER);

        assertFalse(result);
    }

    @Test
    void userCannotLockUser() {
        user = new User("user", Role.USER);

        boolean result = permissionService.canPerformAction(user, Action.LOCK_USER);

        assertFalse(result);
    }

    @Test
    void userCanViewProfile() {
        user = new User("user", Role.USER);

        boolean result = permissionService.canPerformAction(user, Action.VIEW_PROFILE);

        assertTrue(result);
    }
}