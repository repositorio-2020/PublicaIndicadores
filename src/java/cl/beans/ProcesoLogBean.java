/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.ProcesoLog;
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

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 


/**
 *
 * @author user1
 */
@ManagedBean(name="ProcesoLogBean")
@ViewScoped
// @RequestScoped

public class ProcesoLogBean implements Serializable  {

    private ProcesoLog formato;
    private List<ProcesoLog> listFormatos; 
    private ProcesoLog formatoSelected; 
    private UploadedFile file;
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT ProcesoLog----------------------------------------------------------------------");
         formato = new ProcesoLog();
         formatoSelected = new ProcesoLog();
        
     }

    public ProcesoLogBean() {
    }

    public ProcesoLogBean(ProcesoLog formato, List<ProcesoLog> listFormatos, ProcesoLog formatoSelected) {
        this.formato = formato;
        this.listFormatos = listFormatos;
        this.formatoSelected = formatoSelected;
    }

    public ProcesoLog getFormato() {
        return formato;
    }

    public void setFormato(ProcesoLog formato) {
        this.formato = formato;
    }

    public List<ProcesoLog> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<ProcesoLog> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public ProcesoLog getFormatoSelected() {
        return formatoSelected;
    }

    public void setFormatoSelected(ProcesoLog formatoSelected) {
        this.formatoSelected = formatoSelected;
    }

  
    
    
    public void aceptar() {
       System.out.println("Aceptar.......  ");
        
    }
    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }
    
    
    
    public void selectFilter(ProcesoLog pProcesoLog) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select nombre del indicador  "+ pProcesoLog.getId() );
        
        if ( session != null ){
            try {
                
/*                if ( pProcesoLog.getAgendaCode() == null || pFormatoIcbf.getAgendaCode().length() < 1 ){
                    pFormatoIcbf.setAgendaCode("4");
                }
*/                    
                
                this.listFormatos = session.selectList("ProcesoLog.selectFilter", pProcesoLog);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Codigo "+ this.listFormatos.get(0).getId()+" Nombre "+ this.listFormatos.get(0).getId() );                    
                  this.formatoSelected = this.listFormatos.get(0);
                }
                
                this.formato = this.listFormatos.get(0);
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }
    
      public void selectAll(){
           System.out.println("Ingreso selet all formato ");

          try {
            System.out.println("Ingreso selet all formato ");
            
            ProcesoLog formatoParametro = new ProcesoLog();
            
            formatoParametro.setId(formatoSelected.getId());
            selectFilter(formatoParametro);
            
        } catch (Exception ex) {
            Logger.getLogger(ProcesoLogBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }

            
      public void limpiar(){
           System.out.println("Limpiar filtros usuario admin bean .");

          try {
              this.formatoSelected.limpiar();
                            
        } catch (Exception ex) {
            Logger.getLogger(ProcesoLogBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }             
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  ProcesoLog formato = new ProcesoLog();
  
  
  

  ProcesoLogBean formatoBean = new ProcesoLogBean();
  
  
  
  formatoBean.selectFilter(formato);
  
  

}

   
    
}
