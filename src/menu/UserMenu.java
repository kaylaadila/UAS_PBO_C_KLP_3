package menu;

import model.Registration;
import model.Student;
import model.User;
import service.AppContext;
import util.InputHelper;

/** Menu User: lihat data, mendaftar jadi siswa, lihat riwayat pendaftaran dan nilai. */
public class UserMenu {
    private final User user;
    private final AppContext ctx;

    /** Membuat menu untuk user yang sedang login. */
    public UserMenu(User user, AppContext ctx) {
        this.user = user;
        this.ctx = ctx;
    }

    /** Menampilkan menu utama user. */
    public void show() {
        String[] labels = {"Lihat Profil", "Lihat Daftar Kelas", "Lihat Daftar Mapel",
                "Pendaftaran Siswa", "Riwayat Pendaftaran", "Lihat Nilai & Rata-rata"};
        InputHelper.runMenu("MENU USER - " + user.getFullName(), labels, "Logout", choice -> {
