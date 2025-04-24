package com.example.matkulj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class JadwalListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JadwalAdapter adapter;
    private List<Jadwal> jadwalList;
    private ImageButton btnTambah;
    private DatabaseReference database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_list);

        recyclerView = findViewById(R.id.recyclerView);
        btnTambah = findViewById(R.id.btnTambah);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jadwalList = new ArrayList<>();

        // Kirim this (Context) ke adapter
        adapter = new JadwalAdapter(this, jadwalList);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance().getReference("JMK");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jadwalList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Jadwal jadwal = data.getValue(Jadwal.class);
                    if (jadwal != null) {
                        jadwalList.add(jadwal);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error jika perlu
            }
        });

        btnTambah.setOnClickListener(v -> {
            startActivity(new Intent(JadwalListActivity.this, MainActivity.class));
        });
    }
}
