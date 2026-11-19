package model;

/** Akun pengguna biasa; menjadi siswa jika NIS-nya terisi (mewarisi Account). */
public class User extends Account {
    private String nis = "";

    /** Membuat akun user. */
    public User(String username, String password, String fullName) {
        super(username, password, fullName);
    }
