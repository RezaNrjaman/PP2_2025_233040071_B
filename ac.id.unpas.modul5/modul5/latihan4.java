package modul5;

import java.awt.*;
import javax.swing.*;

/*
 * @author wdgue
 */

public class latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("jendela pertamaku");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //1. atur layout manager ke border layout
                //sebenarnya ini tidak perlu
                //karena BorderLayout adalah layout manager default
                frame.setLayout(new BorderLayout());
            
                //2. buat komponen       
                JLabel label = new JLabel("Label ada di atas (NORTH)");
                JButton buttonS = new JButton("Tombol ada di Bawah (SOUTH)");
                JButton buttonW = new JButton("Tombol ada di Kiri (WEST)");
                JButton buttonE = new JButton("Tombol ada di Kanan (EAST)");
                JButton buttonC = new JButton("Tombol ada di Tengah (CENTER)");

                /*3. tambahkan aksi (ActionListener) ke tombol*/
                 buttonS.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                 });
                 buttonW.addActionListener(e -> {
                    label.setText("Tombol di WEST diklik!");
                 });
                 buttonE.addActionListener(e -> {
                    label.setText("Tombol di EAST diklik!");
                 });
                 buttonC.addActionListener(e -> {
                    label.setText("Tombol di CENTER diklik!");
                 });

                /*4. tambahkan komponen di frame dengan posisi
                 */
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonS, BorderLayout.SOUTH);
                frame.add(buttonW, BorderLayout.WEST);
                frame.add(buttonE, BorderLayout.EAST);
                frame.add(buttonC, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}
