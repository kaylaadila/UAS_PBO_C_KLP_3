package service;

import model.Subject;

/** Mengurus data mata pelajaran. */
public class SubjectService extends BaseService<Subject> {
    /** Memuat data mapel dari file. */
    public SubjectService() { super("subjects.txt", Subject::fromFileString); }

    /** @return mapel dengan ID tersebut, atau null */
    public Subject findSubject(String id) {
        for (Subject s : items) if (s.getSubjectId().equalsIgnoreCase(id)) return s;
        return null;
    }

    /** @return nama mapel, atau "-" jika tidak ditemukan */
    public String getSubjectName(String id) {
        Subject s = findSubject(id);
        return s == null ? "-" : s.getSubjectName();
    }

    /** Menambah mapel baru. @throws IllegalArgumentException jika ID mengandung spasi atau sudah ada */
    public void addSubject(String id, String name) {
        id = id.toUpperCase();
        if (id.contains(" ")) throw new IllegalArgumentException("ID mapel tidak boleh ada spasi.");
        if (findSubject(id) != null) throw new IllegalArgumentException("ID mapel sudah ada.");
        items.add(new Subject(id, name));
        save();
    }

    /** Menghapus mapel. */
    public void deleteSubject(String id) {
        Subject s = findSubject(id);
        if (s == null) throw new IllegalArgumentException("Mapel tidak ditemukan.");
        items.remove(s);
        save();
    }
}
