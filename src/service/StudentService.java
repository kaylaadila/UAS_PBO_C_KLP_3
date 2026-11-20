package service;

import java.util.ArrayList;
import java.util.List;
import model.Student;

/** Mengurus data siswa (tambah, edit, hapus, cari). */
public class StudentService extends BaseService<Student> {
    /** Memuat data siswa dari file. */
    public StudentService() { super("students.txt", Student::fromFileString); }

    /** @return siswa dengan NIS tersebut, atau null */
    public Student findByNis(String nis) {
        for (Student s : items) if (s.getNis().equals(nis)) return s;
        return null;
    }

    /** @return siswa dengan NIS tersebut. @throws IllegalArgumentException jika tidak ada */
    public Student requireStudent(String nis) {
        Student s = findByNis(nis);
        if (s == null) throw new IllegalArgumentException("Siswa tidak ditemukan.");
        return s;
    }

    /** Menambah siswa baru. @throws IllegalArgumentException jika NIS kosong atau sudah ada */
    public void addStudent(Student student) {
        if (student.getNis().isEmpty()) throw new IllegalArgumentException("NIS tidak boleh kosong.");
        if (findByNis(student.getNis()) != null) throw new IllegalArgumentException("NIS sudah terdaftar.");
        items.add(student);
        save();
    }

    /** Mengubah data siswa. */
    public void updateStudent(String nis, String name, String classId, String address, String phone) {
        Student s = requireStudent(nis);
        s.setName(name); s.setClassId(classId); s.setAddress(address); s.setPhone(phone);
        save();
    }

    /** Menghapus siswa. */
    public void deleteStudent(String nis) {
        items.remove(requireStudent(nis));
        save();
    }

    /** @return siswa yang NIS atau namanya mengandung kata kunci */
    public List<Student> search(String keyword) {
        String key = keyword.toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student s : items) {
            if (s.getNis().toLowerCase().contains(key) || s.getName().toLowerCase().contains(key)) result.add(s);
        }
        return result;
    }

    /** @return NIS otomatis (angka tertinggi + 1, mulai dari 1001) */
    public String generateNis() {
        int max = 1000;
        for (Student s : items) {
            try {
                max = Math.max(max, Integer.parseInt(s.getNis()));
            } catch (NumberFormatException ignored) {
                // NIS non-angka dilewati
            }
        }
        return String.valueOf(max + 1);
    }
}
