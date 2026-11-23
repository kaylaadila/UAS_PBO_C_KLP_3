# STUDENT MANAGEMENT SYSTEM
### UAS Praktikum Pemrograman Berorientasi Objek (PBO) – Kelompok 3

## 1. Deskripsi Sistem

**Student Management System** merupakan aplikasi berbasis Command Line Interface (CLI) yang dikembangkan menggunakan bahasa pemrograman Java. Aplikasi ini bertujuan untuk membantu proses pengelolaan data siswa, kelas, mata pelajaran, pendaftaran siswa, serta nilai akademik secara terstruktur.

Sistem ini memiliki dua jenis pengguna, yaitu **Admin** dan **User**, yang mempunyai hak akses dan fungsi berbeda. Admin bertugas mengelola seluruh data yang terdapat dalam sistem, sedangkan User dapat melihat informasi, melakukan pendaftaran sebagai siswa, melihat riwayat pendaftaran, serta memeriksa nilai akademik.

Aplikasi menggunakan file berekstensi `.txt` sebagai media penyimpanan data sehingga tidak memerlukan database maupun library tambahan.

### Fitur Utama

**A. Admin**
- Login sebagai Admin.
- Menambahkan, mengedit, menghapus, mencari, dan melihat data siswa.
- Mengelola data kelas.
- Mengelola data mata pelajaran.
- Memasukkan dan mengelola nilai siswa.
- Memverifikasi pendaftaran siswa.
- Melihat riwayat pendaftaran.
- Melihat data seluruh pengguna.

**B. User**
- Registrasi akun baru dan login.
- Melihat profil pengguna.
- Melihat daftar kelas dan mata pelajaran.
- Mengajukan pendaftaran sebagai siswa.
- Melihat riwayat pendaftaran.
- Melihat nilai akademik, rata-rata, dan predikat.

**Teknologi yang Digunakan**
- Bahasa Pemrograman: Java
- Konsep Pemrograman: Object-Oriented Programming (OOP)
- Penyimpanan Data: File .txt
- Antarmuka: Command Line Interface (CLI)
- Java Development Kit: JDK 11 atau lebih baru

**Konsep OOP yang Diterapkan**
- Encapsulation: Menggunakan atribut private serta getter dan setter untuk melindungi data.
- Inheritance: Menggunakan pewarisan antarclass, seperti Admin dan User yang mewarisi Account.
- Polymorphism: Menggunakan method overriding pada beberapa class.
- Abstraction: Menggunakan abstract class dan interface.
- Generic: Menggunakan generic class pada pengelolaan data melalui BaseService.

## 2. Cara Menjalankan Program
**A. Persyaratan**
Sebelum menjalankan program, pastikan perangkat telah memiliki:

JDK versi 11 atau lebih baru.
Terminal atau Command Prompt.
Source code proyek Student Management System.

**B. Struktur Folder**
Pastikan struktur folder proyek tersusun seperti berikut:

UASPBO_KLP3/
│
```text
UASPBO_KLP3/
│
├── src/
│   ├── Main.java
│   │
│   ├── model/
│   ├── service/
│   ├── menu/
│   └── util/
│
├── data/
│   ├── accounts.txt
│   ├── students.txt
│   ├── classes.txt
│   ├── subjects.txt
│   ├── registrations.txt
│   └── grades.txt
│
└── README.md
```

### C. Langkah Menjalankan Program

**1. Buka folder proyek**

Buka terminal atau Command Prompt, kemudian arahkan direktori ke folder utama proyek `UASPBO_KLP3`.

**2. Kompilasi seluruh file Java**

Jalankan perintah berikut:

```bash
javac -d bin src/*.java src/model/*.java src/service/*.java src/menu/*.java src/util/*.java
```

Perintah tersebut digunakan untuk mengompilasi seluruh file Java dan menyimpan hasil kompilasi ke dalam folder `bin`.

**3. Jalankan program**

Setelah proses kompilasi berhasil, jalankan perintah:

```bash
java -cp bin Main
```

**4. Mulai menggunakan aplikasi**

Setelah program dijalankan, menu utama akan ditampilkan. Pengguna dapat memilih menu Login atau Registrasi Akun Baru sesuai kebutuhan.

### D. Akun Login Demo

Gunakan akun berikut untuk mencoba aplikasi:

| Role | Username | Password |
|---|---|---|
| Admin | `admin` | `admin123` |
| User | `siswa` | `siswa123` |
