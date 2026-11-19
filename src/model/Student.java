package model;

/** Data seorang siswa. */
public class Student implements Storable {
    private final String nis;
    private String name, classId, address, phone;

    /** Membuat data siswa. */
    public Student(String nis, String name, String classId, String address, String phone) {
        this.nis = nis; this.name = name; this.classId = classId; this.address = address; this.phone = phone;
    }

    /** @return NIS */
    public String getNis() { return nis; }
    /** @return nama siswa */
    public String getName() { return name; }
    /** @return ID kelas */
    public String getClassId() { return classId; }
    /** @return alamat */
    public String getAddress() { return address; }
    /** @return nomor telepon */
    public String getPhone() { return phone; }
    /** Mengubah nama siswa. */
    public void setName(String name) { this.name = name; }
    /** Mengubah kelas siswa. */
    public void setClassId(String classId) { this.classId = classId; }
    /** Mengubah alamat siswa. */
    public void setAddress(String address) { this.address = address; }
    /** Mengubah nomor telepon siswa. */
    public void setPhone(String phone) { this.phone = phone; }

    /** @return format satu baris untuk disimpan ke file */
    @Override
    public String toFileString() { return nis + "|" + name + "|" + classId + "|" + address + "|" + phone; }

    /** Membaca satu baris file menjadi Student; null jika format salah. */
    public static Student fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        return p.length < 5 ? null : new Student(p[0], p[1], p[2], p[3], p[4]);
    }

    /** @return satu baris tabel siswa */
    @Override
    public String toString() { return String.format("%-8s %-20s %-7s %-14s %s", nis, name, classId, phone, address); }
}
