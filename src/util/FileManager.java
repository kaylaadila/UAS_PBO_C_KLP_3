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
