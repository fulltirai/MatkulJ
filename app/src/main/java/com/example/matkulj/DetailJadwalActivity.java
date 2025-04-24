package com.example.matkulj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailJadwalActivity extends AppCompatActivity {

    TextView tvMatkul, tvHari, tvJam, tvDosen, tvLokasi;
    Button btnTambahTugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);

        // Inisialisasi TextView
        tvMatkul = findViewById(R.id.tvMatkul);
        tvHari = findViewById(R.id.tvHari);
        tvJam = findViewById(R.id.tvJam);
        tvDosen = findViewById(R.id.tvDosen);
        tvLokasi = findViewById(R.id.tvLokasi);
        btnTambahTugas = findViewById(R.id.btnTambahTugas); // Ini view tombol yang akan kita tambahkan di XML

        // Ambil data dari Intent
        String matkul = getIntent().getStringExtra("matkul");
        String hari = getIntent().getStringExtra("hari");
        String jam = getIntent().getStringExtra("jam");
        String dosen = getIntent().getStringExtra("dosen");
        String lokasi = getIntent().getStringExtra("lokasi");

        // Set data ke tampilan
        tvMatkul.setText(matkul);
        tvHari.setText(hari);
        tvJam.setText(jam);
        tvDosen.setText(dosen);
        tvLokasi.setText(lokasi);

        // Set onClick untuk tombol tambah tugas
        btnTambahTugas.setOnClickListener(v -> {
            Intent intent = new Intent(DetailJadwalActivity.this, TambahTugasActivity.class);
            intent.putExtra("matkul", matkul); // ini penting
            startActivity(intent);
        });

    }
}
