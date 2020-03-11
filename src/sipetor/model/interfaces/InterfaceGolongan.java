/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipetor.model.interfaces;

import java.util.List;
import sipetor.model.Golongan;

/**
 *
 * @author User
 */
public interface InterfaceGolongan {
    public boolean insert(Golongan data);
    public boolean update(Golongan data);
    public boolean delete(byte id_golongan);
    public int generateID();
    
    public List<Golongan> getallGolongan();
    
    public Golongan getOneGolonganByID(byte id_golongan);
    
    public List<Golongan> getAllGolonganByName(String nama_golongan);
}
