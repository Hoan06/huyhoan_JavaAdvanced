package btth;

public record User(int id , String username , String email) {
    @Override
    public String toString() {
        return "User{" +
                "ID : " + id + "\n" +
                "Username : " + username + "\n" +
                "Email : '" + email + "\n" +
                '}';
    }
}
