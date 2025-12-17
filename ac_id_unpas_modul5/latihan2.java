package ac_id_unpas_modul5;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/*
 * @author wdgus
 */

public class latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("jendela pertamaku");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //1. buat komponen JLbel
                JLabel label = new JLabel("ini adalah JLabel.");

                //2. tambahkan JLabel ke Jframe
                //secara default,JFrame menggunakan BorderLayout,
                //dan .add() akan menambahkan ke bagian tengah (center) .
                frame.add(label);

                frame.setVisible(true);
            }
        });
    }
}

