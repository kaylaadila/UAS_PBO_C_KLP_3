package service;

import model.Account;
import model.Admin;
import model.User;

/** Mengurus login, registrasi akun, dan data akun. */
public class AuthService extends BaseService<Account> {
    /** Memuat akun; membuat akun admin default jika data kosong. */
    public AuthService() {
        super("accounts.txt", Account::fromFileString);
        if (items.isEmpty()) {
            items.add(new Admin("admin", "admin123", "Administrator"));
            save();
        }
    }

    /** @return akun dengan username tersebut (abaikan huruf besar/kecil), atau null */
    public Account findByUsername(String username) {
        for (Account a : items) if (a.getUsername().equalsIgnoreCase(username)) return a;
        return null;
    }

    /** @return akun jika username dan password cocok, selain itu null */
    public Account login(String username, String password) {
        Account a = findByUsername(username);
        return (a != null && a.checkPassword(password)) ? a : null;
    }

    /** Mendaftarkan akun User baru. @throws IllegalArgumentException jika data tidak valid */
    public void register(String username, String password, String fullName) {
        if (username.length() < 3 || username.contains(" ")) throw new IllegalArgumentException("Username min 3 karakter tanpa spasi.");
        if (password.length() < 4) throw new IllegalArgumentException("Password min 4 karakter.");
        if (findByUsername(username) != null) throw new IllegalArgumentException("Username sudah digunakan.");
        items.add(new User(username, password, fullName));
        save();
    }

    /** Menghubungkan akun User dengan NIS siswa (saat pendaftaran disetujui). */
    public void linkStudent(String username, String nis) {
        Account a = findByUsername(username);
        if (a instanceof User) {
            ((User) a).setNis(nis);
            save();
        }
    }
}

