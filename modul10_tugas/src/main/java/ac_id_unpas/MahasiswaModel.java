package ac_id_unpas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaModel {
    
    // Ambil semua data
    public List<Mahasiswa> getAll() throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        Connection conn = KoneksiDB.configDB();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
        
        while (res.next()) {
            list.add(new Mahasiswa(
                res.getString("nama"),
                res.getString("nim"),
                res.getString("jurusan")
            ));
        }
        return list;
    }

    // Cari data (Latihan 3)
    public List<Mahasiswa> cariData(String keyword) throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        Connection conn = KoneksiDB.configDB();
        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + keyword + "%");
        ResultSet res = pst.executeQuery();
        
        while (res.next()) {
            list.add(new Mahasiswa(
                res.getString("nama"),
                res.getString("nim"),
                res.getString("jurusan")
            ));
        }
        return list;
    }

    // Simpan Data
    public void insert(Mahasiswa m) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, m.getNama());
        pst.setString(2, m.getNim());
        pst.setString(3, m.getJurusan());
        pst.execute();
    }

    // Update Data
    public void update(Mahasiswa m) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, m.getNama());
        pst.setString(2, m.getJurusan());
        pst.setString(3, m.getNim());
        pst.executeUpdate();
    }

    // Hapus Data
    public void delete(String nim) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        pst.execute();
    }

    // Cek Duplikasi NIM (Latihan 4)
    public boolean cekNIM(String nim) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT nim FROM mahasiswa WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            ResultSet res = pst.executeQuery();
            return res.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}