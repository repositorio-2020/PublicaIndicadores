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
 * @see pojo para el almacenamiento de Indicadores
 * 
 */

public class Indicador implements Serializable  {

private String id;
private String nombre;
private String descripcion;
private String tipoPeriodo;
private String estado;
private String cobertura;
private String tipoIndicador;
private String codigo;
private String codEquivale;




    public Indicador() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public String getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(String tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodEquivale() {
        return codEquivale;
    }

    public void setCodEquivale(String codEquivale) {
        this.codEquivale = codEquivale;
    }

    
    
    
 public void limpiar() {
        this.cobertura = null;
        this.descripcion = null;
        this.estado = null;
        this.id = null;
        this.nombre = null;
        this.tipoPeriodo = null;
        this.tipoIndicador = null;
        this.codEquivale = null;
        this.codigo = null;
    }       
    
}
