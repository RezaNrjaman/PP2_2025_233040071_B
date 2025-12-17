package ac_id_unpas_modul08_view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {
    // Komponen UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasil = new JLabel("-");
    private JLabel lblHasilKeliling = new JLabel("-");
    private JButton btnHitung = new JButton("Hitung Luas");
    private JButton btnHitungKeliling = new JButton("Hitung Keliling");
    private JButton btnRiset = new JButton("Reset");

    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 250);
        this.setLayout(new BorderLayout(10, 10));
        this.setTitle("MVC Kalkulator");

        // Panel utama dengan GridLayout untuk form
        JPanel panelUtama = new JPanel(new GridLayout(5, 2, 10, 10));
        panelUtama.add(new JLabel("Panjang:"));
        panelUtama.add(txtPanjang);
        panelUtama.add(new JLabel("Lebar:"));
        panelUtama.add(txtLebar);
        panelUtama.add(new JLabel("Hasil Luas:"));
        panelUtama.add(lblHasil);
        panelUtama.add(new JLabel("Hasil Keliling:"));
        panelUtama.add(lblHasilKeliling);
        panelUtama.add(btnHitung);
        panelUtama.add(btnHitungKeliling);

        // Panel bawah untuk tombol Reset (memenuhi lebar penuh)
        JPanel panelBawah = new JPanel();
        panelBawah.add(btnRiset);

        // Tambahkan panel ke frame
        this.add(panelUtama, BorderLayout.CENTER);
        this.add(panelBawah, BorderLayout.SOUTH);
    }

    // Mengambil nilai panjang dari TextField
    public void setPanjang(String text) {
        this.txtPanjang.setText(text);
    }

    public void setLebar(String text) {
        this.txtLebar.setText(text);
    }

    public void setHasil(String text) {
        this.lblHasil.setText(text);
    }

    public void setHasilKeliling(String text) {
        this.lblHasilKeliling.setText(text);
    }

    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    // Mengambil nilai lebar dari TextField
    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Menampilkan hasil ke Label
    public void setHasil(double hasil) {
        lblHasil.setText(String.valueOf(hasil));
    }

    public void setHasilKeliling(double hasilKeliling) {
        lblHasilKeliling.setText(String.valueOf(hasilKeliling));
    }

    // Menampilkan pesan error (jika input bukan angka)
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // Mendaftarkan Listener untuk tombol (Controller yang akan memberikan aksinya)
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }

    public void addHitungKelilingListener(ActionListener listener) {
        btnHitungKeliling.addActionListener(listener);
    }

    public void addRisetListener(ActionListener listener) {
        btnRiset.addActionListener(listener);
    }
}
