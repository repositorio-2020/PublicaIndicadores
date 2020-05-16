/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mybatis.pojos;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author David Lara 
 * @version 20160309
 * @see pojo para el almacenamiento de usuarios
 * 
 */
public class ModeloExe {
    
    private String mexe_secue;
    private String mod_secue;
    private Date mexe_fecha;
    private Time mexe_hora;
    private String mexe_estado;
    private String mexe_descripcion;
    private String mexe_pickle;

    

    public ModeloExe() {
    }

    public String getMexe_secue() {
        return mexe_secue;
    }

    public void setMexe_secue(String mexe_secue) {
        this.mexe_secue = mexe_secue;
    }

    public String getMod_secue() {
        return mod_secue;
    }

    public void setMod_secue(String mod_secue) {
        this.mod_secue = mod_secue;
    }

    public Date getMexe_fecha() {
        return mexe_fecha;
    }

    public void setMexe_fecha(Date mexe_fecha) {
        this.mexe_fecha = mexe_fecha;
    }

    public Time getMexe_hora() {
        return mexe_hora;
    }

    public void setMexe_hora(Time mexe_hora) {
        this.mexe_hora = mexe_hora;
    }

    public String getMexe_estado() {
        return mexe_estado;
    }

    public void setMexe_estado(String mexe_estado) {
        this.mexe_estado = mexe_estado;
    }

    public String getMexe_descripcion() {
        return mexe_descripcion;
    }

    public void setMexe_descripcion(String mexe_descripcion) {
        this.mexe_descripcion = mexe_descripcion;
    }

    public String getMexe_pickle() {
        return mexe_pickle;
    }

    public void setMexe_pickle(String mexe_pickle) {
        this.mexe_pickle = mexe_pickle;
    }

 
    
    
    

   public void limpiar() {
        mexe_secue = "";
        mod_secue = "";
      //  mexe_fecha = "";
      //  mexe_hora = "";
        mexe_estado = "";
        mexe_descripcion = "";
        mexe_pickle = "";
        
        
        
    }       
      
  
    
    
}
