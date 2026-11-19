package model;

/** Akun pengguna biasa; menjadi siswa jika NIS-nya terisi (mewarisi Account). */
public class User extends Account {
    private String nis = "";

    /** Membuat akun user. */
    public User(String username, String password, String fullName) {
        super(username, password, fullName);
    }
    
    /** @return NIS siswa (kosong jika belum jadi siswa) */
    public String getNis() { return nis; }
    /** Mengisi NIS saat pendaftaran disetujui admin. */
    public void setNis(String nis) { this.nis = nis; }
    /** @return true jika akun ini sudah terdaftar sebagai siswa */
    public boolean isStudent() { return !nis.isEmpty(); }

    /** @return "USER" */
    @Override
    public String getRole() { return "USER"; }

    /** @return NIS siswa yang terhubung */
    @Override
    public String getLinkedNis() { return nis; }

    /** @return deskripsi khusus user */
    @Override
    public String describe() {
        return "User: " + getFullName() + " (" + getUsername() + ", " + (isStudent() ? "NIS " + nis : "belum menjadi siswa") + ")";
    }
}
