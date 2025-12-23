package ac_id_unpas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MahasiswaApp extends JFrame {
    
    // Komponen GUI
    JTextField txtNama, txtNIM, txtJurusan;
    JButton btnSimpan, btnEdit, btnHapus, btnClear;
    
    // Komponen Tambahan Latihan 3 (Pencarian)
    JTextField txtCari;
    JButton btnCari;
    
    JTable tableMahasiswa;
    DefaultTableModel model;

    public MahasiswaApp() {
        // --- Setup Frame ---
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(600, 600); // Saya perbesar sedikit tingginya agar muat
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- 1. Panel Form (Input Data) ---
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // --- Panel Tombol CRUD ---
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        // --- Panel Pencarian (Latihan 3) ---
        JPanel panelPencarian = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelPencarian.add(new JLabel("Cari Nama:"));
        txtCari = new JTextField(20); // Lebar kolom pencarian
        btnCari = new JButton("Cari");
        panelPencarian.add(txtCari);
        panelPencarian.add(btnCari);

        // --- Menggabungkan Panel Tombol & Pencarian ---
        JPanel panelBawahForm = new JPanel(new GridLayout(2, 1)); // 2 Baris (Baris 1: CRUD, Baris 2: Cari)
        panelBawahForm.add(panelTombol);
        panelBawahForm.add(panelPencarian);

        // Gabungkan Panel Form dan Area Bawah di bagian Atas Frame
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelBawahForm, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // --- 2. Tabel Data ---
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");
        
        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);

        // --- Event Listeners ---
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
            }
        });

        btnSimpan.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> kosongkanForm());
        
        // Listener untuk tombol Cari
        btnCari.addActionListener(e -> cariData());
        
        // (Opsional) Tekan Enter di kolom pencarian langsung mencari
        txtCari.addActionListener(e -> cariData());

        loadData(); 
    }

    // Latihan 4 Validasi NIM
    private boolean cekNIM(String nim) {
    try {
        Connection conn = KoneksiDB.configDB();
        String sql = "SELECT nim FROM mahasiswa WHERE nim = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        ResultSet res = pst.executeQuery();
        
        // Jika res.next() bernilai true, berarti data ditemukan (NIM sudah ada)
        return res.next(); 
    } catch (Exception e) {
        System.out.println("Gagal Cek NIM: " + e.getMessage());
        return false;
    }
}

    // --- LOGIKA CRUD ---

    // 1. READ (Menampilkan Data Semua)
    private void loadData() {
        model.setRowCount(0);
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
            
            int no = 1;
            while (res.next()) {
                model.addRow(new Object[] {
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
        }
    }

    // Latihan 3 cari data
    private void cariData() {
        String kataKunci = txtCari.getText().trim();
        
        // Jika pencarian kosong, load semua data lagi
        if (kataKunci.isEmpty()) {
            loadData();
            return;
        }

        model.setRowCount(0); // Reset tabel sebelum menampilkan hasil cari
        try {
            Connection conn = KoneksiDB.configDB();
            // Query LIKE untuk mencari nama yang mengandung kata kunci
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            // Tambahkan % di awal dan akhir untuk pencarian parsial (wildcard)
            pst.setString(1, "%" + kataKunci + "%");
            
            ResultSet res = pst.executeQuery();
            
            int no = 1;
            while (res.next()) {
                model.addRow(new Object[] {
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Mencari Data: " + e.getMessage());
        }
    }

    // 2. CREATE (Dengan Validasi Latihan 2 & Latihan 4)
private void tambahData() {
    String nama = txtNama.getText().trim();
    String nim = txtNIM.getText().trim();

    // Validasi Latihan 2 
    if (nama.isEmpty() || nim.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
        return; 
    }
    if (nama.matches(".*\\d.*")) {
        JOptionPane.showMessageDialog(this, "Nama tidak boleh mengandung angka!");
        return;
    }
    if (!nim.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "NIM harus berupa angka!");
        return;
    }

    // validasi latihan 4
    if (cekNIM(nim)) {
        JOptionPane.showMessageDialog(this, "NIM " + nim + " sudah terdaftar! Mohon gunakan NIM lain.");
        return;
    }

    // Proses Simpan ke Database
    try {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        
        pst.setString(1, nama);
        pst.setString(2, nim);
        pst.setString(3, txtJurusan.getText());
        
        pst.execute();
        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
        loadData();
        kosongkanForm();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
    }
}

    // 3. UPDATE
    private void ubahData() {
        try {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtJurusan.getText());
            pst.setString(3, txtNIM.getText());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }

    // 4. DELETE
    private void hapusData() {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNIM.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
        }
    }

    private void kosongkanForm() {
        txtNama.setText(null);
        txtNIM.setText(null);
        txtJurusan.setText(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}