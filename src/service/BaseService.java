package service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import model.Storable;
import util.FileManager;

/** Kelas abstrak dasar semua service: memuat dan menyimpan daftar objek dari/ke file. */
public abstract class BaseService<T extends Storable> {
    private final String fileName;
    protected final List<T> items = new ArrayList<>();

    /** Memuat data dari file memakai parser milik masing-masing model. */
    protected BaseService(String fileName, Function<String, T> parser) {
        this.fileName = fileName;
        for (String line : FileManager.readLines(fileName)) {
            T item = parser.apply(line);
            if (item != null) items.add(item);
        }
    }

    /** Menyimpan seluruh data ke file. */
    protected void save() { FileManager.writeLines(fileName, items); }

    /** @return salinan seluruh data */
    public List<T> getAll() { return new ArrayList<>(items); }
}
