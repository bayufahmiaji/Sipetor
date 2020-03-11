/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipetor.controller;

import java.util.List;
import sipetor.model.Sparepart;
import sipetor.model.query.QuerySparepart;

/**
 *
 * @author User
 */
public class ControllerSparepart {
    private QuerySparepart querySparepart = new QuerySparepart();
    
    public boolean InsertSparepart(Sparepart data){
       return querySparepart.insert(data);
    }
    public boolean UpdateSparepart(Sparepart data){
       return querySparepart.update(data);
    }
    public boolean DeletetSparepart(int id_sparepart){
       return querySparepart.delete(id_sparepart);
    }
     public List<Sparepart> getallData(){
        return querySparepart.getAllSparepart();
    }
     public int GenerateID(){
        return querySparepart.generateID();
    }
     public List<Sparepart> getAllSparepartByName(String nama_Saparepart){
        return querySparepart.getAllSparepartByName(nama_Saparepart);
    }
    
}
