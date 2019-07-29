package com.arif.otp.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

/**
 * Created by Angga Kristiono on 08/06/2019.
 */
//Interface menghendle query / controller
@Dao
public interface sekolahDAO {

    @Query("SELECT * FROM Sekolah")
    List<Sekolah>getAll();

//    @Query("SELECT * FROM Sekolah WHERE NPSN LIKE :NPSN AND nama_sekolah LIKE :nama_dusun AND bentuk_pendidikan LIKE :bentuk_pendidikan AND status_lembaga LIKE :status_lembaga AND sk_izin_operasional LIKE :sk_izin_operasional AND tanggal_sk_izin_operasional LIKE :tanggal_sk_izin_operasional")
//    Sekolah findByName(Integer NPSN, String nama_sekolah, String bentuk_pendidikan, String status_lembaga, String sk_izin_operasional, Date tanggal_sk_izin_operasional);

    @Query("SELECT COUNT (NPSN) FROM Sekolah")
    int getAllCount();

    @Query("UPDATE Sekolah SET alamat = :alamat, nama_dusun = :nama_dusun, provinsi = :provinsi, kecamatan = :kecamatan, kabupaten = :kabupaten, nomor_telpon = :nomor_telpon, email = :email")
    void update(String alamat,String nama_dusun, String provinsi, String kecamatan, String kabupaten, Integer nomor_telpon, String email);

    @Insert
    void insertAll(Sekolah sekolah);

    @Delete
    public void deleteSekolah(Sekolah NPSN);

    @Update
    public void update(Sekolah sekolah);

    @Delete
    public void deleteAll(Sekolah user1, Sekolah user2);


}
