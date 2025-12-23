package ac_id_unpas;

import java.awt.event.*;
import java.util.List;

public class MahasiswaController {
    private MahasiswaModel model;
    private MahasiswaView view;

    public MahasiswaController(MahasiswaModel model, MahasiswaView view) {
        this.model = model;
        this.view = view;

        // Load data awal
        loadData();

        // Daftarkan aksi tombol (Listener)
        view.addSimpanListener(e -> simpan());
        view.addEditListener(e -> edit());
        view.addHapusListener(e -> hapus());
        view.addClearListener(e -> view.clearForm());
        view.addCariListener(e -> cari());
        
        // Listener Klik Tabel
        view.addTabelMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                String nama = view.getTable().getValueAt(row, 1).toString();
                String nim = view.getTable().getValueAt(row, 2).toString();
                String jurusan = view.getTable().getValueAt(row, 3).toString();
                view.setForm(nama, nim, jurusan);
            }
        });

        view.setVisible(true);
    }

    private void loadData() {
        try {
            List<Mahasiswa> list = model.getAll();
            view.setTableData(list);
        } catch (Exception e) {
            view.showMessage("Gagal load data: " + e.getMessage());
        }
    }

    private void simpan() {
        String nama = view.getNama().trim();
        String nim = view.getNim().trim();
        String jurusan = view.getJurusan().trim();

        // Validasi Latihan 2
        if (nama.isEmpty() || nim.isEmpty()) {
            view.showMessage("Data tidak boleh kosong!");
            return;
        }
        if (!nim.matches("\\d+")) {
            view.showMessage("NIM harus angka!");
            return;
        }
        // Validasi Latihan 4
        if (model.cekNIM(nim)) {
            view.showMessage("NIM sudah ada!");
            return;
        }

        try {
            model.insert(new Mahasiswa(nama, nim, jurusan));
            view.showMessage("Berhasil disimpan!");
            view.clearForm();
            loadData();
        } catch (Exception e) {
            view.showMessage("Gagal simpan: " + e.getMessage());
        }
    }

    private void edit() {
        try {
            Mahasiswa m = new Mahasiswa(view.getNama(), view.getNim(), view.getJurusan());
            model.update(m);
            view.showMessage("Berhasil diedit!");
            view.clearForm();
            loadData();
        } catch (Exception e) {
            view.showMessage("Gagal edit: " + e.getMessage());
        }
    }

    private void hapus() {
        try {
            model.delete(view.getNim());
            view.showMessage("Berhasil dihapus!");
            view.clearForm();
            loadData();
        } catch (Exception e) {
            view.showMessage("Gagal hapus: " + e.getMessage());
        }
    }
    
    private void cari() {
        try {
            String kata = view.getKataKunci();
            List<Mahasiswa> list = model.cariData(kata);
            view.setTableData(list);
        } catch (Exception e) {
            view.showMessage("Gagal cari: " + e.getMessage());
        }
    }
}