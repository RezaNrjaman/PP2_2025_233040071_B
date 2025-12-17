package ac_id_unpas_modul08;

import ac_id_unpas_modul08_controller.PersegiPanjangController;
import ac_id_unpas_modul08_model.PersegiPanjangModel;
import ac_id_unpas_modul08_view.PersegiPanjangView;

public class Main {
    public static void main(String[] args) {
        // 1. Instansiasi Model
        PersegiPanjangModel model = new PersegiPanjangModel();

        // 2. Instansiasi View
        PersegiPanjangView view = new PersegiPanjangView();

        // 3. Instansiasi Controller (Hubungkan Model & View)
        PersegiPanjangController controller = new PersegiPanjangController(model, view);

        // 4. Tampilkan View
        view.setVisible(true);
    }
}
