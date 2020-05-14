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
 * @see pojo para el almacenamiento de Perfiles
 * 
 */
public class Perfil {
    private String perfilCodigo;
    private String perfilNombre;

    public Perfil() {
    }

    public String getPerfilCodigo() {
        return perfilCodigo;
    }

    public void setPerfilCodigo(String perfilCodigo) {
        this.perfilCodigo = perfilCodigo;
    }

    public String getPerfilNombre() {
        return perfilNombre;
    }

    public void setPerfilNombre(String perfilNombre) {
        this.perfilNombre = perfilNombre;
    }

    public void limpiar() {
        this.perfilCodigo = null;
        this.perfilNombre = null;
        
    }       
      
   
    
    
}
