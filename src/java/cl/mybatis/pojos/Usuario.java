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
public class Usuario {
    private String codigo;
    private String nombre;
    private String perfil;
    private String estado;
    private String clave;
    private String namePerfil;
    private String clave1;
    private String clave2;
    

    public Usuario() {
    }

    public Usuario(String codigo, String nombre, String perfil, String estado, String clave) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.perfil = perfil;
        this.estado = estado;
        this.clave = clave;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNamePerfil() {
        return namePerfil;
    }

    public void setNamePerfil(String namePerfil) {
        this.namePerfil = namePerfil;
    }

    public String getClave1() {
        return clave1;
    }

    public void setClave1(String clave1) {
        this.clave1 = clave1;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

   public void limpiar() {
        this.clave = "";  // P4$$w0rd2020
        this.clave1 = "";
        this.clave2 = "";
        this.codigo = "";
        this.estado = "";
        this.namePerfil = "";
        this.nombre = "";
        this.perfil = "";
        
        
        
    }       
      
  
    
    
}
