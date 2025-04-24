    package com.example.matkulj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editMatkul, editHari, editJam;
    Button btnSimpan;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editMatkul = findViewById(R.id.editMatkul);
        editHari = findViewById(R.id.editHari);
        editJam = findViewById(R.id.editJam);
        btnSimpan = findViewById(R.id.btnSimpan);

        database = FirebaseDatabase.getInstance().getReference("JMK");

        btnSimpan.setOnClickListener(v -> {
            String matkul = editMatkul.getText().toString();
            String hari = editHari.getText().toString();
            String jam = editJam.getText().toString();

            if (matkul.isEmpty() || hari.isEmpty() || jam.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            Jadwal jadwal = new Jadwal(matkul, hari, jam);
            database.push().setValue(jadwal);

            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, JadwalListActivity.class));
        });
    }
}
