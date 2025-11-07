import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Latihan2 {
    public static void main(String[] args) {
        // 1. buat frame
        JFrame frame = new JFrame("Konversi Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // 2. atur layout frame menjadi GridLayout 3 baris, 2 kolom
        // kita juga bisa menambahkan jarak (gap) antar sel
        frame.setLayout(new GridLayout(3, 2, 5, 5)); // 3 baris, 2 kolom, 5px h-gap, 5px v-g

        JLabel celLabel = new JLabel("Celcius");
        JTextField celField = new JTextField();
        JButton konversi = new JButton("Konversi");
        JLabel fahLabel = new JLabel("Fahrenheit");
        JTextField fahField = new JTextField();

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Mengambil teks dari celciusField
                    // mengubah teks ke double
                    double celcius = Double.parseDouble(celField.getText());
                    // ubah teks ke Fahrenheit
                    double fahrenheit = (celcius * 9 / 5) + 32;
                    fahField.setText(String.format("%.2f", fahrenheit));
                } catch (NumberFormatException ex) {
                    fahField.setText("Input Salah");
                }
            }
        };

        // Daftarkan ActionListener ke buttonKonversi
        konversi.addActionListener(listener);

        // 3. tambahkan 6 komponen (3 * 2 - 6)
        frame.add(celLabel);
        frame.add(celField);
        frame.add(fahLabel);
        frame.add(fahField);
        frame.add(konversi);


        // 4. tampilkan frame
        frame.setVisible(true);
    }
}
