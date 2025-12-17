package ac_id_unpas_modul08_controller;

import ac_id_unpas_modul08_model.PersegiPanjangModel;
import ac_id_unpas_modul08_view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    // Model dan View sebagai atribut class
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        // Menghubungkan tombol di View dengan logic di Controller
        this.view.addHitungListener(new HitungListener());
        this.view.addHitungKelilingListener(new HitungKelilingListener());
        this.view.addRisetListener(new RisetListener());
    }

    // Inner class untuk menangani event klik tombol
    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Ambil data dari View
                double p = view.getPanjang();
                double l = view.getLebar();

                // 2. Kirim data ke Model
                model.setPanjang(p);
                model.setLebar(l);

                // 3. Jalankan logika bisnis di Model
                model.hitungLuas();

                // 4. Ambil hasil dari Model dan tampilkan kembali di View
                double hasil = model.getLuas();
                view.setHasil(hasil);

            } catch (NumberFormatException ex) {
                // Handle jika user memasukkan huruf
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }

    class HitungKelilingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Ambil data dari View
                double p = view.getPanjang();
                double l = view.getLebar();

                // 2. Kirim data ke Model
                model.setPanjang(p);
                model.setLebar(l);

                // 3. Jalankan logika bisnis di Model
                model.hitungKeliling();

                // 4. Ambil hasil dari Model dan tampilkan kembali di View
                double hasilKeliling = model.getKeliling();
                view.setHasilKeliling(hasilKeliling);

            } catch (NumberFormatException ex) {
                // Handle jika user memasukkan huruf
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }

    class RisetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Reset semua field di View
            view.setPanjang("");
            view.setLebar("");
            view.setHasil("-");
            view.setHasilKeliling("-");
        }
    }
}
