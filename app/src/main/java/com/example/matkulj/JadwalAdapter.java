package com.example.matkulj;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    private List<Jadwal> jadwalList;
    private Context context;

    public JadwalAdapter(Context context, List<Jadwal> jadwalList) {
        this.context = context;
        this.jadwalList = jadwalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jadwal jadwal = jadwalList.get(position);
        holder.namaMatkul.setText(jadwal.getMatkul());
        holder.hari.setText(jadwal.getHari());
        holder.jam.setText(jadwal.getJam());

        // Tambahkan click listener
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailJadwalActivity.class);
            intent.putExtra("matkul", jadwal.getMatkul());
            intent.putExtra("hari", jadwal.getHari());
            intent.putExtra("jam", jadwal.getJam());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaMatkul, hari, jam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaMatkul = itemView.findViewById(R.id.namaMatkul);
            hari = itemView.findViewById(R.id.hari);
            jam = itemView.findViewById(R.id.jam);
        }
    }
}
