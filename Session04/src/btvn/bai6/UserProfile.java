package btvn.bai6;

import java.time.LocalDate;

public class UserProfile {

    private String email;
    private LocalDate birthDate;
    private String name;

    public UserProfile(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }
}
