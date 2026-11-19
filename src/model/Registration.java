package model;

/** Data pendaftaran siswa baru (transaksi User yang diverifikasi Admin). */
public class Registration implements Storable {
    public static final String STATUS_PENDING = "MENUNGGU";
    public static final String STATUS_APPROVED = "DISETUJUI";
    public static final String STATUS_REJECTED = "DITOLAK";

    private final String regId, username, studentName, classId, address, phone;
    private String status, nis;

    /** Membuat data pendaftaran. */
    public Registration(String regId, String username, String studentName, String classId,
                        String address, String phone, String status, String nis) {
        this.regId = regId; this.username = username; this.studentName = studentName;
        this.classId = classId; this.address = address; this.phone = phone;
        this.status = status; this.nis = nis;
    }

    /** @return ID pendaftaran */
    public String getRegId() { return regId; }
    /** @return username pendaftar */
    public String getUsername() { return username; }
    /** @return nama calon siswa */
    public String getStudentName() { return studentName; }
    /** @return ID kelas pilihan */
    public String getClassId() { return classId; }
    /** @return alamat calon siswa */
    public String getAddress() { return address; }
    /** @return nomor telepon calon siswa */
    public String getPhone() { return phone; }
    /** @return status pendaftaran */
    public String getStatus() { return status; }
    /** @return NIS (kosong sebelum disetujui) */
    public String getNis() { return nis; }
    /** Mengubah status pendaftaran. */
    public void setStatus(String status) { this.status = status; }
    /** Mengisi NIS setelah disetujui. */
    public void setNis(String nis) { this.nis = nis; }

    /** @return format satu baris untuk disimpan ke file */
    @Override
    public String toFileString() {
        return regId + "|" + username + "|" + studentName + "|" + classId + "|" + address + "|" + phone + "|" + status + "|" + nis;
    }

    /** Membaca satu baris file menjadi Registration; null jika format salah. */
    public static Registration fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        return p.length < 8 ? null : new Registration(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7]);
    }

    /** @return satu baris tabel pendaftaran */
    @Override
    public String toString() {
      
