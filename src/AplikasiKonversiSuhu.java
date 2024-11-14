import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author HADI PC
 */
public class AplikasiKonversiSuhu extends javax.swing.JFrame {
// Menyimpan arah konversi
    private String arahKonversi = "CelciusKeFahrenheit"; // Default

    /**
     * Creates new form AplikasiKonversiSuhu
     */
    public AplikasiKonversiSuhu() {
        initComponents();
        batasiInputAngka(txtInput);
        // Menambahkan listener pada JTextField untuk melakukan konversi otomatis
        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                konversiSuhu();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                konversiSuhu();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                konversiSuhu();
            }
        });
        
        // Menambahkan ItemListener pada JRadioButton
        rbCelcius.addItemListener(e -> {
            if (rbCelcius.isSelected()) {
                arahKonversi = "CelciusKeFahrenheit";
                konversiSuhu();
            }
        });

        rbFahrenheit.addItemListener(e -> {
            if (rbFahrenheit.isSelected()) {
                arahKonversi = "FahrenheitKeCelcius";
                konversiSuhu();
            }
        });

        rbKelvin.addItemListener(e -> {
            if (rbKelvin.isSelected()) {
                arahKonversi = "CelciusKeKelvin"; // Ganti sesuai kebutuhan
                konversiSuhu();
            }
        });

        rbReamur.addItemListener(e -> {
            if (rbReamur.isSelected()) {
                arahKonversi = "CelciusKeReamur"; // Ganti sesuai kebutuhan
                konversiSuhu();
            }
        });
    }

    // Fungsi utama untuk mengkonversi suhu
    private void konversiSuhu() {
        // Validasi input
        String inputStr = txtInput.getText();
        if (inputStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan nilai suhu yang valid", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double inputSuhu;
        try {
            inputSuhu = Double.parseDouble(inputStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai suhu harus berupa angka", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mendapatkan suhu input yang dipilih
        String suhuInput = cbbSuhuInput.getSelectedItem().toString();
        double hasilKonversi = 0.0;

        if (rbCelcius.isSelected()) {
            hasilKonversi = konversiDariCelcius(suhuInput, inputSuhu);
        } else if (rbFahrenheit.isSelected()) {
            hasilKonversi = konversiDariFahrenheit(suhuInput, inputSuhu);
        } else if (rbKelvin.isSelected()) {
            hasilKonversi = konversiDariKelvin(suhuInput, inputSuhu);
        } else if (rbReamur.isSelected()) {
            hasilKonversi = konversiDariReamur(suhuInput, inputSuhu);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih jenis suhu input", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtHasil.setText(String.format("%.2f", hasilKonversi));
    }

// Konversi dari Celcius ke suhu lainnya
    private double konversiDariCelcius(String suhuInput, double suhu) {
        switch (suhuInput) {
            case "Fahrenheit":
                return (suhu * 9 / 5) + 32;
            case "Kelvin":
                return suhu + 273.15;
            case "Reamur":
                return suhu * 4 / 5;
            default:
                return suhu; // jika Celcius
        }
    }

// Konversi dari Fahrenheit ke suhu lainnya
    private double konversiDariFahrenheit(String suhuInput, double suhu) {
        switch (suhuInput) {
            case "Celcius":
                return (suhu - 32) * 5 / 9;
            case "Kelvin":
                return (suhu - 32) * 5 / 9 + 273.15;
            case "Reamur":
                return (suhu - 32) * 4 / 9;
            default:
                return suhu; // jika Fahrenheit
        }
    }

// Konversi dari Kelvin ke suhu lainnya
    private double konversiDariKelvin(String suhuInput, double suhu) {
        switch (suhuInput) {
            case "Celcius":
                return suhu - 273.15;
            case "Fahrenheit":
                return (suhu - 273.15) * 9 / 5 + 32;
            case "Reamur":
                return (suhu - 273.15) * 4 / 5;
            default:
                return suhu; // jika Kelvin
        }
    }

    private void batasiInputAngka(javax.swing.JTextField textField) {
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char karakter = evt.getKeyChar();

                // Memeriksa apakah karakter bukan angka atau bukan backspace
                // Memeriksa apakah karakter bukan angka dan bukan karakter kontrol (seperti backspace)
                if (!Character.isDigit(karakter) && karakter != KeyEvent.VK_BACK_SPACE) {
                    evt.consume(); // Mengabaikan karakter yang bukan angka atau backspace
                    JOptionPane.showMessageDialog(null, "Input hanya boleh angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
// Konversi dari Reamur ke suhu lainnya
    private double konversiDariReamur(String suhuInput, double suhu) {
        switch (suhuInput) {
            case "Celcius":
                return suhu * 5 / 4;
            case "Fahrenheit":
                return (suhu * 9 / 4) + 32;
            case "Kelvin":
                return (suhu * 5 / 4) + 273.15;
            default:
                return suhu; // jika Reamur
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtInput = new javax.swing.JTextField();
        btnKonversi = new javax.swing.JButton();
        txtHasil = new javax.swing.JTextField();
        cbbSuhuInput = new javax.swing.JComboBox<>();
        rbCelcius = new javax.swing.JRadioButton();
        rbFahrenheit = new javax.swing.JRadioButton();
        rbKelvin = new javax.swing.JRadioButton();
        rbReamur = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aplikasi Konversi Suhu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Input Nilai");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(23, 23, 23, 23);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Hasil");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(23, 23, 23, 23);
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(23, 23, 23, 23);
        jPanel1.add(txtInput, gridBagConstraints);

        btnKonversi.setText("Konversi");
        btnKonversi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonversiActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(23, 23, 23, 23);
        jPanel1.add(btnKonversi, gridBagConstraints);

        txtHasil.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(23, 23, 23, 23);
        jPanel1.add(txtHasil, gridBagConstraints);

        cbbSuhuInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Celcius", "Fahrenheit", "Kelvin", "Reamur" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(cbbSuhuInput, gridBagConstraints);

        buttonGroup1.add(rbCelcius);
        rbCelcius.setText("Celcius");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(rbCelcius, gridBagConstraints);

        buttonGroup1.add(rbFahrenheit);
        rbFahrenheit.setSelected(true);
        rbFahrenheit.setText("Fahrenheit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(rbFahrenheit, gridBagConstraints);

        buttonGroup1.add(rbKelvin);
        rbKelvin.setText("Kelvin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(rbKelvin, gridBagConstraints);

        buttonGroup1.add(rbReamur);
        rbReamur.setText("Reamur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(rbReamur, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKonversiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonversiActionPerformed
        konversiSuhu();
    }//GEN-LAST:event_btnKonversiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplikasiKonversiSuhu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiKonversiSuhu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiKonversiSuhu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiKonversiSuhu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiKonversiSuhu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKonversi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbSuhuInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbCelcius;
    private javax.swing.JRadioButton rbFahrenheit;
    private javax.swing.JRadioButton rbKelvin;
    private javax.swing.JRadioButton rbReamur;
    private javax.swing.JTextField txtHasil;
    private javax.swing.JTextField txtInput;
    // End of variables declaration//GEN-END:variables
}
