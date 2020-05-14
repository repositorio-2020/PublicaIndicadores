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

public class Transaccion implements Serializable  {

   
   
private String  cuf_sol_secue;
private String  cuf_car_secue;
private String  cuf_sol_padre;
private String  cuf_par_tipopenom; 
private String  cuf_car_mail;
private String  cuf_car_fch_cargue;
private String  cuf_car_hor_cargue;
private String  cuf_car_asunto;
private String  cuf_err_nombre;
private String  cuf_car_name_origen;



    public Transaccion() {


    }

    public String getCuf_sol_secue() {
        return cuf_sol_secue;
    }

    public void setCuf_sol_secue(String cuf_sol_secue) {
        this.cuf_sol_secue = cuf_sol_secue;
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

    public String getCuf_par_tipopenom() {
        return cuf_par_tipopenom;
    }

    public void setCuf_par_tipopenom(String cuf_par_tipopenom) {
        this.cuf_par_tipopenom = cuf_par_tipopenom;
    }

    public String getCuf_car_mail() {
        return cuf_car_mail;
    }

    public void setCuf_car_mail(String cuf_car_mail) {
        this.cuf_car_mail = cuf_car_mail;
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

    public String getCuf_car_asunto() {
        return cuf_car_asunto;
    }

    public void setCuf_car_asunto(String cuf_car_asunto) {
        this.cuf_car_asunto = cuf_car_asunto;
    }

    public String getCuf_err_nombre() {
        return cuf_err_nombre;
    }

    public void setCuf_err_nombre(String cuf_err_nombre) {
        this.cuf_err_nombre = cuf_err_nombre;
    }

    public String getCuf_car_name_origen() {
        return cuf_car_name_origen;
    }

    public void setCuf_car_name_origen(String cuf_car_name_origen) {
        this.cuf_car_name_origen = cuf_car_name_origen;
    }


    
    
    

    
}
