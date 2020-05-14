/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mybatis.pojos;

import java.io.Serializable;

/**
 *
 * @author David Lara 
 * @version 20160309
 * @see pojo para el almacenamiento de agenda
 * 
 */

public class Cargue implements Serializable  {

   
    
    
   
private String  cuf_sol_secue;
private String  cuf_for_secue;   
private String  cuf_for_nombre;
private String  cuf_par_estsol;   
private String  cuf_sol_fchcar;   
private String  cuf_par_estado;   
private String  cuf_car_tamano;   
private String  cuf_car_secue;  
private String  cuf_sol_padre;
private String  usu_cod;
private String  usu_nombre;
private String  tz01_operador_cod;
private String  tz01_funcionario_cod;
private String  tz01_estable_name;
private String  tz01_estable_dir;
private String  tz01_categoria;
private String  tz01_campana;

private String cuf_car_name_origen;
private String cuf_car_name_destino;
private String cuf_par_estcargue;
private String cuf_car_fch_cargue;
private String cuf_car_hor_cargue;
private String cuf_car_fch_iniproc;
private String cuf_car_hor_iniproc;
private String cuf_car_nro_errores;  
private String cuf_car_origen;
private String cuf_car_mail;   
private String cuf_seg_recibo;
private String cuf_par_tipope;   
private String cuf_par_estsolicitud;
private String acc_usu_secue;
private String cuf_err_secue;    
private String cuf_car_comando;
private String estado_flujo;




    public Cargue() {


    }

    public Cargue(String cuf_car_name_origen, String cuf_car_name_destino, String cuf_par_estcargue, String cuf_car_fch_cargue, String cuf_car_hor_cargue, String cuf_car_fch_iniproc, String cuf_car_hor_iniproc, String cuf_car_nro_errores, String cuf_car_origen, String cuf_car_mail, String cuf_seg_recibo, String cuf_par_tipope, String cuf_par_estsolicitud, String acc_usu_secue, String cuf_err_secue, String cuf_car_comando) {
        this.cuf_car_name_origen = cuf_car_name_origen;
        this.cuf_car_name_destino = cuf_car_name_destino;
        this.cuf_par_estcargue = cuf_par_estcargue;
        this.cuf_car_fch_cargue = cuf_car_fch_cargue;
        this.cuf_car_hor_cargue = cuf_car_hor_cargue;
        this.cuf_car_fch_iniproc = cuf_car_fch_iniproc;
        this.cuf_car_hor_iniproc = cuf_car_hor_iniproc;
        this.cuf_car_nro_errores = cuf_car_nro_errores;
        this.cuf_car_origen = cuf_car_origen;
        this.cuf_car_mail = cuf_car_mail;
        this.cuf_seg_recibo = cuf_seg_recibo;
        this.cuf_par_tipope = cuf_par_tipope;
        this.cuf_par_estsolicitud = cuf_par_estsolicitud;
        this.acc_usu_secue = acc_usu_secue;
        this.cuf_err_secue = cuf_err_secue;
        this.cuf_car_comando = cuf_car_comando;
    }

    public String getCuf_car_name_origen() {
        return cuf_car_name_origen;
    }

    public void setCuf_car_name_origen(String cuf_car_name_origen) {
        this.cuf_car_name_origen = cuf_car_name_origen;
    }

    public String getCuf_car_name_destino() {
        return cuf_car_name_destino;
    }

    public void setCuf_car_name_destino(String cuf_car_name_destino) {
        this.cuf_car_name_destino = cuf_car_name_destino;
    }

    public String getCuf_par_estcargue() {
        return cuf_par_estcargue;
    }

    public void setCuf_par_estcargue(String cuf_par_estcargue) {
        this.cuf_par_estcargue = cuf_par_estcargue;
    }

    public String getCuf_car_fch_cargue() {
        return cuf_car_fch_cargue;
    }

    public void setCuf_car_fch_cargue(String cuf_car_fch_cargue) {
        this.cuf_car_fch_cargue = cuf_car_fch_cargue;
    }

    public String getCuf_car_hor_cargue() {
        return cuf_car_hor_cargue;
    }

    public void setCuf_car_hor_cargue(String cuf_car_hor_cargue) {
        this.cuf_car_hor_cargue = cuf_car_hor_cargue;
    }

    public String getCuf_car_fch_iniproc() {
        return cuf_car_fch_iniproc;
    }

    public void setCuf_car_fch_iniproc(String cuf_car_fch_iniproc) {
        this.cuf_car_fch_iniproc = cuf_car_fch_iniproc;
    }

    public String getCuf_car_hor_iniproc() {
        return cuf_car_hor_iniproc;
    }

    public void setCuf_car_hor_iniproc(String cuf_car_hor_iniproc) {
        this.cuf_car_hor_iniproc = cuf_car_hor_iniproc;
    }

    public String getCuf_car_nro_errores() {
        return cuf_car_nro_errores;
    }

    public void setCuf_car_nro_errores(String cuf_car_nro_errores) {
        this.cuf_car_nro_errores = cuf_car_nro_errores;
    }

    public String getCuf_car_origen() {
        return cuf_car_origen;
    }

    public void setCuf_car_origen(String cuf_car_origen) {
        this.cuf_car_origen = cuf_car_origen;
    }

    public String getCuf_car_mail() {
        return cuf_car_mail;
    }

    public void setCuf_car_mail(String cuf_car_mail) {
        this.cuf_car_mail = cuf_car_mail;
    }

    public String getCuf_seg_recibo() {
        return cuf_seg_recibo;
    }

    public void setCuf_seg_recibo(String cuf_seg_recibo) {
        this.cuf_seg_recibo = cuf_seg_recibo;
    }

    public String getCuf_par_tipope() {
        return cuf_par_tipope;
    }

    public void setCuf_par_tipope(String cuf_par_tipope) {
        this.cuf_par_tipope = cuf_par_tipope;
    }

    public String getCuf_par_estsolicitud() {
        return cuf_par_estsolicitud;
    }

    public void setCuf_par_estsolicitud(String cuf_par_estsolicitud) {
        this.cuf_par_estsolicitud = cuf_par_estsolicitud;
    }

    public String getAcc_usu_secue() {
        return acc_usu_secue;
    }

    public void setAcc_usu_secue(String acc_usu_secue) {
        this.acc_usu_secue = acc_usu_secue;
    }

    public String getCuf_err_secue() {
        return cuf_err_secue;
    }

    public void setCuf_err_secue(String cuf_err_secue) {
        this.cuf_err_secue = cuf_err_secue;
    }

    public String getCuf_car_comando() {
        return cuf_car_comando;
    }

    public void setCuf_car_comando(String cuf_car_comando) {
        this.cuf_car_comando = cuf_car_comando;
    }

    public String getCuf_sol_secue() {
        return cuf_sol_secue;
    }

    public void setCuf_sol_secue(String cuf_sol_secue) {
        this.cuf_sol_secue = cuf_sol_secue;
    }

    public String getCuf_for_secue() {
        return cuf_for_secue;
    }

    public void setCuf_for_secue(String cuf_for_secue) {
        this.cuf_for_secue = cuf_for_secue;
    }

    public String getCuf_for_nombre() {
        return cuf_for_nombre;
    }

    public void setCuf_for_nombre(String cuf_for_nombre) {
        this.cuf_for_nombre = cuf_for_nombre;
    }

    public String getCuf_par_estsol() {
        return cuf_par_estsol;
    }

    public void setCuf_par_estsol(String cuf_par_estsol) {
        this.cuf_par_estsol = cuf_par_estsol;
    }

    public String getCuf_sol_fchcar() {
        return cuf_sol_fchcar;
    }

    public void setCuf_sol_fchcar(String cuf_sol_fchcar) {
        this.cuf_sol_fchcar = cuf_sol_fchcar;
    }

    public String getCuf_par_estado() {
        return cuf_par_estado;
    }

    public void setCuf_par_estado(String cuf_par_estado) {
        this.cuf_par_estado = cuf_par_estado;
    }

    public String getCuf_car_tamano() {
        return cuf_car_tamano;
    }

    public void setCuf_car_tamano(String cuf_car_tamano) {
        this.cuf_car_tamano = cuf_car_tamano;
    }

    public String getCuf_car_secue() {
        return cuf_car_secue;
    }

    public void setCuf_car_secue(String cuf_car_secue) {
        this.cuf_car_secue = cuf_car_secue;
    }

    public String getCuf_sol_padre() {
        return cuf_sol_padre;
    }

    public void setCuf_sol_padre(String cuf_sol_padre) {
        this.cuf_sol_padre = cuf_sol_padre;
    }

    public String getUsu_cod() {
        return usu_cod;
    }

    public void setUsu_cod(String usu_cod) {
        this.usu_cod = usu_cod;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getTz01_operador_cod() {
        return tz01_operador_cod;
    }

    public void setTz01_operador_cod(String tz01_operador_cod) {
        this.tz01_operador_cod = tz01_operador_cod;
    }

    public String getTz01_funcionario_cod() {
        return tz01_funcionario_cod;
    }

    public void setTz01_funcionario_cod(String tz01_funcionario_cod) {
        this.tz01_funcionario_cod = tz01_funcionario_cod;
    }

    public String getTz01_estable_name() {
        return tz01_estable_name;
    }

    public void setTz01_estable_name(String tz01_estable_name) {
        this.tz01_estable_name = tz01_estable_name;
    }

    public String getTz01_estable_dir() {
        return tz01_estable_dir;
    }

    public void setTz01_estable_dir(String tz01_estable_dir) {
        this.tz01_estable_dir = tz01_estable_dir;
    }

    public String getTz01_categoria() {
        return tz01_categoria;
    }

    public void setTz01_categoria(String tz01_categoria) {
        this.tz01_categoria = tz01_categoria;
    }

    public String getTz01_campana() {
        return tz01_campana;
    }

    public void setTz01_campana(String tz01_campana) {
        this.tz01_campana = tz01_campana;
    }

    public String getEstado_flujo() {
        return estado_flujo;
    }

    public void setEstado_flujo(String estado_flujo) {
        this.estado_flujo = estado_flujo;
    }

    
    
    
    
    


    
}
