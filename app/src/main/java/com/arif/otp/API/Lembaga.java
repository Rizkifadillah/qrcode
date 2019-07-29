

 package com.arif.otp.API;

import android.os.Parcel;
import android.os.Parcelable;

public class Lembaga implements Parcelable
{

    private String nPSN;
    private String nama_sekolah;
    private String bentuk_pendidikan;
    private String status_lembaga;
    private String sk_izin_operasional;
    private String tanggal_sk_izin_operasional;
    public final static Parcelable.Creator<Lembaga> CREATOR = new Creator<Lembaga>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Lembaga createFromParcel(Parcel in) {
            return new Lembaga(in);
        }

        public Lembaga[] newArray(int size) {
            return (new Lembaga[size]);
        }

    }
            ;

    protected Lembaga(Parcel in) {
        this.nPSN = ((String) in.readValue((String.class.getClassLoader())));
        this.nama_sekolah = ((String) in.readValue((String.class.getClassLoader())));
        this.bentuk_pendidikan = ((String) in.readValue((String.class.getClassLoader())));
        this.status_lembaga = ((String) in.readValue((String.class.getClassLoader())));
        this.sk_izin_operasional = ((String) in.readValue((String.class.getClassLoader())));
        this.tanggal_sk_izin_operasional = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Lembaga() {
    }

    public String getNPSN() {
        return nPSN;
    }

    public void setNPSN(String nPSN) {
        this.nPSN = nPSN;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    public String getBentuk_pendidikan() {
        return bentuk_pendidikan;
    }

    public void setBentuk_pendidikan(String bentuk_pendidikan) {
        this.bentuk_pendidikan = bentuk_pendidikan;
    }

    public String getStatus_lembaga() {
        return status_lembaga;
    }

    public void setStatus_lembaga(String status_lembaga) {
        this.status_lembaga = status_lembaga;
    }

    public String getSk_izin_operasional() {
        return sk_izin_operasional;
    }

    public void setSk_izin_operasional(String sk_izin_operasional) {
        this.sk_izin_operasional = sk_izin_operasional;
    }

    public String getTanggal_sk_izin_operasional() {
        return tanggal_sk_izin_operasional;
    }

    public void setTanggal_sk_izin_operasional(String tanggal_sk_izin_operasional) {
        this.tanggal_sk_izin_operasional = tanggal_sk_izin_operasional;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(nPSN);
        dest.writeValue(nama_sekolah);
        dest.writeValue(bentuk_pendidikan);
        dest.writeValue(status_lembaga);
        dest.writeValue(sk_izin_operasional);
        dest.writeValue(tanggal_sk_izin_operasional);
    }

    public int describeContents() {
        return 0;
    }

}