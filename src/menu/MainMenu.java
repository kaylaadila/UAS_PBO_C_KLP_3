package menu;

import model.Account;
import model.Admin;
import model.User;
import service.AppContext;
import util.InputHelper;

/** Menu awal sebelum login: login dan registrasi akun. */
public class MainMenu {
    private final AppContext ctx;

    /** Membuat menu utama. */
    public MainMenu(AppContext ctx) { this.ctx = ctx; }

    /** Menampilkan menu utama sampai pengguna memilih keluar. */
    public void show() {
        InputHelper.runMenu("STUDENT MANAGEMENT SYSTEM", new String[]{"Login", "Registrasi Akun Baru"}, "Keluar",
                choice -> { if (choice == 1) login(); else register(); });
        System.out.println("Terima kasih. Sampai jumpa!");
    }

    /** Alur login, lalu mengarahkan ke menu Admin atau User sesuai tipe akun. */
    private void login() {
        InputHelper.printHeader("LOGIN");
        Account account = ctx.getAuth().login(InputHelper.readRequired("Username: "), InputHelper.readRequired("Password: "));
        if (account == null) {
            System.out.println("[Gagal] Username atau password salah.");
            return;
        }
        System.out.println("Login berhasil. " + account.describe());
        if (account instanceof Admin) new AdminMenu((Admin) account, ctx).show();
        else new UserMenu((User) account, ctx).show();
    }

    /** Alur registrasi akun User baru. */
    private void register() {
        InputHelper.printHeader("REGISTRASI AKUN");
        String fullName = InputHelper.readRequired("Nama lengkap: ");
        String username = InputHelper.readRequired("Username (min. 3 karakter, tanpa spasi): ");
        String password = InputHelper.readRequired("Password (min. 4 karakter): ");
        if (!password.equals(InputHelper.readRequired("Ulangi password: "))) {
            throw new IllegalArgumentException("Konfirmasi password tidak sama.");
        }
        ctx.getAuth().register(username, password, fullName);
        System.out.println("Registrasi berhasil. Silakan login.");
    }
}
