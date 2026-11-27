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
    }
   public static int readInt(String var0, int var1, int var2) {
      while(true) {
         try {
            int var3 = Integer.parseInt(readLine(var0));
            if (var3 >= var1 && var3 <= var2) {
               return var3;
            }

            System.out.println("Masukkan angka antara " + var1 + " dan " + var2 + ".");
         } catch (NumberFormatException var4) {
            System.out.println("Input harus berupa angka bulat.");
         }
      }
   }

   public static double readDouble(String var0, double var1, double var3) {
      while(true) {
         try {
            double var5 = Double.parseDouble(readLine(var0).replace(',', '.'));
            if (var5 >= var1 && var5 <= var3) {
               return var5;
            }

            System.out.println("Masukkan angka antara " + var1 + " dan " + var3 + ".");
         } catch (NumberFormatException var7) {
            System.out.println("Input harus berupa angka.");
         }
      }
   }

   public static boolean confirm(String var0) {
      String var1 = readLine(var0 + " (y/n): ").toLowerCase();
      return var1.equals("y") || var1.equals("ya");
   }

   public static void printHeader(String var0) {
      String var1 = "=".repeat(56);
      System.out.println("\n" + var1 + "\n " + var0 + "\n" + var1);
   }

   public static void printList(List<?> var0) {
      if (var0.isEmpty()) {
         System.out.println("Data masih kosong.");
      }

      for(Object var2 : var0) {
         System.out.println(var2);
      }

   }

   public static void runMenu(String var0, String[] var1, String var2, IntConsumer var3) {
      while(true) {
         printHeader(var0);

         for(int var4 = 0; var4 < var1.length; ++var4) {
            System.out.println(var4 + 1 + ". " + var1[var4]);
         }

         System.out.println("0. " + var2);
         int var7 = readInt("Pilih menu: ", 0, var1.length);
         if (var7 == 0) {
            return;
         }

         try {
            var3.accept(var7);
         } catch (RuntimeException var6) {
            System.out.println("[Gagal] " + var6.getMessage());
         }
      }
   }

}
