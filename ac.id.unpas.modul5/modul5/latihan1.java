package modul5;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * @author wdgus
 */

public class latihan1 {
    public static void main(String[] args) {
        // menjalankan kode GUI di Event Dispatch Thread (EDT)
        // ini adalah praktik terbaik untuk menghindari masalah thread
        // akan dijelaskan lebih detail nanti 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //1. buat objek JFrame
                JFrame frame = new JFrame("jendela pertamaku");

                //2. Atur ukuran jendela (lebar 400, tinggi 300)
                frame.setSize(400, 300);

                //3. Atur aksi saat tombol close (x) ditekan
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //4. Buat jendela terlihat
                frame.setVisible(true);
            }
        });
    }
}






