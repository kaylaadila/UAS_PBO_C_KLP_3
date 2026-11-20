package util;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.IntConsumer;

/** Alat bantu input keyboard dan tampilan menu CLI. */
public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    private InputHelper() {}

    /** Membaca teks; karakter "|" diganti "/" agar tidak merusak format file. */
    public static String readLine(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextLine().trim().replace("|", "/");
        } catch (NoSuchElementException e) {
            System.out.println("\nInput berakhir. Program ditutup.");
            System.exit(0);
            return "";
        }
    }

    /** Membaca teks yang tidak boleh kosong. */
    public static String readRequired(String prompt) {
        String text = readLine(prompt);
        while (text.isEmpty()) {
            System.out.println("Input tidak boleh kosong.");
            text = readLine(prompt);
        }
        return text;
    }

    /** Membaca teks; jika dikosongkan, nilai lama dipertahankan. */
    public static String readOrKeep(String label, String old) {
        String text = readLine(label + " [" + old + "]: ");
        return text.isEmpty() ? old : text;
