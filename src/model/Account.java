package model;

/** Kelas abstrak dasar untuk semua akun (Admin dan User). */
public abstract class Account implements Storable {
    private final String username, password, fullName;

    /** Membuat akun baru. */
    public Account(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

