import javax.swing.*;


public class ContohFlowLayout {
    public static void main(String[] args) {
        //1. buat frame window
        JFrame frame = new JFrame("Contoh FlowLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        //2. buat panel (container)
        //JPane; secara default sudah menggunakan FlowLayout
        JPanel panel = new JPanel();
        //anda juga bisa mengaturnya secara eksplisit:
        //panel.setLayout (new FlowLayout (FlowLayout.Center)); CENTER, LEFT, RIGHT

        //3. buat dan tambahkan komponen
        panel.add(new JButton("Tombol 1"));
        panel.add(new JButton("Tombol 2"));
        panel.add(new JButton("Tombol Tiga"));
        panel.add(new JButton("Tombol Empat Panjang"));
        panel.add(new JButton("Tombol 5"));

        //4. tambahkan panel ke frame
        frame.add(panel);

        //5. tampilkan frame
        frame.setVisible(true);
    }
}
