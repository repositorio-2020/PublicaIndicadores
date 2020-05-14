/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mybatis.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author David Lara 
 * @version 20191029
 * @see pojo para el almacenamiento de Universidades
 * 
 */

public class Universidad implements Serializable  {

private String id;
private String codigo;
private String nombre;
private String estado;
private String codEquivale;



    public Universidad() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodEquivale() {
        return codEquivale;
    }

    public void setCodEquivale(String codEquivale) {
        this.codEquivale = codEquivale;
    }

    
    
     public void limpiar() {
        this.codigo = "";
        this.codEquivale = "";
        this.estado = "";
        this.id = "";
        this.nombre = "";
       
    }
    
    
}
