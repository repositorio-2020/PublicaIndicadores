/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;    
import cl.mybatis.pojos.Cargue;
import cl.mybatis.pojos.FormatoIcbf;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.apache.ibatis.session.SqlSession;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.UploadedFile;

 


/**
 *
 * @author user1
 */
@ManagedBean(name="CargueBean")
@ViewScoped
// @RequestScoped

public class CargueBean implements Serializable  {

    private Cargue cargue;
    private List<Cargue> listCargues; 
    private Cargue cargueSelected; 

    
    public CargueBean(Cargue cargue, List<Cargue> listCargues, Cargue cargueSelected ) {
        this.cargue = cargue;
        this.listCargues = listCargues;
        this.cargueSelected = cargueSelected;
    }
    
    public CargueBean( ) {
        this.cargue = null;
        this.listCargues = null;
        this.cargueSelected = null;
    }
    
    
    public Cargue getCargue() {
        return cargue;
    }

    public void setCargue(Cargue cargue) {
        this.cargue = cargue;
    }

    public List<Cargue> getListCargues() {
        return listCargues;
    }

    public void setListCargues(List<Cargue> listCargues) {
        this.listCargues = listCargues;
    }

    public Cargue getCargueSelected() {
        return cargueSelected;
    }

    public void setCargueSelected(Cargue cargueSelected) {
        this.cargueSelected = cargueSelected;
    }
    
    
    public void aceptar() {
       System.out.println("Aceptar.......  ");
        
    }
    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }
    

    public void selectFilter(Cargue pCargue) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select ID");
        
        if ( session != null ){
            try {
                
                listCargues = session.selectList("Cargue.selectFilter", pCargue);
                if ( listCargues.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+listCargues.size());
                }
                
                this.cargue = listCargues.get(0);
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }
    
    
    

    public void selectAll(){
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Ingreso selet all agenda");
        
        if ( session != null ){
            try {
                
                this.listCargues = session.selectList("Cargue.selectAll");  
                  System.out.println(" numerod e registros de cargue "+this.listCargues.size());
//                System.out.println("Ver 02 - Tamaño de la lista "+listAgendas.size()+ " Nombre 1 "+ listAgendas.get(1).getUa() );
                
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }
      
      
      
    public String insertar(Cargue pCargue){
        SqlSession session = new myBatisUtil().getSession();
        if ( session != null ){
            try {
                session.insert("Cargue.insert", pCargue);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion. CARGUE ");
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Cargue Creado"));
        return "Cargue";
    }

    public String generaFormato( String pCodigo ){
        
        
    Cargue cargueInsert = new Cargue();

     cargueInsert.setCuf_car_name_destino("Agenda WEB");
     cargueInsert.setCuf_car_name_origen("xxxx yyy zzz");
     cargueInsert.setCuf_car_comando("*IFGENE* ICBFD ID_"+pCodigo);
     cargueInsert.setAcc_usu_secue("204");
     cargueInsert.setCuf_par_estcargue("820");
     cargueInsert.setCuf_par_tipope("804");
     cargueInsert.setCuf_car_mail("david.lara.amaya@gmail.com");
     cargueInsert.setCuf_car_origen("MAIL");
     cargueInsert.setCuf_err_secue("0");
     cargueInsert.setCuf_seg_recibo("998877");
     cargueInsert.setCuf_par_estsolicitud("0");
  
     this.insertar(cargueInsert);

     return "";
     
    }


    public String generaFormato( String pCodigo, String pMail, String pUsuario ){
        
        
    Cargue cargueInsert = new Cargue();

     cargueInsert.setCuf_car_name_destino("Agenda WEB");
     cargueInsert.setCuf_car_name_origen("xxxx yyy zzz");
     cargueInsert.setCuf_car_comando("*IFGENE* ICBFD ID_"+pCodigo);
     cargueInsert.setAcc_usu_secue(pUsuario);
     cargueInsert.setCuf_par_estcargue("820");
     cargueInsert.setCuf_par_tipope("804");
     cargueInsert.setCuf_car_mail(pMail);
     cargueInsert.setCuf_car_origen("MAIL");
     cargueInsert.setCuf_err_secue("0");
     cargueInsert.setCuf_seg_recibo("998877");
     cargueInsert.setCuf_par_estsolicitud("0");
  
     this.insertar(cargueInsert);

     return "";
     
    }




      
   // ------------------------------------------------------------   
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST 11111.....v3 ");
  
  Cargue cargue = new Cargue();
  
  
  CargueBean cargueBean = new CargueBean();
  
  cargueBean.selectFilter(cargue);
  
  
//  cargue.setAcc_usu_secue("204");
  
  //cargueBean.selectFilter(cargue);
/*  
  
  cargueBean.selectAll();
  
  System.out.println("------------------------- SELECT FILTER ----------------------------");
  cargueBean.selectFilter(cargue);
 */


/*
  cargue.setCuf_car_name_destino("Agenda WEB");
  cargue.setCuf_car_name_origen("xxxx yyy zzz");
  cargue.setCuf_car_comando("*IFGENE* ICBFD ID_6810");
  cargue.setAcc_usu_secue("204");
  cargue.setCuf_par_estcargue("820");
  cargue.setCuf_par_tipope("804");
  cargue.setCuf_car_mail("david.lara.amaya@gmail.com");
  cargue.setCuf_car_origen("MAIL");
  cargue.setCuf_err_secue("0");
  cargue.setCuf_seg_recibo("998877");
  
  cargueBean.insertar(cargue);
  */

}

   
    
}
