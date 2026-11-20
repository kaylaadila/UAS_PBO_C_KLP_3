package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Storable;

/** Alat baca/tulis file .txt di folder "data". */
public class FileManager {
    private static final String DIR = "data";

    private FileManager() {}

    /** Membaca semua baris file (baris kosong diabaikan); list kosong jika file belum ada. */
    public static List<String> readLines(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(DIR, fileName);
        if (!file.exists()) return lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("[Peringatan] Gagal membaca file: " + e.getMessage());
        }
        return lines;
    }

    /** Menulis semua objek Storable ke file (menimpa isi lama). */
    public static void writeLines(String fileName, List<? extends Storable> items) {
        new File(DIR).mkdirs();
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(DIR, fileName)))) {
            for (Storable item : items) writer.println(item.toFileString());
        } catch (IOException e) {
            System.out.println("[Peringatan] Gagal menyimpan file: " + e.getMessage());
        }
    }
}
