package service;

import model.ClassRoom;

/** Mengurus data kelas. */
public class ClassService extends BaseService<ClassRoom> {
    /** Memuat data kelas dari file. */
    public ClassService() { super("classes.txt", ClassRoom::fromFileString); }

    /** @return kelas dengan ID tersebut, atau null */
    public ClassRoom findClass(String id) {
        for (ClassRoom c : items) if (c.getClassId().equalsIgnoreCase(id)) return c;
        return null;
    }

    /** @return kelas dengan ID tersebut. @throws IllegalArgumentException jika tidak ada */
    public ClassRoom requireClass(String id) {
        ClassRoom c = findClass(id);
        if (c == null) throw new IllegalArgumentException("Kelas tidak ditemukan.");
        return c;
    }

    /** Menambah kelas baru. @throws IllegalArgumentException jika ID mengandung spasi atau sudah ada */
    public void addClass(String id, String name, String teacher) {
        id = id.toUpperCase();
        if (id.contains(" ")) throw new IllegalArgumentException("ID kelas tidak boleh ada spasi.");
        if (findClass(id) != null) throw new IllegalArgumentException("ID kelas sudah ada.");
        items.add(new ClassRoom(id, name, teacher));
        save();
    }

    /** Menghapus kelas. */
    public void deleteClass(String id) {
        items.remove(requireClass(id));
        save();
    }
}
