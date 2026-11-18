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

    /** Mencetak tabel siswa. */
    private void printStudents(List<Student> list) {
        System.out.printf("%-8s %-20s %-7s %-14s %s%n", "NIS", "Nama", "Kelas", "Telepon", "Alamat");
        InputHelper.printList(list);
    }

    /** Menampilkan daftar kelas lalu meminta admin memilih ID kelas yang valid. */
    private String chooseClassId() {
        InputHelper.printList(ctx.getClasses().getAll());
        return ctx.getClasses().requireClass(InputHelper.readRequired("ID kelas: ")).getClassId();
    }

    /** Menambah siswa baru dengan NIS otomatis. */
    private void addStudent() {
        String nis = ctx.getStudents().generateNis();
        String name = InputHelper.readRequired("Nama siswa: ");
        String classId = chooseClassId();
        String address = InputHelper.readRequired("Alamat: ");
        String phone = InputHelper.readRequired("No. telepon: ");
        ctx.getStudents().addStudent(new Student(nis, name, classId, address, phone));
        System.out.println("Siswa ditambahkan dengan NIS " + nis + ".");
    }

    /** Mengedit siswa; input kosong berarti data lama dipertahankan. */
    private void editStudent() {
        Student s = ctx.getStudents().requireStudent(InputHelper.readRequired("NIS siswa yang diedit: "));
        String name = InputHelper.readOrKeep("Nama", s.getName());
        String classId = InputHelper.readOrKeep("ID kelas", s.getClassId()).toUpperCase();
        ctx.getClasses().requireClass(classId);
        String address = InputHelper.readOrKeep("Alamat", s.getAddress());
        String phone = InputHelper.readOrKeep("Telepon", s.getPhone());
        ctx.getStudents().updateStudent(s.getNis(), name, classId, address, phone);
        System.out.println("Siswa diperbarui.");
    }

    /** Menghapus siswa setelah konfirmasi. */
    private void deleteStudent() {
        Student s = ctx.getStudents().requireStudent(InputHelper.readRequired("NIS siswa yang dihapus: "));
        if (InputHelper.confirm("Yakin menghapus " + s.getName() + "?")) {
            ctx.getStudents().deleteStudent(s.getNis());
            System.out.println("Siswa dihapus.");
        }
    }

    // ===================== KELAS & MAPEL =====================

    /** Sub-menu data kelas. */
    private void classMenu() {
        InputHelper.runMenu("KELOLA KELAS", new String[]{"Lihat Kelas", "Tambah", "Hapus"}, "Kembali", choice -> {
            if (choice == 1) {
                InputHelper.printList(ctx.getClasses().getAll());
            } else if (choice == 2) {
                ctx.getClasses().addClass(InputHelper.readRequired("ID kelas (contoh X-A): "),
                        InputHelper.readRequired("Nama kelas: "), InputHelper.readRequired("Wali kelas: "));
                System.out.println("Kelas ditambahkan.");
            } else {
                ctx.getClasses().deleteClass(InputHelper.readRequired("ID kelas yang dihapus: "));
                System.out.println("Kelas dihapus.");
            }
        });
    }
    /** Sub-menu data mata pelajaran. */
    private void subjectMenu() {
        InputHelper.runMenu("KELOLA MAPEL", new String[]{"Lihat Mapel", "Tambah", "Hapus"}, "Kembali", choice -> {
            if (choice == 1) {
                InputHelper.printList(ctx.getSubjects().getAll());
            } else if (choice == 2) {
                ctx.getSubjects().addSubject(InputHelper.readRequired("ID mapel (contoh MTK): "),
                        InputHelper.readRequired("Nama mapel: "));
                System.out.println("Mapel ditambahkan.");
            } else {
                ctx.getSubjects().deleteSubject(InputHelper.readRequired("ID mapel yang dihapus: "));
                System.out.println("Mapel dihapus.");
            }
        });
    }

    // ===================== NILAI & VERIFIKASI =====================

    /** Admin memasukkan nilai siswa; ulangi sampai mengetik 0. */
    private void inputGrades() {
        Student s = ctx.getStudents().requireStudent(InputHelper.readRequired("NIS siswa: "));
        InputHelper.printList(ctx.getSubjects().getAll());
        while (true) {
            ctx.getGrades().printGrades(s.getNis());
            String subjectId = InputHelper.readRequired("ID mapel (0 = selesai): ");
            if (subjectId.equals("0")) return;
            try {
                ctx.getGrades().setGrade(s.getNis(), subjectId, InputHelper.readDouble("Nilai (0-100): ", 0, 100));
                System.out.println("Nilai tersimpan.");
            } catch (IllegalArgumentException e) {
                System.out.println("[Gagal] " + e.getMessage());
            }
        }
    }

    /** Admin menyetujui atau menolak pendaftaran yang menunggu. */
    private void verifyRegistrations() {
        List<Registration> pending = ctx.getRegistrations().getPending();
        if (pending.isEmpty()) {
            System.out.println("Tidak ada pendaftaran menunggu.");
            return;
        }
        InputHelper.printList(pending);
        String id = InputHelper.readRequired("ID pendaftaran (0 = batal): ");
        if (id.equals("0")) return;
        int choice = InputHelper.readInt("1 = Setujui, 2 = Tolak, 0 = Batal: ", 0, 2);
        if (choice == 1) {
            System.out.println("Disetujui. NIS: " + ctx.getRegistrations().approve(id, ctx.getStudents(), ctx.getAuth()));
        } else if (choice == 2) {
            ctx.getRegistrations().reject(id);
            System.out.println("Pendaftaran ditolak.");
        }
    }
}
