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
 * @version 20191109
 * @see pojo para el almacenamiento de la informacion de consulta de estudiantes indicador.
 * 
 */

public class EstudianteIndicador implements Serializable  {

private String idTransaccion;
private String estudianteNombre;
private String estudianteCodigo;
private String indicadorNombre;
private String indicadorCodigo;
private String nroPeriodo;
private Date fchInicial;
private String valorPeriodo;
private String valorPromedio;
private String valorHistorico;
private String agno;
private String nroPeriodoProg;

    public String getValorPromedio() {
        return valorPromedio;
    }

    public void setValorPromedio(String valorPromedio) {
        this.valorPromedio = valorPromedio;
    }






    public String getAgno() {
        return agno;
    }

    public void setAgno(String agno) {
        this.agno = agno;
    }

    public String getNroPeriodoProg() {
        return nroPeriodoProg;
    }

    public void setNroPeriodoProg(String nroPeriodoProg) {
        this.nroPeriodoProg = nroPeriodoProg;
    }

    public EstudianteIndicador() {
    }

    
    
    
    
    public String getEstudianteNombre() {
        return estudianteNombre;
    }

    public void setEstudianteNombre(String estudianteNombre) {
        this.estudianteNombre = estudianteNombre;
    }

    public String getEstudianteCodigo() {
        return estudianteCodigo;
    }

    public void setEstudianteCodigo(String estudianteCodigo) {
        this.estudianteCodigo = estudianteCodigo;
    }

    public String getIndicadorNombre() {
        return indicadorNombre;
    }

    public void setIndicadorNombre(String indicadorNombre) {
        this.indicadorNombre = indicadorNombre;
    }

    public String getIndicadorCodigo() {
        return indicadorCodigo;
    }

    public void setIndicadorCodigo(String indicadorCodigo) {
        this.indicadorCodigo = indicadorCodigo;
    }

    public String getNroPeriodo() {
        return nroPeriodo;
    }

    public void setNroPeriodo(String nroPeriodo) {
        this.nroPeriodo = nroPeriodo;
    }

    public Date getFchInicial() {
        return fchInicial;
    }

    public void setFchInicial(Date fchInicial) {
        this.fchInicial = fchInicial;
    }

    public String getValorPeriodo() {
        return valorPeriodo;
    }

    public void setValorPeriodo(String valorPeriodo) {
        this.valorPeriodo = valorPeriodo;
    }

    public String getValorHistorico() {
        return valorHistorico;
    }

    public void setValorHistorico(String valorHistorico) {
        this.valorHistorico = valorHistorico;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

   public void limpiar() {
        this.agno = null;
        this.estudianteCodigo = null;
        this.estudianteNombre = null;
        this.fchInicial = null;
        this.idTransaccion = null;
        this.indicadorCodigo = null;
        this.indicadorNombre = null;
        this.nroPeriodo = null;
        this.nroPeriodoProg = null;
        this.valorHistorico = null;
        this.valorPeriodo = null;
        this.valorPromedio = null;
        
        
        
    }       
      

    
    
}
