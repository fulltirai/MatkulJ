package com.example.matkulj;

public class Jadwal {
    private String matkul, hari, jam;

    public Jadwal() {}

    public Jadwal(String matkul, String hari, String jam) {
        this.matkul = matkul;
        this.hari = hari;
        this.jam = jam;
    }

    public String getMatkul() { return matkul; }
    public String getHari() { return hari; }
    public String getJam() { return jam; }
}
