package ac_id_unpas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

public class MahasiswaView extends JFrame {
    // Komponen GUI
    private JTextField txtNama, txtNIM, txtJurusan; // Ubah ke JComboBox jika sudah pakai Latihan 5
    private JTextField txtCari;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    private JTable tableMahasiswa;
    private DefaultTableModel model;

    public MahasiswaView() {
        setTitle("Aplikasi CRUD Mahasiswa JDBC - MVC");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Setup Form
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);
        
        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);
        
        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField(); // Sesuaikan jika pakai JComboBox
        panelForm.add(txtJurusan);

        // Setup Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        JPanel panelPencarian = new JPanel(new FlowLayout());
        panelPencarian.add(new JLabel("Cari Nama:"));
        txtCari = new JTextField(20);
        btnCari = new JButton("Cari");
        panelPencarian.add(txtCari);
        panelPencarian.add(btnCari);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        JPanel panelBawahForm = new JPanel(new GridLayout(2, 1));
        panelBawahForm.add(panelTombol);
        panelBawahForm.add(panelPencarian);
        panelAtas.add(panelBawahForm, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // Setup Tabel
        model = new DefaultTableModel(new String[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        tableMahasiswa = new JTable(model);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);
    }

    // --- GETTER DATA DARI FORM ---
    public String getNama() { return txtNama.getText(); }
    public String getNim() { return txtNIM.getText(); }
    public String getJurusan() { return txtJurusan.getText(); } // Sesuaikan jika ComboBox
    public String getKataKunci() { return txtCari.getText(); }

    // --- SETTER UNTUK MENGISI FORM ---
    public void setForm(String nama, String nim, String jurusan) {
        txtNama.setText(nama);
        txtNIM.setText(nim);
        txtJurusan.setText(jurusan); // Sesuaikan jika ComboBox
    }

    public void clearForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText(""); // Sesuaikan jika ComboBox
    }

    // --- UPDATE TABEL ---
    public void setTableData(List<Mahasiswa> list) {
        model.setRowCount(0);
        int no = 1;
        for (Mahasiswa m : list) {
            model.addRow(new Object[]{no++, m.getNama(), m.getNim(), m.getJurusan()});
        }
    }

    // --- LISTENER (Agar Controller bisa merespon tombol) ---
    public void addSimpanListener(ActionListener listener) { btnSimpan.addActionListener(listener); }
    public void addEditListener(ActionListener listener) { btnEdit.addActionListener(listener); }
    public void addHapusListener(ActionListener listener) { btnHapus.addActionListener(listener); }
    public void addClearListener(ActionListener listener) { btnClear.addActionListener(listener); }
    public void addCariListener(ActionListener listener) { btnCari.addActionListener(listener); }
    
    public void addTabelMouseListener(MouseAdapter adapter) {
        tableMahasiswa.addMouseListener(adapter);
    }
    
    public JTable getTable() { return tableMahasiswa; }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}