/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipetor.model.interfaces;

import sipetor.model.Login;

/**
 *
 * @author User
 */
public interface InterfaceLogin {
    public boolean benar(String username, String passsword);
    public boolean insert(Login data);
    public boolean update(Login data);
    public boolean delete(String nik);
}
