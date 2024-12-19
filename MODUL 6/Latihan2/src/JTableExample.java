import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableExample {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Form Tabel dengan Input Pengguna");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());


        String[][] data = {
                {"1", "Budi", "Santoso", "25"},
                {"2", "Siti", "Nurhaliza", "30"},
                {"3", "Ahmad", "Suhendra", "40"},
                {"4", "Dewi", "Putri", "35"}
        };

        String[] columns = {"ID", "Nama Depan", "Nama Belakang", "Usia"};


        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField idField = new JTextField(20);
        JTextField namaDepanField = new JTextField(20);
        JTextField namaBelakangField = new JTextField(20);
        JTextField usiaField = new JTextField(20);

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Nama Depan:"));
        panel.add(namaDepanField);
        panel.add(new JLabel("Nama Belakang:"));
        panel.add(namaBelakangField);
        panel.add(new JLabel("Usia:"));
        panel.add(usiaField);

        JButton addButton = new JButton("Tambah Data");
        panel.add(addButton);

        frame.add(panel, BorderLayout.NORTH);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = idField.getText().trim();
                String namaDepan = namaDepanField.getText().trim();
                String namaBelakang = namaBelakangField.getText().trim();
                String usia = usiaField.getText().trim();


                if (id.isEmpty() || namaDepan.isEmpty() || namaBelakang.isEmpty() || usia.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Integer.parseInt(usia);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Usia harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                model.addRow(new Object[]{id, namaDepan, namaBelakang, usia});


                idField.setText("");
                namaDepanField.setText("");
                namaBelakangField.setText("");
                usiaField.setText("");
            }
        });


        frame.setVisible(true);
    }
}
