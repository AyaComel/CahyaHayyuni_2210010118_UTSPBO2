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

## 3. Fitur-fitur Utama
## a. Tambah Agenda
Pengguna dapat menambahkan Agenda baru dengan Tanggal dan kegiatan.
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
