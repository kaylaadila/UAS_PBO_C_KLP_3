import menu.MainMenu;
import service.AppContext;

/** Titik awal program: membuat semua service lalu membuka menu utama. */
public class Main {
    /** Menjalankan aplikasi. */
    public static void main(String[] args) {
        new MainMenu(new AppContext()).show();
    }
}
