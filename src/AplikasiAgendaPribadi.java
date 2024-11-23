import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class AplikasiAgendaPribadi extends javax.swing.JFrame {
    private final DatabaseHelper dbHelper;
    private final TableRowSorter<DefaultTableModel> sorter;
    private final DefaultTableModel model;
    private String TanggalBaru;

    public AplikasiAgendaPribadi() {
        initComponents();
        dbHelper = new DatabaseHelper();
        model = (DefaultTableModel) tabelAgenda.getModel();
        sorter = new TableRowSorter<>(model);
        tabelAgenda.setRowSorter(sorter);
        // Tambahkan listener hanya sekali di sini
        addTableSelectionListener();
        loadAgendaData();
        
        tabelAgenda.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting() && tabelAgenda.getSelectedRow() != -1) {
        int selectedRow = tabelAgenda.getSelectedRow();
        String tanggalString = (String) tabelAgenda.getValueAt(selectedRow, 1); // Ambil data tanggal
        String deskripsiString = (String) tabelAgenda.getValueAt(selectedRow, 2); // Ambil data deskripsi

        // Sesuaikan format dengan data tabel
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        
        try {
            java.util.Date utilDate = format.parse(tanggalString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Parsing string ke objek Date
            java.util.Date TanggalString = null;
            DateTanggal.setDate(TanggalString); // Set tanggal ke jDateChooser
            TextAreaAgenda.setText(deskripsiString); // Set deskripsi agenda ke jTextArea
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Gagal mengkonversi tanggal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Debugging
        }
    }
});
   }
    
    // Method untuk menambahkan data Agenda
    private void tambahAgenda() throws SQLException {
        String tanggal = ((JTextField)DateTanggal.getDateEditor().getUiComponent()).getText();
        String deskripsi = TextAreaAgenda.getText();

        if (tanggal.isEmpty() || deskripsi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (dbHelper.tambahAgenda(tanggal, deskripsi)) {
            loadAgendaData();
            JOptionPane.showMessageDialog(this, "Deskripsi berhasil ditambahkan!");
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan Deskripsi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method untuk mengedit data Agenda
    private void updateAgenda() {
        int selectedRow = tabelAgenda.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diperbarui!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tabelAgenda.getValueAt(selectedRow, 0).toString());
        String tanggal = ((JTextField)DateTanggal.getDateEditor().getUiComponent()).getText();
        String deskripsi = TextAreaAgenda.getText();

        if (tanggal.isEmpty() || deskripsi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (dbHelper.updateAgenda(id, tanggal, deskripsi)) {
            loadAgendaData();
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
            resetForm();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method untuk mengahapus data Agenda
    private void hapusAgenda() {
        int selectedRow = tabelAgenda.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tabelAgenda.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (dbHelper.hapusAgenda(id)) {
                loadAgendaData();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Method untuk mencari data Agenda di tsbel
    private void cariAgenda() {
        String keyword = textCari.getText().toLowerCase();
        if (keyword.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }
    }

    private void resetForm() {
        DateTanggal.setDate(null);
        TextAreaAgenda.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tanggal = new javax.swing.JLabel();
        deskripsi = new javax.swing.JLabel();
        DateTanggal = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaAgenda = new javax.swing.JTextArea();
        buttonTambah = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelAgenda = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        textCari = new javax.swing.JTextField();
        buttonCari = new javax.swing.JButton();
        buttonEkspor = new javax.swing.JButton();
        buttonKeluar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));

        tanggal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tanggal.setForeground(new java.awt.Color(153, 0, 153));
        tanggal.setText("Tanggal :");

        deskripsi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deskripsi.setForeground(new java.awt.Color(153, 0, 153));
        deskripsi.setText("Deskripsi :");

        DateTanggal.setDateFormatString("dd MM yyyy");

        TextAreaAgenda.setColumns(20);
        TextAreaAgenda.setRows(5);
        TextAreaAgenda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextAreaAgendaFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(TextAreaAgenda);

        buttonTambah.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonTambah.setForeground(new java.awt.Color(204, 0, 204));
        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });

        buttonEdit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonEdit.setForeground(new java.awt.Color(204, 0, 204));
        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonHapus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonHapus.setForeground(new java.awt.Color(204, 0, 204));
        buttonHapus.setText("Hapus");
        buttonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusActionPerformed(evt);
            }
        });

        tabelAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabelAgenda);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 153));
        jLabel4.setText("Pencarian Agenda :");

        buttonCari.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonCari.setForeground(new java.awt.Color(204, 0, 204));
        buttonCari.setText("Cari");
        buttonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariActionPerformed(evt);
            }
        });

        buttonEkspor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonEkspor.setForeground(new java.awt.Color(204, 0, 204));
        buttonEkspor.setText("Export");
        buttonEkspor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEksporActionPerformed(evt);
            }
        });

        buttonKeluar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttonKeluar.setForeground(new java.awt.Color(204, 0, 204));
        buttonKeluar.setText("keluar");
        buttonKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonHapus)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deskripsi)
                                    .addComponent(tanggal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(DateTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(138, 138, 138))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonTambah))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(buttonCari, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonEkspor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tanggal)
                    .addComponent(DateTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deskripsi)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCari, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEkspor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(buttonKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 153, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel1.setText("APLIKASI AGENDA PRIBADI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabel1)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
    String tanggal = ((JTextField) DateTanggal.getDateEditor().getUiComponent()).getText();
    String deskripsi = TextAreaAgenda.getText();

    if (tanggal.isEmpty() || deskripsi.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

        try {
            if (dbHelper.tambahAgenda(tanggal, deskripsi)) {
                loadAgendaData();
                JOptionPane.showMessageDialog(this, "Data Agenda berhasil ditambahkan!");
            }   } catch (SQLException ex) {
            Logger.getLogger(AplikasiAgendaPribadi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
    int selectedRow = tabelAgenda.getSelectedRow(); // Tombol untuk menambahkan data Agenda

    // Validasi baris yang dipilih
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris data yang ingin diperbarui!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Ambil model tabel
    DefaultTableModel model = (DefaultTableModel) tabelAgenda.getModel();

    // Validasi jumlah kolom
    if (model.getColumnCount() < 3) {
        JOptionPane.showMessageDialog(this, "Tabel tidak memiliki kolom yang cukup!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Ambil data dari form input
    String tanggal = ((JTextField) DateTanggal.getDateEditor().getUiComponent()).getText();
    String deskripsi = TextAreaAgenda.getText();

    // Validasi input
    if (tanggal.isEmpty() || deskripsi.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Perbarui data di tabel
    model.setValueAt(tanggal, selectedRow, 1); // Kolom untuk tanggal
    model.setValueAt(deskripsi, selectedRow, 2); // Kolom untuk deskripsi

    // Ambil ID dari tabel (kolom pertama)
    String id = model.getValueAt(selectedRow, 0).toString(); // Pastikan kolom 0 adalah ID
        try {
            // Perbarui database
            if (!dbHelper.updateAgenda(id, tanggal, deskripsi)) {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Data Agenda berhasil diperbarui!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AplikasiAgendaPribadi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed
    int selectedRow = tabelAgenda.getSelectedRow(); // Tombol untuj menghapus data Agenda
    if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Pilih baris data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
    return;
    }
    int id = Integer.parseInt(tabelAgenda.getValueAt(selectedRow, 0).toString());
    dbHelper.hapusAgenda(id);
    loadAgendaData();
    JOptionPane.showMessageDialog(this, "Data Agenda berhasil dihapus!");
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void buttonKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKeluarActionPerformed
    int confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0); // Tombol untuk keluar dari aplikasi
        }
    }//GEN-LAST:event_buttonKeluarActionPerformed

    private void buttonEksporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEksporActionPerformed
    JFileChooser fileChooser = new JFileChooser(); // Tombol untuk menegkspor data Ke CSV
    fileChooser.setDialogTitle("Simpan File");
    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
    File fileToSave = fileChooser.getSelectedFile();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave + ".csv"))) {
     DefaultTableModel model = (DefaultTableModel) tabelAgenda.getModel();
     for (int i = 0; i < model.getColumnCount(); i++) {
         bw.write(model.getColumnName(i) + ",");
     }
     bw.newLine();
     for (int i = 0; i < model.getRowCount(); i++) {
         for (int j = 0; j < model.getColumnCount(); j++) {
             bw.write(model.getValueAt(i, j).toString() + ",");
         }
         bw.newLine();
    }
     JOptionPane.showMessageDialog(this, "Data Agenda berhasil diekspor!");
    } catch (IOException ex) {
     JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}
    }//GEN-LAST:event_buttonEksporActionPerformed

    private void buttonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariActionPerformed
    String keyword = textCari .getText().toLowerCase(); // Tombol untuk mencari data Agenda di tabel
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
    }//GEN-LAST:event_buttonCariActionPerformed

    private void TextAreaAgendaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextAreaAgendaFocusGained
    TextAreaAgenda.setText("");
    }//GEN-LAST:event_TextAreaAgendaFocusGained

    private void EksporKeCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave + ".csv"))) {
                // Menulis header kolom
                bw.write("ID,Tanggal,Deskripsi\n"); // Sesuaikan dengan kolom tabel Anda

                // Menulis data dari model tabel
                DefaultTableModel model = (DefaultTableModel) tabelAgenda.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        bw.write(model.getValueAt(i, j).toString());
                        if (j < model.getColumnCount() - 1) {
                            bw.write(",");
                        }
                    }
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Data Agenda berhasil diekspor!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengekspor data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     private void loadAgendaData() {
        model.setRowCount(0);
        List<Agenda> agendaList = dbHelper.getAgendaList();
        for (Agenda agenda : agendaList) {
            model.addRow(new Object[]{agenda.getId(), agenda.getTanggal(), agenda.getDeskripsi()});
        }
    }

    private void addTableSelectionListener() {
    tabelAgenda.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting() && tabelAgenda.getSelectedRow() != -1) {
            int selectedRow = tabelAgenda.getSelectedRow();
            String tanggalString = (String) tabelAgenda.getValueAt(selectedRow, 1);
            String deskripsiString = (String) tabelAgenda.getValueAt(selectedRow, 2);

            // Sesuaikan format dengan data tabel
            SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy"); 
            try {
                java.util.Date utilDate = format.parse(tanggalString); // Parsing tanggal
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Konversi ke SQL Date

                DateTanggal.setDate(utilDate); // Menggunakan java.util.Date untuk JDateChooser
                TextAreaAgenda.setText(deskripsiString); // Set kegiatan ke jTextArea
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Gagal mengkonversi tanggal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    });
}
    
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
            java.util.logging.Logger.getLogger(AplikasiAgendaPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplikasiAgendaPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplikasiAgendaPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplikasiAgendaPribadi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplikasiAgendaPribadi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateTanggal;
    private javax.swing.JTextArea TextAreaAgenda;
    private javax.swing.JButton buttonCari;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonEkspor;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonKeluar;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JLabel deskripsi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelAgenda;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField textCari;
    // End of variables declaration//GEN-END:variables
}
