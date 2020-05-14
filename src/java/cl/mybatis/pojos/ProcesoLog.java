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

public class ProcesoLog implements Serializable  {

private String id;
private String agnoEjecuta;
private String fchEjecuta;
private String horaEjecuta;
private String estado;
private String logsProcesados;
private String tiempoEjecucion;

private String indicadorId;
private String indicadorNombre;



    public ProcesoLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgnoEjecuta() {
        return agnoEjecuta;
    }

    public void setAgnoEjecuta(String agnoEjecuta) {
        this.agnoEjecuta = agnoEjecuta;
    }

    public String getFchEjecuta() {
        return fchEjecuta;
    }

    public void setFchEjecuta(String fchEjecuta) {
        this.fchEjecuta = fchEjecuta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogsProcesados() {
        return logsProcesados;
    }

    public void setLogsProcesados(String logsProcesados) {
        this.logsProcesados = logsProcesados;
    }

    public String getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(String tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public String getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(String indicadorId) {
        this.indicadorId = indicadorId;
    }

    public String getIndicadorNombre() {
        return indicadorNombre;
    }

    public void setIndicadorNombre(String indicadorNombre) {
        this.indicadorNombre = indicadorNombre;
    }

    public String getHoraEjecuta() {
        return horaEjecuta;
    }

    public void setHoraEjecuta(String horaEjecuta) {
        this.horaEjecuta = horaEjecuta;
    }
    
  
 public void limpiar() {
        this.agnoEjecuta = null;
        this.estado = null;
        this.fchEjecuta = null;
        this.horaEjecuta = null;
        this.id = null;
        this.indicadorId = null;
        this.indicadorNombre = null;
        this.logsProcesados = null;
        this.tiempoEjecucion = null;
        
        
        
    }       
      
    
}
