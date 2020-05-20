/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mybatis.pojos;

/**
 *
 * @author David Lara 
 * @version 20160309
 * @see pojo para el almacenamiento de Modelo
 * 
 */
public class Modelo {
    
    private String mod_secue;
    private String mod_nombre;
    private String mod_descripcion;
    private String mod_proceso;
    private String mod_estado;
    
    

    public Modelo() {
    }

    public Modelo(String mod_secue, String mod_nombre, String mod_descripcion, String mod_proceso, String mod_estado) {
        this.mod_secue = mod_secue;
        this.mod_nombre = mod_nombre;
        this.mod_descripcion = mod_descripcion;
        this.mod_proceso = mod_proceso;
        this.mod_estado = mod_estado;
    }

    
    
    
    
    public String getMod_secue() {
        return mod_secue;
    }

    public void setMod_secue(String mod_secue) {
        this.mod_secue = mod_secue;
    }

    
    
    
    
    
    public String getMod_nombre() {
        return mod_nombre;
    }

    public void setMod_nombre(String mod_nombre) {
        this.mod_nombre = mod_nombre;
    }

    public String getMod_descripcion() {
        return mod_descripcion;
    }

    public void setMod_descripcion(String mod_descripcion) {
        this.mod_descripcion = mod_descripcion;
    }

    public String getMod_proceso() {
        return mod_proceso;
    }

    public void setMod_proceso(String mod_proceso) {
        this.mod_proceso = mod_proceso;
    }

    public String getMod_estado() {
        return mod_estado;
    }

    public void setMod_estado(String mod_estado) {
        this.mod_estado = mod_estado;
    }


    
    
    
    
   public void limpiar() {
       
        this.mod_secue = "";
        this.mod_nombre = "";  
        this.mod_descripcion = "";
        this.mod_proceso = "";
        this.mod_estado = "";
        
    }       
      
  
    
    
}
