/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mybatis.pojos;

/**
 *
 * @author David Lara 
 * @version 20160309
 * @see pojo para el almacenamiento de usuarios
 * 
 */
public class ModeloEstudiante {
    
    
    private String mmes_secue;
    private String mexe_secue;
    private String mod_secue;
    private String mod_nombre;
    private String mgru_secue;
    private String mgru_nombre;
    private String est_id_secue;
    private String est_nombre;
    private String mmes_agno;
    private String mmes_nroperiodo;
    private String mmes_nota;
    private String est_codigo;
    
    

    
    
    public ModeloEstudiante() {
    }

    public ModeloEstudiante(String mmes_secue, String mexe_secue, String mod_secue, String mgru_secue, String est_id_secue, String mmes_agno, String mmes_nroperiodo, String mmes_nota) {
        this.mmes_secue = mmes_secue;
        this.mexe_secue = mexe_secue;
        this.mod_secue = mod_secue;
        this.mgru_secue = mgru_secue;
        this.est_id_secue = est_id_secue;
        this.mmes_agno = mmes_agno;
        this.mmes_nroperiodo = mmes_nroperiodo;
        this.mmes_nota = mmes_nota;
    }

    
    
    public String getMmes_secue() {
        return mmes_secue;
    }

    public void setMmes_secue(String mmes_secue) {
        this.mmes_secue = mmes_secue;
    }

    public String getMexe_secue() {
        return mexe_secue;
    }

    public void setMexe_secue(String mexe_secue) {
        this.mexe_secue = mexe_secue;
    }

    public String getMod_secue() {
        return mod_secue;
    }

    public void setMod_secue(String mod_secue) {
        this.mod_secue = mod_secue;
    }

    public String getMgru_secue() {
        return mgru_secue;
    }

    public void setMgru_secue(String mgru_secue) {
        this.mgru_secue = mgru_secue;
    }

    public String getEst_id_secue() {
        return est_id_secue;
    }

    public void setEst_id_secue(String est_id_secue) {
        this.est_id_secue = est_id_secue;
    }

    public String getMmes_agno() {
        return mmes_agno;
    }

    public void setMmes_agno(String mmes_agno) {
        this.mmes_agno = mmes_agno;
    }

    public String getMmes_nroperiodo() {
        return mmes_nroperiodo;
    }

    public void setMmes_nroperiodo(String mmes_nroperiodo) {
        this.mmes_nroperiodo = mmes_nroperiodo;
    }

    public String getMmes_nota() {
        return mmes_nota;
    }

    public void setMmes_nota(String mmes_nota) {
        this.mmes_nota = mmes_nota;
    }

    public String getMod_nombre() {
        return mod_nombre;
    }

    public void setMod_nombre(String mod_nombre) {
        this.mod_nombre = mod_nombre;
    }

    public String getEst_nombre() {
        return est_nombre;
    }

    public void setEst_nombre(String est_nombre) {
        this.est_nombre = est_nombre;
    }

    public String getMgru_nombre() {
        return mgru_nombre;
    }

    public void setMgru_nombre(String mgru_nombre) {
        this.mgru_nombre = mgru_nombre;
    }

    public String getEst_codigo() {
        return est_codigo;
    }

    public void setEst_codigo(String est_codigo) {
        this.est_codigo = est_codigo;
    }

    
    
    
    

   public void limpiar() {
        mmes_secue = "";
        mexe_secue = "";
        mod_secue = "";
        mgru_secue = "";
        est_id_secue = "";
        mmes_agno = "";
        mmes_nroperiodo = "";
        mmes_nota = "";
        this.mod_nombre = "";
        this.est_nombre = ""; 
        this.mgru_nombre = "";
        this.est_codigo = "";
        
        
    }       
      
  
    
    
}
