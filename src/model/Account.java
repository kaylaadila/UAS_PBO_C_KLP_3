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

/** @return username akun */
    public String getUsername() { return username; }
    /** @return nama lengkap pemilik akun */
    public String getFullName() { return fullName; }
    /** @return true jika password yang diketik cocok */
    public boolean checkPassword(String input) { return password.equals(input); }

    /** @return peran akun; wajib diisi oleh subclass (polymorphism) */
    public abstract String getRole();

    /** @return NIS yang terhubung dengan akun (kosong jika tidak ada) */
    public String getLinkedNis() { return ""; }

    /** @return deskripsi singkat akun; bisa di-override subclass */
    public String describe() { return getRole() + " - " + username; }

    /** @return format satu baris untuk disimpan ke file */
    @Override
    public String toFileString() {
        return getRole() + "|" + username + "|" + password + "|" + fullName + "|" + getLinkedNis();
    }

    /** @return deskripsi akun */
    @Override
    public String toString() { return describe(); }

    /** Membuat objek Admin atau User dari satu baris file; null jika format salah. */
    public static Account fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length < 5) return null;
        if (p[0].equals("ADMIN")) return new Admin(p[1], p[2], p[3]);
        if (p[0].equals("USER")) {
            User user = new User(p[1], p[2], p[3]);
            user.setNis(p[4]);
            return user;
        }
        return null;
    }
}
