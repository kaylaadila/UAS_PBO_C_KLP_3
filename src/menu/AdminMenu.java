package menu;

import java.util.List;
import model.Admin;
import model.Registration;
import model.Student;
import service.AppContext;
import util.InputHelper;

/** Menu Admin: kelola siswa, kelas, mapel, nilai, verifikasi pendaftaran, dan lihat data pengguna. */
public class AdminMenu {
    private final Admin admin;
    private final AppContext ctx;

    /** Membuat menu untuk admin yang sedang login. */
    public AdminMenu(Admin admin, AppContext ctx) {
        this.admin = admin;
        this.ctx = ctx;
    }

    /** Menampilkan menu utama admin. */
    public void show() {
        String[] labels = {"Kelola Siswa", "Kelola Kelas", "Kelola Mata Pelajaran", "Input Nilai",
                "Verifikasi Pendaftaran", "Riwayat Pendaftaran", "Data Pengguna"};
        InputHelper.runMenu("MENU ADMIN - " + admin.getFullName(), labels, "Logout", choice -> {
            switch (choice) {
                case 1: studentMenu(); break;
                case 2: classMenu(); break;
                case 3: subjectMenu(); break;
                case 4: inputGrades(); break;
                case 5: verifyRegistrations(); break;
                case 6: InputHelper.printList(ctx.getRegistrations().getAll()); break;
                default: InputHelper.printList(ctx.getAuth().getAll());
            }
        });
        System.out.println("Anda telah logout.");
    }

    // ===================== SISWA =====================

    /** Sub-menu CRUD data siswa. */
    private void studentMenu() {
        String[] labels = {"Tambah Siswa", "Edit Siswa", "Hapus Siswa", "Lihat Semua", "Cari Siswa"};
        InputHelper.runMenu("KELOLA SISWA", labels, "Kembali", choice -> {
            switch (choice) {
                case 1: addStudent(); break;
                case 2: editStudent(); break;
                case 3: deleteStudent(); break;
                case 4: printStudents(ctx.getStudents().getAll()); break;
                default: printStudents(ctx.getStudents().search(InputHelper.readRequired("NIS atau nama: ")));
            }
        });
    }
