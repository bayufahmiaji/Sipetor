/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipetor.model;

/**
 *
 * @author User
 */
public class Pelanggan {
    private String Tnkb;
    private String Nama_Pelanggan;
    private String Alamat_Pelanggan;
    
    public Pelanggan(String tnkb, String nama_pelanggan, String alamat_pelanggan){
        this.Tnkb = tnkb;
        this.Nama_Pelanggan = nama_pelanggan;
        this.Alamat_Pelanggan = alamat_pelanggan;
    }

    public String getTnkb() {
        return Tnkb;
    }

    public void setTnkb(String Tnkb) {
        this.Tnkb = Tnkb;
    }

    public String getNama_Pelanggan() {
        return Nama_Pelanggan;
    }

    public void setNama_Pelanggan(String Nama_Pelanggan) {
        this.Nama_Pelanggan = Nama_Pelanggan;
    }

    public String getAlamat_Pelanggan() {
        return Alamat_Pelanggan;
    }

    public void setAlamat_Pelanggan(String Alamat_Pelanggan) {
        this.Alamat_Pelanggan = Alamat_Pelanggan;
    }
    
}
