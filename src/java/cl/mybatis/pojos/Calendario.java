/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mybatis.pojos;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author David Lara 
 * @version 20191030
 * @see pojo para el almacenamiento de Calendario
 * 
 */

public class Calendario implements Serializable  {

private String id;
private String universidadId;
private String universidadNombre;
private String nombre;
private String agno;
private String fchInicial;
private String fchFinal;
private String estado;
private Date fchInicialOut;
private Date fchFinalOut;

private String codigo;
private String descripcion;
private String codEquivale;
private String semana_ini;
private String semana_fin;



    public Calendario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAgno() {
        return agno;
    }

    public void setAgno(String agno) {
        this.agno = agno;
    }

    public String getFchInicial() {
        return fchInicial;
    }

    public void setFchInicial(String fchInicial) {
        this.fchInicial = fchInicial;
    }

    public String getFchFinal() {
        return fchFinal;
    }

    public void setFchFinal(String fchFinal) {
        this.fchFinal = fchFinal;
    }

    public Date getFchInicialOut() {
        return fchInicialOut;
    }

    public void setFchInicialOut(Date fchInicialOut) {
        this.fchInicialOut = fchInicialOut;
    }

    public Date getFchFinalOut() {
        return fchFinalOut;
    }

    public void setFchFinalOut(Date fchFinalOut) {
        this.fchFinalOut = fchFinalOut;
    }

    
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodEquivale() {
        return codEquivale;
    }

    public void setCodEquivale(String codEquivale) {
        this.codEquivale = codEquivale;
    }

    public String getSemana_ini() {
        return semana_ini;
    }

    public void setSemana_ini(String semana_ini) {
        this.semana_ini = semana_ini;
    }

    public String getSemana_fin() {
        return semana_fin;
    }

    public void setSemana_fin(String semana_fin) {
        this.semana_fin = semana_fin;
    }

   
    
    
    
    
    public void limpiar() {
        this.agno = null;
        this.estado = null;
        this.fchFinal = null;
        this.fchInicial = null;
        this.id = null;
        this.nombre = null;
        this.universidadId = null;
        this.universidadNombre = null;
        
        this.codigo = null;
        this.descripcion = null;
        this.codEquivale = null;
        this.semana_ini = null;
        this.semana_fin = null;
        
        this.fchFinalOut = null;
        this.fchInicialOut = null;
        
    }   
    
   
    
    
    
}
