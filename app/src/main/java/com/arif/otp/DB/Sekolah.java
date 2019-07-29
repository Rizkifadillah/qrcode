package com.arif.otp.DB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Angga Kristiono on 08/06/2019.
 */
@Entity
public class Sekolah {
    //Model
    @PrimaryKey
    @ColumnInfo(name = "NPSN")
    int NPSN;
    @ColumnInfo(name = "nama_sekolah")
    String nama_sekolah;
    @ColumnInfo(name = "bentuk_pendidikan")
    String bentuk_pendidikan;
    @ColumnInfo(name = "status_lembaga")
    String status_lembaga;
    @ColumnInfo(name = "sk_izin_operasional")
    String sk_izin_operasional;
    @ColumnInfo(name = "tanggal_sk_izin_operasional")
    String tanggal_sk_izin_operasional;
    @ColumnInfo(name = "alamat")
    String alamat;
    @ColumnInfo(name = "nama_dusun")
    String nama_dusun;
    @ColumnInfo(name = "provinsi")
    String provinsi;
    @ColumnInfo(name = "kecamatan")
    String kecamatan;
    @ColumnInfo(name = "kabupaten")
    String kabupaten;
    @ColumnInfo(name = "nomor_telpon")
    int nomor_telepon;
    @ColumnInfo(name = "email")
    String email;



//    public static class DateTypeConverter {
//        @TypeConverter
//        public Date fromTimestamp(Long value) {
//            return value == null ? null : new Date(value);
//        }
//
//        @TypeConverter
//        public Long dateToTimestamp(Date date) {
//            if (date == null) {
//                return null;
//            } else {
//                return date.getTime();
//            }
//        }
//    }

    public int getNPSN() {
        return NPSN;
    }
    public void setNPSN(int NPSN) {
        this.NPSN = NPSN;
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

    public String getTanggal_sk_izin_operasionall() {
        return tanggal_sk_izin_operasional;
    }
    public void setTanggal_sk_izin_operasional(String tanggal_sk_izin_operasional) {
        this.tanggal_sk_izin_operasional = tanggal_sk_izin_operasional;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama_dusun() {
        return nama_dusun;
    }

    public void setNama_dusun(String nama_dusun) {
        this.nama_dusun = nama_dusun;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public Integer getNomor_telepon() {
        return nomor_telepon;
    }

    public void setNomor_telepon(Integer nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
