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
 * @see pojo para el almacenamiento de Estudiantes
 * 
 */

public class Curso implements Serializable  {

private String id;
private String nombre;
private String descripcion;
private String estado;
private String codigo;
private String codEquiva;
private String universidadId;
private String universidadNombre;


    public Curso() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodEquiva() {
        return codEquiva;
    }

    public void setCodEquiva(String codEquiva) {
        this.codEquiva = codEquiva;
    }

    
    
    
    public void limpiar() {
        this.descripcion = null;
        this.estado = null;
        this.id = null;
        this.nombre = null;
        this.universidadId = null;
        this.universidadNombre = null;
        this.codigo = null;
        this.codEquiva = null;
    }
     
    
}
