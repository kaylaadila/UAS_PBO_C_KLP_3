package model;

/** Data mata pelajaran. */
public class Subject implements Storable {
    private final String subjectId, subjectName;

    /** Membuat data mata pelajaran. */
    public Subject(String subjectId, String subjectName) {
    this.subjectId = subjectId; this.subjectName = subjectName;
    }

    /** @return ID mapel */
    public String getSubjectId() { return subjectId; }
    /** @return nama mapel */
    public String getSubjectName() { return subjectName; }

    /** @return format satu baris untuk disimpan ke file */
    @Override
    public String toFileString() { return subjectId + "|" + subjectName; }

    /** Membaca satu baris file menjadi Subject; null jika format salah. */
    public static Subject fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        return p.length < 2 ? null : new Subject(p[0], p[1]);
    }

    /** @return ringkasan mapel */
    @Override
    public String toString() { return subjectId + " - " + subjectName; }
}
