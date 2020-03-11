/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipetor.controller;

import java.util.List;
import sipetor.model.query.QueryGolongan;
import sipetor.model.Golongan;
;

/**
 *
 * @author User
 */
public class ControllerGolongan {
    private QueryGolongan queryGolongan = new QueryGolongan();
    
    public List<Golongan> getallData(){
        return  queryGolongan.getallGolongan();
    }
 
    public boolean insertGolongan(byte id, String name){
        if(id == 0 && name.isEmpty()){
            return false;
        }else{
            Golongan gol = new Golongan(id,name);
            return queryGolongan.insert(gol);
        }
    }
    
    public boolean updateGolongan(byte id, String name){
        if(id == 0 && name.isEmpty()){
            return false;
        }else{
            Golongan gol = new Golongan(id,name);
            return queryGolongan.update(gol);
        }
    }
    
    public boolean deleteGolongan(byte id){
        return queryGolongan.delete(id);
        
    }
    
    public int GenerateID(){
        return queryGolongan.generateID();
    }

}