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
            switch (choice) {
                case 1: showProfile(); break;
                case 2: InputHelper.printList(ctx.getClasses().getAll()); break;
                case 3: InputHelper.printList(ctx.getSubjects().getAll()); break;
                case 4: registerAsStudent(); break;
                case 5: InputHelper.printList(ctx.getRegistrations().getByUsername(user.getUsername())); break;
                default: showGrades();
            }
        });
        System.out.println("Anda telah logout.");
    }

    /** @return data siswa milik user. @throws IllegalArgumentException jika belum terdaftar sebagai siswa */
    private Student requireStudent() {
        if (!user.isStudent()) throw new IllegalArgumentException("Anda belum terdaftar sebagai siswa.");
        return ctx.getStudents().requireStudent(user.getNis());
    }

    /** Menampilkan profil akun dan data siswa (jika sudah terdaftar). */
    private void showProfile() {
        InputHelper.printHeader("PROFIL SAYA");
        System.out.println(user.describe());
        if (user.isStudent()) {
            Student s = requireStudent();
            System.out.println("Kelas   : " + s.getClassId());
            System.out.println("Alamat  : " + s.getAddress());
            System.out.println("Telepon : " + s.getPhone());
        }
    }

    /** Mengajukan pendaftaran menjadi siswa (transaksi User). */
    private void registerAsStudent() {
        InputHelper.printHeader("PENDAFTARAN SISWA");
        if (user.isStudent()) throw new IllegalArgumentException("Anda sudah terdaftar dengan NIS " + user.getNis() + ".");
        if (ctx.getRegistrations().hasPending(user.getUsername())) throw new IllegalArgumentException("Pendaftaran masih menunggu verifikasi.");
        String name = InputHelper.readRequired("Nama lengkap siswa: ");
        InputHelper.printList(ctx.getClasses().getAll());
        String classId = ctx.getClasses().requireClass(InputHelper.readRequired("ID kelas pilihan: ")).getClassId();
        String address = InputHelper.readRequired("Alamat: ");
        String phone = InputHelper.readRequired("No. telepon: ");
        Registration reg = ctx.getRegistrations().submit(user.getUsername(), name, classId, address, phone);
        System.out.println("Pendaftaran terkirim dengan ID " + reg.getRegId() + ". Tunggu verifikasi admin.");
    }

    /** Menampilkan nilai pribadi beserta rata-rata dan predikat. */
    private void showGrades() {
        Student s = requireStudent();
        InputHelper.printHeader("NILAI SAYA - " + s.getName());
        ctx.getGrades().printGrades(s.getNis());
    }
}
