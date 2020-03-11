/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipetor.model.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sipetor.connection.SQLConnection;
import sipetor.model.Karyawan;
import sipetor.model.interfaces.InterfaceKaryawan;

/**
 *
 * @author User
 */
public class QueryKaryawan implements InterfaceKaryawan{
    private Connection conn = SQLConnection.getConnection();
    
    @Override
    public boolean insert(Karyawan k) {
        String sql = "INSERT INTO karyawan values(?,?,?,?,?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, k.getNik());
            statement.setString(2, k.getNama_karyawan());
            statement.setString(3, k.getAlamat_karyawan());
            statement.setString(4, k.getEmail_karyawan());
            statement.setByte(5, k.getId_golongan());
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Karyawan k) {
        String sql = "UPDATE karyawan SET nama_karyawan=?,"
                +" alamat_karyawan=?,email_karyawan=?,id_golongan=?"
                +" WHERE nik=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, k.getNama_karyawan());
            statement.setString(2, k.getAlamat_karyawan());
            statement.setString(3, k.getEmail_karyawan());
            statement.setByte(4, k.getId_golongan());
            statement.setString(5, k.getNik());
            
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String nik) {
        String sql = "DELETE FROM karyawan where nik=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, nik);
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Karyawan> getAllKaryawan() {
        List<Karyawan> listkaryawan = new ArrayList<>();
        String sql = "Select * from karyawan";
        try{
            if(SQLConnection.getConnection()==null){
                return null;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Karyawan k = new Karyawan(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4), 
                            rs.getByte(5));
                listkaryawan.add(k);
                }
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listkaryawan;
    }

    @Override
    public Karyawan getOneKaryawanByID(String nik) {
        Karyawan output = null;
        String sql = "Select * from karyawan where nik=?";
        try{
            if(SQLConnection.getConnection()==null){
                return null;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, nik);
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    output = new Karyawan(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3), 
                                rs.getString(4), 
                                rs.getByte(5));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }

    @Override
    public List<Karyawan> getAllKaryawanByName(String nama_karyawan) {
     List<Karyawan> listKaryawan = new ArrayList<>();
        String sql = "Select * from karyawan WHERE nama_karyawan like '%"+nama_karyawan+"%'";
        try{
            if(SQLConnection.getConnection()==null){
                return null;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Karyawan s = new Karyawan(
                                rs.getString(1), 
                                rs.getString(2), 
                                rs.getString(3), 
                                rs.getString(4),
                                rs.getByte(5));
                    listKaryawan.add(s);
                }
                statement.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryKaryawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKaryawan;
    
    }
    
}
