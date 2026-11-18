package model;

/** Akun administrator (mewarisi Account). */
public class Admin extends Account {
    /** Membuat akun admin. */
    public Admin(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    /** @return "ADMIN" */
    @Override
    public String getRole() { return "ADMIN"; }

    /** @return deskripsi khusus admin */
    @Override
    public String describe() { return "Administrator: " + getFullName() + " (" + getUsername() + ")"; }
}
