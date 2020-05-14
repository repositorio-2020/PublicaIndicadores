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
 * @version 20160309
 * @see pojo para el almacenamiento de agenda
 * 
 */

public class Agenda implements Serializable  {

private String id;
private String analista;
private String ea;
private String ua;
private Date fchVisita;
private String estado;
private String direccion;
private String otPadre;
private String departamento;
private String municipio;
private String cedula;
private String analistaCode;
private String analistaMail;


    public Agenda() {
    }


    public Agenda(String id, String analista, String ea, String ua, Date fchVisita, String estado, String direccion, String otPadre) {
        this.id = id;
        this.analista = analista;
        this.ea = ea;
        this.ua = ua;
        this.fchVisita = fchVisita;
        this.estado = estado;
        this.direccion = direccion;
        this.otPadre = otPadre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public String getEa() {
        return ea;
    }

    public void setEa(String ea) {
        this.ea = ea;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public Date getFchVisita() {
        return fchVisita;
    }

    public void setFchVisita(Date fchVisita) {
        this.fchVisita = fchVisita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOtPadre() {
        return otPadre;
    }

    public void setOtPadre(String otPadre) {
        this.otPadre = otPadre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getAnalistaCode() {
        return analistaCode;
    }

    public void setAnalistaCode(String analistaCode) {
        this.analistaCode = analistaCode;
    }

    public String getAnalistaMail() {
        return analistaMail;
    }

    public void setAnalistaMail(String analistaMail) {
        this.analistaMail = analistaMail;
    }

    
    
    
    
}
