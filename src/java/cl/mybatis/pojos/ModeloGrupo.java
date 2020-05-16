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
 * @see pojo para el almacenamiento de usuarios
 * 
 */
public class ModeloGrupo {
    private String mgru_secue;
    private String mod_secue;
    private String mgru_nombre;
    private String mgru_descripcion;
    private String mgru_estado;
    

    public ModeloGrupo() {
    }

    public ModeloGrupo(String mgru_secue, String mod_secue, String mgru_nombre, String mgru_descripcion, String mgru_estado) {
        this.mgru_secue = mgru_secue;
        this.mod_secue = mod_secue;
        this.mgru_nombre = mgru_nombre;
        this.mgru_descripcion = mgru_descripcion;
        this.mgru_estado = mgru_estado;
    }
    
    

    public String getMgru_secue() {
        return mgru_secue;
    }

    public void setMgru_secue(String mgru_secue) {
        this.mgru_secue = mgru_secue;
    }

    public String getMod_secue() {
        return mod_secue;
    }

    public void setMod_secue(String mod_secue) {
        this.mod_secue = mod_secue;
    }

    public String getMgru_nombre() {
        return mgru_nombre;
    }

    public void setMgru_nombre(String mgru_nombre) {
        this.mgru_nombre = mgru_nombre;
    }

    public String getMgru_descripcion() {
        return mgru_descripcion;
    }

    public void setMgru_descripcion(String mgru_descripcion) {
        this.mgru_descripcion = mgru_descripcion;
    }

    public String getMgru_estado() {
        return mgru_estado;
    }

    public void setMgru_estado(String mgru_estado) {
        this.mgru_estado = mgru_estado;
    }

    
    
    
    
    
    
  
   public void limpiar() {
       
    this.mgru_secue ="";
    this.mod_secue ="";
    this.mgru_nombre ="";
    this.mgru_descripcion ="";
    this.mgru_estado ="";
                       
    }       
      
  
    
    
}
