package service;

import java.util.ArrayList;
import java.util.List;
import model.Registration;
import model.Student;

/** Mengurus pengajuan pendaftaran siswa (oleh User) dan verifikasinya (oleh Admin). */
public class RegistrationService extends BaseService<Registration> {
    /** Memuat data pendaftaran dari file. */
    public RegistrationService() { super("registrations.txt", Registration::fromFileString); }

    /** User mengajukan pendaftaran. @throws IllegalArgumentException jika masih ada yang menunggu */
    public Registration submit(String username, String name, String classId, String address, String phone) {
        if (hasPending(username)) throw new IllegalArgumentException("Pendaftaran sebelumnya masih menunggu.");
        Registration r = new Registration(String.valueOf(items.size() + 1), username, name, classId,
                address, phone, Registration.STATUS_PENDING, "");
        items.add(r);
        save();
        return r;
    }

    /** @return true jika user masih punya pendaftaran berstatus MENUNGGU */
    public boolean hasPending(String username) {
        for (Registration r : getByUsername(username)) {
            if (r.getStatus().equals(Registration.STATUS_PENDING)) return true;
        }
        return false;
    }

    /** @return semua pendaftaran berstatus MENUNGGU */
    public List<Registration> getPending() {
        List<Registration> result = new ArrayList<>();
        for (Registration r : items) if (r.getStatus().equals(Registration.STATUS_PENDING)) result.add(r);
        return result;
    }

    /** @return riwayat pendaftaran milik satu user */
    public List<Registration> getByUsername(String username) {
        List<Registration> result = new ArrayList<>();
        for (Registration r : items) if (r.getUsername().equalsIgnoreCase(username)) result.add(r);
        return result;
    }

    /** @return pendaftaran MENUNGGU dengan ID tersebut. @throws IllegalArgumentException jika tidak valid */
    private Registration requirePending(String regId) {
        for (Registration r : items) {
            if (r.getRegId().equals(regId) && r.getStatus().equals(Registration.STATUS_PENDING)) return r;
        }
        throw new IllegalArgumentException("Pendaftaran tidak valid.");
    }

    /** Admin menyetujui: membuat siswa baru, menghubungkan akun, lalu mengubah status. @return NIS baru */
    public String approve(String regId, StudentService studentService, AuthService authService) {
        Registration r = requirePending(regId);
        String nis = studentService.generateNis();
        studentService.addStudent(new Student(nis, r.getStudentName(), r.getClassId(), r.getAddress(), r.getPhone()));
        authService.linkStudent(r.getUsername(), nis);
        r.setStatus(Registration.STATUS_APPROVED);
        r.setNis(nis);
        save();
        return nis;
    }

    /** Admin menolak pendaftaran. */
    public void reject(String regId) {
        requirePending(regId).setStatus(Registration.STATUS_REJECTED);
        save();
    }
}
