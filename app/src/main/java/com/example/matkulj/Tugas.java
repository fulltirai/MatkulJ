package com.example.matkulj;

public class Tugas {
    public String tugasId;
    public String namaTugas;
    public String deskripsi;
    public String deadline;

    public Tugas() {
        // Diperlukan Firebase
    }

    public Tugas(String tugasId, String namaTugas, String deskripsi, String deadline) {
        this.tugasId = tugasId;
        this.namaTugas = namaTugas;
        this.deskripsi = deskripsi;
        this.deadline = deadline;
    }

    // Getter dan Setter kalau perlu
}
