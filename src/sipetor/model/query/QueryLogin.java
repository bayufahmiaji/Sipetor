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
import java.util.logging.Level;
import java.util.logging.Logger;
import sipetor.connection.SQLConnection;
import sipetor.model.Login;
import sipetor.model.interfaces.InterfaceLogin;

/**
 *
 * @author User
 */
public class QueryLogin implements InterfaceLogin {
    private Connection conn = SQLConnection.getConnection();
    
    @Override
    public boolean benar(String username, String passsword) {
        String sql = "select karyawan.nama_karyawan, login.username, login.password from login inner join karyawan "
                    + "on login.nik = karyawan.nik" +
                    " where login.username like ? and login.password like ?";
        try{
            if(SQLConnection.getConnection()==null){
                return false;
            }else{
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, passsword);
                
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                   return true;
                }
                statement.close();
            }
     
        } catch (SQLException ex) {
            Logger.getLogger(QueryLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; }

    @Override
    public boolean insert(Login data) {
       String sql = "INSERT INTO login values (?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, data.getNik());
            statement.setString(2, data.getUsername());
            statement.setString(3, data.getPassword());
            int row = statement.executeUpdate();
            if (row > 0) {
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; }

    @Override
    public boolean update(Login data) {
      String sql = "UPDATE login SET password=?"
                + " WHERE nik=? AND username like ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, data.getPassword());
            statement.setString(2, data.getNik());
            statement.setString(3, data.getUsername());
            int row = statement.executeUpdate();
            if (row > 0) {
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;}

    @Override
    public boolean delete(String nik) {
       String sql = "DELETE FROM login WHERE nik=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, nik);
            int row = statement.executeUpdate();
            if (row > 0) {
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;}
    
}
