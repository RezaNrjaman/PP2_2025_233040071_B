import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.*;


public class ContohGridLayout {
    public static void main(String[] args) {
        // 1. buat frame
        JFrame frame = new JFrame("Grid Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // 2. atur layout frame menjadi GridLayout 3 baris, 2 kolom
        // kita juga bisa menambahkan jarak (gap) antar sel
        frame.setLayout(new GridLayout(3, 2, 5, 5)); // 3 baris, 2 kolom, 5px h-gap, 5px v-g

        // 3. tambahkan 6 komponen (3 * 2 - 6)
        frame.add(new JLabel("Label 1:"));
        frame.add(new JTextField());
        frame.add(new JLabel("Label 2"));
        frame.add(new JPasswordField());
        frame.add(new JButton("Login"));
        frame.add(new JButton("Batal")); 

        // 4. tampilkan frame
        frame.setVisible(true);
    }
}
