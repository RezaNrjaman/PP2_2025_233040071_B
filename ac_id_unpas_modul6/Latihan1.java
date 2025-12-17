package ac_id_unpas_modul6;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class Latihan1 {
    public static void main(String[] args) {
        // 1. buat frame
        JFrame frame = new JFrame("Kalkulator Sederhana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        JTextField layar = new JTextField();
        frame.add(layar, BorderLayout.NORTH);

        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new GridLayout(4, 4, 5, 5));

        panelTombol.add(new JButton("7"));
        panelTombol.add(new JButton("8"));
        panelTombol.add(new JButton("9"));
        panelTombol.add(new JButton("/"));
        panelTombol.add(new JButton("4"));
        panelTombol.add(new JButton("5"));
        panelTombol.add(new JButton("6"));
        panelTombol.add(new JButton("x"));
        panelTombol.add(new JButton("1"));
        panelTombol.add(new JButton("2"));
        panelTombol.add(new JButton("3"));
        panelTombol.add(new JButton("+"));
        panelTombol.add(new JButton("C"));
        panelTombol.add(new JButton("0"));
        panelTombol.add(new JButton("-"));
        panelTombol.add(new JButton("="));

        //panelTombol.add(new JButton("'"));
        frame.add(panelTombol, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
