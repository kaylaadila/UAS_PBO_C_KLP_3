package model;

/** Nilai seorang siswa pada satu mata pelajaran. */
public class Grade implements Storable {
    private final String nis, subjectId;
    private double score;

    /** Membuat data nilai. */
    public Grade(String nis, String subjectId, double score) {
        this.nis = nis; this.subjectId = subjectId; this.score = score;
    }

    /** @return NIS siswa */
    public String getNis() { return nis; }
    /** @return ID mapel */
    public String getSubjectId() { return subjectId; }
    /** @return nilai (0-100) */
    public double getScore() { return score; }
    /** Mengubah nilai. */
    public void setScore(double score) { this.score = score; }

    /** @return format satu baris untuk disimpan ke file */
    @Override
    public String toFileString() { return nis + "|" + subjectId + "|" + score; }

    /** Membaca satu baris file menjadi Grade; null jika format salah. */
    public static Grade fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length < 3) return null;
        try {
            return new Grade(p[0], p[1], Double.parseDouble(p[2]));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** @return predikat huruf (A-E) dari sebuah nilai */
