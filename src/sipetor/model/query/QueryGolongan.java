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
import sipetor.model.Golongan;
import sipetor.model.interfaces.InterfaceGolongan;

/**
 *
 * @author User
 */
public class QueryGolongan implements InterfaceGolongan{
    private Connection conn = SQLConnection.getConnection();

    @Override
    public boolean insert(Golongan g) {
        String sql = "Insert Into golongan values (?,?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setByte(1, g.getId_golongan());
            statement.setString(2, g.getNama_golongan());
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryGolongan.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }

    @Override
    public boolean update(Golongan g) {
        String sql = "UPDATE golongan SET nama_golongan=? WHERE id_golongan=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, g.getNama_golongan());
            statement.setByte(2, g.getId_golongan());
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryGolongan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(byte id_golongan) {
        String sql = "DELETE FROM golongan where id_golongan=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setByte(1, id_golongan);
            int row = statement.executeUpdate();
            if(row>0){
                return true;
            }
            statement.close();
            
        }catch (SQLException ex) {
            Logger.getLogger(QueryGolongan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    
    }

    @Override
    public int generateID() {
        int id_golongan = 0;
        String sql = "EXEC genIDGolongan";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                id_golongan = rs.getInt(1);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(QueryGolongan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_golongan;
    }

    @Override
    public Golongan getOneGolonganByID(byte id_golongan) {
        Golongan output = null;
        String sql = "Select * from golongan where id_golongan=?";
        try{
            if(SQLConnection.getConnection()==null){
                return null;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setByte(1, id_golongan);
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    output = new Golongan(rs.getByte(1), rs.getString(2));
                }
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryGolongan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }

    @Override
    public List<Golongan> getAllGolonganByName(String nama_golongan) {
        List<Golongan> listGolongan = new ArrayList<>();
        String sql = "Select * from golongan WHERE nama_golongan like %?%";
        try{
           if(SQLConnection.getConnection()==null){
               return null;
           } else{
               PreparedStatement statement = conn.prepareStatement(sql);
               statement.setString(1, nama_golongan);
               
               ResultSet rs = statement.executeQuery();
               while(rs.next()){
                   Golongan g = new Golongan(rs.getByte(1), rs.getString(2));
                   listGolongan.add(g);
               }
               statement.close();
           }
        } catch (SQLException ex) {
            Logger.getLogger(QueryGolongan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGolongan;
    }

    @Override
    public List<Golongan> getallGolongan() {
    List<Golongan> listGolongan = new ArrayList<>();
        String sql = "Select * from golongan";
        try{
            if(SQLConnection.getConnection()==null){
                return null;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    Golongan g = new Golongan(
                            rs.getByte(1),
                            rs.getString(2)); 
                listGolongan.add(g);
                }
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryGolongan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGolongan;
    }
}
