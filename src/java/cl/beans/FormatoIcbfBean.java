/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
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

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 


/**
 *
 * @author user1
 */
@ManagedBean(name="FormatoIcbfBean")
@ViewScoped
// @RequestScoped

public class FormatoIcbfBean implements Serializable  {

    private FormatoIcbf formato;
    private List<FormatoIcbf> listFormatos; 
    private FormatoIcbf formatoSelected; 
    private UploadedFile file;
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT FormatoICBF----------------------------------------------------------------------");
         formato = new FormatoIcbf();
         formatoSelected = new FormatoIcbf();
        
     }

    public FormatoIcbfBean() {
    }

    public FormatoIcbfBean(FormatoIcbf formato, List<FormatoIcbf> listFormatos, FormatoIcbf formatoSelected) {
        this.formato = formato;
        this.listFormatos = listFormatos;
        this.formatoSelected = formatoSelected;
    }

    public FormatoIcbf getFormato() {
        return formato;
    }

    public void setFormato(FormatoIcbf formato) {
        this.formato = formato;
    }

    public List<FormatoIcbf> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<FormatoIcbf> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public FormatoIcbf getFormatoSelected() {
        return formatoSelected;
    }

    public void setFormatoSelected(FormatoIcbf formatoSelected) {
        this.formatoSelected = formatoSelected;
    }

  
    
    
    public void aceptar() {
       System.out.println("Aceptar.......  ");
        
    }
    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }
    
    
    
    public void selectFilter(FormatoIcbf pFormatoIcbf) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select ID codigo de la agenda  "+ pFormatoIcbf.getAgendaCode());
        
        if ( session != null ){
            try {
                
                if ( pFormatoIcbf.getAgendaCode() == null || pFormatoIcbf.getAgendaCode().length() < 1 ){
                    pFormatoIcbf.setAgendaCode("4");
                }
                    
                
                this.listFormatos = session.selectList("FormatoIcbf.selectFilter", pFormatoIcbf);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Nombre 1 "+ this.listFormatos.get(0).getObservacion()+" id secue "+ this.listFormatos.get(0).getCodPregunta() );                    
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
            
            FormatoIcbf formatoParametro = new FormatoIcbf();
            
            formatoParametro.setAgendaCode(formatoSelected.getAgendaCode());
            selectFilter(formatoParametro);
            
        } catch (Exception ex) {
            Logger.getLogger(FormatoIcbfBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }

    // ------------------------------------- file load
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            
        }
    }
      
      
   // ------------------------------------------------------------   
    
    
    /*
    public String guardar(){
        SqlSession session = new myBatisUtil().getSession();
        if ( session != null ){
            try {
                session.insert("Agenda.insert", agenda);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Agenda Creado"));
        return "index";
    }
    
    
    public String actualizar( Agenda pAgenda ){
        SqlSession session = new myBatisUtil().getSession();
        if ( session != null ){
            try {
                session.update("Agenda.update", pAgenda);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Agenda Creado"));
        return "index";
    }
 
       public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((Agenda) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println("---------------------------------------------------------------------- ONrOWeDIT ");

    }
    */ 
    
        
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  FormatoIcbf formato = new FormatoIcbf();
  
  
  

  FormatoIcbfBean formatoBean = new FormatoIcbfBean();
  
  
  
  formatoBean.selectFilter(formato);
  
  

}

   
    
}
