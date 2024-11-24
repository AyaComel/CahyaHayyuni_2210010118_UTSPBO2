# CahyaHayyuni_2210010118_UTSPBO2
 Cahya Hayyuni - 2210010118 - UTS PBO2 - Aplikasi Agenda Pribadi 

 # Pembuat
 Nama : Cahya Hayyuni
 NPM  : 2210010118
 Kelas : 5A REG BJM

## 1. Deskripsi Program
Aplikasi Agenda Pribadi adalah aplikasi GUI berbasis Java untuk mengelola Agenda robadi yang membantu pengguna untuk mencatat, mengelola, dan mengatur jadwal atau kegiatan harian. Aplikasi ini memungkinkan pengguna untuk menambah, mengedit, menghapus, serta mencari agenda dengan mudah.

## 2. Komponen
Aplikasi ini dibuat menggunakan komponen GUI berikut: 
• JFrame: Sebagai kerangka utama aplikasi.

• JPanel: Wadah komponen GUI.

• JLabel: Label teks untuk elemen seperti "Tanggal", "Deskripsi", dll.

• JDateChooser: Input untuk Tanggal agenda.

• JTextArea: Input dan tampilan isi Deskripsi.

• JButton: Tombol untuk berbagai aksi (Simpan, Edit, Hapus, Cari, Ekspor, Keluar).

• JButton: Tombol untuk berbagai aksi (Simpan, Edit, Hapus, Cari, Ekspor, Keluar).

• JTable: Menampilkan daftar agenda.

• JFileChooser: Memilih file untuk ekspor data.

## 3. Fitur-fitur dan EventsUtama
## a. Menambah data Agenda
Pengguna dapat menambahkan Agenda baru dengan Tanggal dan Deskripsi.
~~~
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
~~~
## Tombol Tambah
~~~
private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {                                             
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
    }
~~~                                            
## b. Mengedit data Agenda
Pengguna dapat memperbaharui data Agenda.
~~~
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
~~~
## Tombol Edit
~~~
private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {                                           
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
    }                                          
~~~
## c. Menghapus data Agenda
Pengguna dapat menghapus Agenda tertentu.
~~~
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
                JOptionPane.showMessageDialog(this, "Data Agenda berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
~~~
## Tombol Hapus
~~~
private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {                                            
    int selectedRow = tabelAgenda.getSelectedRow(); // Tombol untuk menghapus data Agenda
    if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Pilih baris data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
    return;
    }
    int id = Integer.parseInt(tabelAgenda.getValueAt(selectedRow, 0).toString());
    dbHelper.hapusAgenda(id);
    loadAgendaData();
    JOptionPane.showMessageDialog(this, "Data Agenda berhasil dihapus!");
    }
~~~
## d. Mencari data Agenda
Pengguna dapat mencari Agenda berdasarkan Tanggal atau Deskripsi.
~~~
// Method untuk mencari data Agenda di tsbel
    private void cariAgenda() {
        String keyword = textCari.getText().toLowerCase();
        if (keyword.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }
    }
~~~
## Tombol Cari
~~~
private void buttonCariActionPerformed(java.awt.event.ActionEvent evt) {                                           
    String keyword = textCari .getText().toLowerCase(); // Tombol untuk mencari data Agenda di tabel
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
    }
~~~
## e. Mengekspor data Agenda ke CSV
Pengguna dapat mengekspor data Agenda dari Tabel ke CSV
~~~
// Method untuk Mengekspor data Agenda
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
~~~
## Tombol Ekspor
~~~
private void buttonEksporActionPerformed(java.awt.event.ActionEvent evt) {                                             
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
    }
~~~
## f. Tombol Keluar Aplikasi Agenda Pribadi
~~~
private void buttonKeluarActionPerformed(java.awt.event.ActionEvent evt) {                                             
    int confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0); // Tombol untuk keluar dari aplikasi
        }
    }
~~~

## 4. Class DatabaseHelper
Class DatabaseHelper adalah kelas yang digunakan untuk memudahkan interaksi antara aplikasi dan database. 
~~~
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

class Agenda {
    private int id;
    private String tanggal;
    private String deskripsi;

    public Agenda(int id, String tanggal, String deskripsi) {
        this.id = id;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}

public class DatabaseHelper {
    private Connection conn;
    
    public DatabaseHelper() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:agenda.db");
            createTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Membuat tabel agenda jika belum ada
    private void createTable() {
    String sqlCreateTable = "CREATE TABLE IF NOT EXISTS agenda (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "tanggal TEXT NOT NULL, " +
                            "Deskripsi TEXT NOT NULL)"; // Kolom 'judul' dihapus
    try (Statement stmt = conn.createStatement()) {
        stmt.execute(sqlCreateTable);
        System.out.println("Tabel agenda berhasil dibuat atau sudah ada.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error creating table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Untuk debugging
    }
}
    
    // Menambah agenda baru
    public boolean tambahAgenda(String tanggal, String deskripsi) throws SQLException {
    if (tanggal == null || deskripsi == null || tanggal.isEmpty() || deskripsi.isEmpty()) {
        System.err.println("Error: Data tidak lengkap. Tanggal: " + tanggal + ", Deskripsi: " + deskripsi);
        return false;
    }

    String sqlInsert = "INSERT INTO agenda (tanggal, Deskripsi) VALUES (?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
        pstmt.setString(1, tanggal);
        pstmt.setString(2, deskripsi);
        pstmt.executeUpdate();
        System.out.println("Agenda berhasil ditambahkan.");
        return true;
    } catch (SQLException e) {
        System.err.println("Error adding agenda: " + e.getMessage());
        return false;
    }
}

// Menghapus agenda berdasarkan ID
    public boolean hapusAgenda(int id) {
    String sqlDelete = "DELETE FROM agenda WHERE id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {
        pstmt.setInt(1, id);
        int rowsDeleted = pstmt.executeUpdate();
        return rowsDeleted > 0; // Mengembalikan true jika ada baris yang dihapus
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error menghapus agenda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false; // Mengembalikan false jika terjadi error
    }
}
    
    // Memperbarui agenda berdasarkan ID
    public boolean updateAgenda(String id, String tanggal, String deskripsi) throws SQLException {
    String sql = "UPDATE agenda SET tanggal = ?, deskripsi = ? WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, tanggal);
        stmt.setString(2, deskripsi);
        stmt.setString(3, id);
        return stmt.executeUpdate() > 0;
    }
}
    
    // Mengambil daftar agenda
    public List<Agenda> getAgendaList() {
        List<Agenda> agendaList = new ArrayList<>();
        String sqlSelect = "SELECT id, tanggal, deskripsi FROM agenda";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlSelect)) {
            while (rs.next()) {
                agendaList.add(new Agenda(rs.getInt("id"), rs.getString("tanggal"), rs.getString("deskripsi")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mengambil data agenda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return agendaList;
    }

    boolean updateAgenda(int id, String tanggal, String deskripsi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
~~~

## 5. Tampilan Aplikasi saat di Run
