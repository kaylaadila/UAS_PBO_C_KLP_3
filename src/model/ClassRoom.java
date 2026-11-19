package model;

/** Data kelas. */
public class ClassRoom implements Storable {
    private final String classId, className, homeroomTeacher;
    
    /** Membuat data kelas. */
    public ClassRoom(String classId, String className, String homeroomTeacher) {
        this.classId = classId; this.className = className; this.homeroomTeacher = homeroomTeacher;
    }

    /** @return ID kelas */
    public String getClassId() { return classId; }
    /** @return nama kelas */
    public String getClassName() { return className; }
    /** @return nama wali kelas */
    public String getHomeroomTeacher() { return homeroomTeacher; }

    /** @return format satu baris untuk disimpan ke file */
    @Override
    public String toFileString() { return classId + "|" + className + "|" + homeroomTeacher; }

    /** Membaca satu baris file menjadi ClassRoom; null jika format salah. */
    public static ClassRoom fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        return p.length < 3 ? null : new ClassRoom(p[0], p[1], p[2]);
    }

    /** @return ringkasan kelas */
    @Override
    public String toString() { return classId + " - " + className + " (Wali: " + homeroomTeacher + ")"; }
}
