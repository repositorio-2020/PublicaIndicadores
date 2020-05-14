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
 * @see pojo para el almacenamiento de Programa 
 * 
 */

public class Programa implements Serializable  {

private String id;
private String codigo;
private String codEquivale;

private String nombre;
private String universidadId;
private String universidadNombre;


    public Programa() {
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

    public String getUniversidadId() {
        return universidadId;
    }

    public void setUniversidadId(String universidadId) {
        this.universidadId = universidadId;
    }

    public String getUniversidadNombre() {
        return universidadNombre;
    }

    public void setUniversidadNombre(String universidadNombre) {
        this.universidadNombre = universidadNombre;
    }

    public String getCodEquivale() {
        return codEquivale;
    }

    public void setCodEquivale(String codEquivale) {
        this.codEquivale = codEquivale;
    }

    
    
    
   public void limpiar() {
        this.codigo = null;
        this.id = null;
        this.nombre = null;
        this.universidadId = null;
        this.universidadNombre = null;
        this.codEquivale = null;
    }   
    
    
}
