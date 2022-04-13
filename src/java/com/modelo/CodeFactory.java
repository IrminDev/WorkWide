package com.modelo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
/**
 *
 * @author IrminDev
 */
public class CodeFactory {
    private String uniqueId;
    private Timestamp exp;
    private int idUser;
    
    public CodeFactory(Date date, int min){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, min);
        
        this.setExp(new Timestamp(cal.getTimeInMillis()));
        
        this.setUniqueId(UUID.randomUUID().toString());
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Timestamp getExp() {
        return exp;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public void verify(){
        Conexion con = new Conexion();
        
        try{
            con.conectar();
            
            
            con.desconectar();
        }
        catch(Exception e){
            
        }
    }
    
    private void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    private void setExp(Timestamp exp) {
        this.exp = exp;
    }
}
