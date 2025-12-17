package ac_id_unpas_modul5;

import java.awt.FlowLayout;
import javax.swing.*;

/*
 * @author wdgue
 */

public class latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("jendela pertamaku");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //1. atur layout manager
                //FlowLayout akan menyusun komponen dari kiri kekanan
                frame.setLayout(new FlowLayout());
            
                //2. buat komponen GUI                  
                JLabel label = new JLabel("Text Awal");
                JButton button = new JButton("Klik Saya!");

                /*3. tambahkan aksi (ActionListener) ke tombol penambahan
                 ini menggunakan lambda untuk mempersingkat penggunaan
                 anonymouse inner class */
                 button.addActionListener(e -> {
                    //aksi ini akan mengubah teks pada label
                    label.setText("Tombol berhasil diklik!");
                 });

                /*4. tambahkan komponen ke frame karena kita menggunakan
                 * FlowLayout, keduanya akan tampil berdampingan
                 */
                frame.add(label);
                frame.add(button);

                frame.setVisible(true);
            }
        });
    }
}
