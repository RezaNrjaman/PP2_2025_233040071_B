package ac_id_unpas;

import javax.swing.SwingUtilities;

public class MainMVC {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaModel model = new MahasiswaModel();
            MahasiswaView view = new MahasiswaView();
            new MahasiswaController(model, view);
        });
    }
}