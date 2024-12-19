package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KasirSewaApartemenGUI {
    private JFrame frame;
    private JTextField namaField;
    private JTextField lamaSewaField;
    private JLabel hasilLabel;
    private JTable table;
    private DefaultTableModel tableModel;

    public KasirSewaApartemenGUI() {

        frame = new JFrame("Kasir Sewa Apartemen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));


        JLabel headerLabel = new JLabel("Kasir Sewa Apartemen", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(0, 102, 204));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainPanel.add(headerLabel, BorderLayout.NORTH);


        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(new Color(240, 248, 255));


        JLabel namaLabel = new JLabel("Nama Penyewa:");
        namaField = new JTextField();
        namaLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        JLabel lamaSewaLabel = new JLabel("Lama Sewa (hari):");
        lamaSewaField = new JTextField();
        lamaSewaLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        formPanel.add(namaLabel);
        formPanel.add(namaField);
        formPanel.add(lamaSewaLabel);
        formPanel.add(lamaSewaField);


        JPanel actionPanel = new JPanel(new BorderLayout());
        actionPanel.setBackground(new Color(240, 248, 255));


        JButton hitungButton = new JButton("Hitung Total");
        hitungButton.setFont(new Font("Arial", Font.BOLD, 16));
        hitungButton.setBackground(new Color(0, 153, 76));
        hitungButton.setForeground(Color.WHITE);


        hasilLabel = new JLabel("Total Harga: Rp 0", SwingConstants.CENTER);
        hasilLabel.setFont(new Font("Arial", Font.BOLD, 18));
        hasilLabel.setForeground(new Color(255, 51, 51));
        hasilLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        actionPanel.add(hitungButton, BorderLayout.NORTH);
        actionPanel.add(hasilLabel, BorderLayout.CENTER);


        String[] columnNames = {"Nama Penyewa", "Lama Sewa (hari)", "Total Harga"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);


        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);
        mainPanel.add(tableScrollPane, BorderLayout.EAST);


        frame.add(mainPanel);


        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesHitung();
            }
        });


        frame.setVisible(true);
    }

    private void prosesHitung() {
        String nama = namaField.getText().trim();
        String lamaSewaText = lamaSewaField.getText().trim();

        // Validasi inputan
        if (nama.isEmpty() || lamaSewaText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int lamaSewa = Integer.parseInt(lamaSewaText);
            if (lamaSewa <= 0) {
                JOptionPane.showMessageDialog(frame, "Lama sewa harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            double totalHarga = lamaSewa * 250000;
            hasilLabel.setText(String.format("Total Harga: Rp %.2f", totalHarga));


            tableModel.addRow(new Object[]{nama, lamaSewa, String.format("Rp %.2f", totalHarga)});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Lama sewa harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KasirSewaApartemenGUI::new);
    }
}
