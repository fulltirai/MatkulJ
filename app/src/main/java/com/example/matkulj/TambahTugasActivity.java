package com.example.matkulj;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahTugasActivity extends AppCompatActivity {
    EditText etNama, etDeskripsi, etDeadline;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tugas);

        etNama = findViewById(R.id.etNamaTugas);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        etDeadline = findViewById(R.id.etDeadline);
        btnSimpan = findViewById(R.id.btnSimpan);

        String matkul = getIntent().getStringExtra("matkul");
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        btnSimpan.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            String desk = etDeskripsi.getText().toString();
            String deadline = etDeadline.getText().toString();

            if (nama.isEmpty() || desk.isEmpty() || deadline.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            // Referensi ke Firebase /Tugas/<UserId>/<NamaMatkul>/
            DatabaseReference tugasRef = FirebaseDatabase.getInstance()
                    .getReference("Tugas")
                    .child(userId)
                    .child(matkul);

            // Buat ID tugas unik
            String tugasId = tugasRef.push().getKey();

            // Buat objek Tugas
            Tugas tugas = new Tugas(tugasId, nama, desk, deadline);

            // Simpan ke Firebase
            tugasRef.child(tugasId).setValue(tugas)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Tugas berhasil disimpan!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Gagal menyimpan tugas", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
