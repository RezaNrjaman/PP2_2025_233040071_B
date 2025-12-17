package ac_id_unpas_modul7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManajemenNilaiSiswaApp extends JFrame {
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    public ManajemenNilaiSiswaApp() {
        // 1. Konfigurasi Frame Utama
        JFrame frame = new JFrame("Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi di tengah layar

        // 2. Inialisasi TabbedPane
        tabbedPane = new JTabbedPane();

        // 3. Membuat Panel untuk tab 1 (form input)
        JPanel inputPanel = createInputPanel();
        tabbedPane.addTab("Input Nilai", inputPanel);

        // 4. Membuat Panel untuk tab 2 (tabel data)
        JPanel tablePanel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", tablePanel);

        // 5. Menambahkan TabbedPane ke Frame
        add(tabbedPane);
    }

    // Method untuk membuat desain Tab Input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen Nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // Komponen Mata Pelajaran
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia", "Algoritma dan Pemerograman I", "Praktikum Pemerograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(new JLabel("")); // Placeholder kosong agar tomnbol di kanan
        panel.add(btnSimpan);

        // Event Handling Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });
        return panel;
    }

    // Method untuk membuat desain Tab Tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Setup Model Tabel (kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        // Membungkus tabel dengan ScrollPane (agar bisa di scroll jika datanya banyak)
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // logika validasi dan penyimpanan data
    private void prosesSimpan() {
        // 1. ambil data dari input
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String nilaiStr = txtNilai.getText();

        // 2. validasi input
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama siswa tidak boleh kosong.", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return; // Hentikan proses
        }

        // validasi 2: cek apakah nilai berupa angka dan dalam range valid
        int nilai;
        try {
            nilai = Integer.parseInt(nilaiStr);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(null, "Nilai harus antara 0 - 100.", "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka.", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // 3. Logika Bisnis (Menentukan Grade)
        String grade;
        if (nilai >= 80) grade = "A";
        else if (nilai >= 70) grade = "AB";
        else if (nilai >= 60) grade = "B"; 
        else if (nilai >= 50) grade = "BC";
        else if (nilai >= 40) grade = "C";
        else if (nilai >= 30) grade = "D";
        else grade = "E";

        // 4. Masukkan ke Tabel (Upadate Model)
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        // 5. Reset Form dan Pindah Tab
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        tabbedPane.setSelectedIndex(1); // Pindah ke tab tabel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}

