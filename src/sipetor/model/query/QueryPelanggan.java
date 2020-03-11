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
import sipetor.model.Pelanggan;
import sipetor.model.interfaces.InterfacePelanggan;

/**
 *
 * @author User
 */
public class QueryPelanggan implements InterfacePelanggan{
    private Connection conn = SQLConnection.getConnection();
    
    @Override
    public boolean insert(Pelanggan data) {
      String sql = "INSERT INTO pelanggan values (?,?,?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, data.getTnkb());
            statement.setString(2, data.getNama_Pelanggan());
            statement.setString(3, data.getAlamat_Pelanggan());
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
        } catch (SQLException ex) { 
            Logger.getLogger(QueryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Pelanggan data) {
       String sql = "UPDATE pelanggan set nama_pelanggan=?,"
                    + " alamat_pelanggan=? where tnkb=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, data.getNama_Pelanggan());
            statement.setString(2, data.getAlamat_Pelanggan());  
            statement.setString(3, data.getTnkb());
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
        } catch (SQLException ex) { 
            Logger.getLogger(QueryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String Tnkb) {
        String sql = "DELETE FROM pelanggan where tnkb=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, Tnkb);
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
}

    @Override
    public List<Pelanggan> getAllPelanggan() {
      List<Pelanggan> listPelanggan = new ArrayList<>();
        String sql = "Select * from pelanggan order by tnkb asc";
        try{
            if(SQLConnection.getConnection()==null){
                return null;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Pelanggan p = new Pelanggan(
                            rs.getString(1),
                            rs.getString(2), 
                            rs.getString(3));
                            
                listPelanggan.add(p);
                }
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPelanggan;
    }

    @Override
    public List<Pelanggan> getAllPelangganByName(String nama_pelanggan) {
      List<Pelanggan> listPelanggan = new ArrayList<>();
        String sql = "Select * from pelanggan WHERE nama_pelanggan like '%"+nama_pelanggan+"%'";
        try{
            if(SQLConnection.getConnection()==null){
                return null;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Pelanggan s = new Pelanggan(rs.getString(1), rs.getString(2), rs.getString(3));
                                
                    listPelanggan.add(s);
                }
                statement.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return listPelanggan;
    
}
}
