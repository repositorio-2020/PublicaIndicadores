/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mybatis.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author David Lara 
 * @version 20160315
 * @see pojo para el almacenamiento de agenda
 * 
 */

public class FormatoIcbf implements Serializable  {






    private String easNombre;
    private String easCodigo;
    private String easDir;
    private String easTel;
    private String easRepLeg;
    private String easNit;
    private String easModContrata;
    private String easCupAtendido;
    private String pregunta;
    private String observacion;
    private String codPregunta;
    private String ordenTrabajo;
    private Date fchCargue;
    private String foto;
    private String zipcode;
    private String ubicacion;
    private String nameFormato;
    private String mail;
    private String tamano;
    private String analista;
    private String cedula;
    private String dir;
    private String depto;
    private String municipio;
    private String agendaCode;
    
    
    
    

    public FormatoIcbf() {
    }

    public FormatoIcbf(String easNombre, String easCodigo, String easDir, String easTel, String easRepLeg, String easNit, String easModContrata, String easCupAtendido, String pregunta, String observacion, String codPregunta, String ordenTrabajo, Date fchCargue) {
        this.easNombre = easNombre;
        this.easCodigo = easCodigo;
        this.easDir = easDir;
        this.easTel = easTel;
        this.easRepLeg = easRepLeg;
        this.easNit = easNit;
        this.easModContrata = easModContrata;
        this.easCupAtendido = easCupAtendido;
        this.pregunta = pregunta;
        this.observacion = observacion;
        this.codPregunta = codPregunta;
        this.ordenTrabajo = ordenTrabajo;
        this.fchCargue = fchCargue;
    }

    public String getEasNombre() {
        return easNombre;
    }

    public void setEasNombre(String easNombre) {
        this.easNombre = easNombre;
    }

    public String getEasCodigo() {
        return easCodigo;
    }

    public void setEasCodigo(String easCodigo) {
        this.easCodigo = easCodigo;
    }

    public String getEasDir() {
        return easDir;
    }

    public void setEasDir(String easDir) {
        this.easDir = easDir;
    }

    public String getEasTel() {
        return easTel;
    }

    public void setEasTel(String easTel) {
        this.easTel = easTel;
    }

    public String getEasRepLeg() {
        return easRepLeg;
    }

    public void setEasRepLeg(String easRepLeg) {
        this.easRepLeg = easRepLeg;
    }

    public String getEasNit() {
        return easNit;
    }

    public void setEasNit(String easNit) {
        this.easNit = easNit;
    }

    public String getEasModContrata() {
        return easModContrata;
    }

    public void setEasModContrata(String easModContrata) {
        this.easModContrata = easModContrata;
    }

    public String getEasCupAtendido() {
        return easCupAtendido;
    }

    public void setEasCupAtendido(String easCupAtendido) {
        this.easCupAtendido = easCupAtendido;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCodPregunta() {
        return codPregunta;
    }

    public void setCodPregunta(String codPregunta) {
        this.codPregunta = codPregunta;
    }

    public String getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(String ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public Date getFchCargue() {
        return fchCargue;
    }

    public void setFchCargue(Date fchCargue) {
        this.fchCargue = fchCargue;
    }

    public String getFoto() {
        Random rnd = new Random();
        int nro = 0;
                
        nro = rnd.nextInt(20);        
        foto = "demo"+ nro +".jpg";
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNameFormato() {
        return nameFormato;
    }

    public void setNameFormato(String nameFormato) {
        this.nameFormato = nameFormato;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getAgendaCode() {
        return agendaCode;
    }

    public void setAgendaCode(String agendaCode) {
        this.agendaCode = agendaCode;
    }
    
    
    
    
    
    
}
