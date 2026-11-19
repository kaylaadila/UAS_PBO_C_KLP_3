package model;

/** Kontrak untuk objek yang bisa disimpan ke file teks. */
public interface Storable {
    /** @return satu baris teks (dipisah "|") untuk disimpan ke file */
    String toFileString();
}
