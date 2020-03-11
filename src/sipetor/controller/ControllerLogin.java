/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipetor.controller;

import sipetor.model.Login;
import sipetor.model.query.QueryLogin;

/**
 *
 * @author User
 */
public class ControllerLogin {
    private QueryLogin queryLogin = new QueryLogin();
    
     public boolean Benar(String username, String password){
        return queryLogin.benar(username, password);
    }
    
    public boolean insertLogin(Login data){
        return queryLogin.insert(data);
    }
    
    public boolean updateLogin(Login data){
        return queryLogin.update(data);
    }
    
    public boolean deleteLogin(String nik){
        return queryLogin.delete(nik);
    }
}
