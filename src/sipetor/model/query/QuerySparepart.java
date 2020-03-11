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
import sipetor.model.Sparepart;
import sipetor.model.interfaces.InterfaceSparepart;

/**
 *
 * @author User
 */
public class QuerySparepart implements InterfaceSparepart{
    private Connection conn = SQLConnection.getConnection();
    
    @Override
    public boolean insert(Sparepart data) {
         String sql = "INSERT INTO sparepart values (?,?,?,?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setInt(1, data.getId_sparepart());
            statement.setString(2, data.getNama_sparepart());
            statement.setInt(3, data.getHarga_sparepart());
            statement.setInt(4, data.getStock_sparepart());
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuerySparepart.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return false;
 }

    @Override
    public boolean update(Sparepart data) {
    String sql = "UPDATE sparepart set nama_sparepart=? ,"
            + " harga_sparepart=? ,"
            + " stock_sparepart=? where id_sparepart=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, data.getNama_sparepart());
            statement.setInt(2, data.getHarga_sparepart());
            statement.setInt(3, data.getStock_sparepart());
            statement.setInt(4, data.getId_sparepart());
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuerySparepart.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return false;   
    }

    @Override
    public boolean delete(int id_sparepart) {
       String sql = "DELETE FROM sparepart where id_sparepart=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setInt(1, id_sparepart);
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuerySparepart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;}

    @Override
    public int generateID() {
      int id_sparepart = 0;
        String sql = "EXEC genIDSparepart";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id_sparepart = rs.getInt(1);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryGolongan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_sparepart;
    }

    @Override
    public List<Sparepart> getAllSparepart() {
       List<Sparepart> listSparepart = new ArrayList<>();
        String sql = "Select * from sparepart";
        try{
            if(SQLConnection.getConnection()==null){
                return null;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Sparepart s = new Sparepart(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3), 
                            rs.getInt(4));
                            
                listSparepart.add(s);
                }
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryGolongan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSparepart;
    }

    @Override
    public List<Sparepart> getAllSparepartByName(String nama_Sparepart) {
     List<Sparepart> listSparepart = new ArrayList<>();
        String sql = "Select * from sparepart WHERE nama_sparepart like '%"+nama_Sparepart+"%'";
        try{
            if(SQLConnection.getConnection()==null){
                return null;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Sparepart s = new Sparepart(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                                
                    listSparepart.add(s);
                }
                statement.close();
            }
             } catch (SQLException ex) {
            Logger.getLogger(QuerySparepart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
}
