/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Estudiante;
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
@ManagedBean(name="EstudianteBean")
@ViewScoped
// @RequestScoped

public class EstudianteBean implements Serializable  {

    private Estudiante formato;
    private List<Estudiante> listFormatos; 
    private Estudiante formatoSelected; 
    private UploadedFile file;
    private String mensaje;  // Sacar los mensajes
    private String estadoCRUD;  // Estado Ventana Update - Insert
    private String valida;  // Indica si valida True / False

    private String btnLeer ;  // Indica si el boton se permite visualizar o no.
    private String btnNuevo ;  // Indica si el boton se permite visualizar o no.
    private String btnCancelar ;  // Indica si el boton se permite visualizar o no.
    private String btnGuardar ;  // Indica si el boton se permite visualizar o no.    
    
    
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT Estudiante----------------------------------------------------------------------");
         formato = new Estudiante();
         formatoSelected = new Estudiante();
         this.valida = "false";         
         this.formato.limpiar(); 
         this.formatoSelected.limpiar();

         this.estadoCRUD = "Leer";
         this.btnLeer = "True";
         this.btnNuevo = "True";
         this.btnCancelar = "True";
         this.btnGuardar = "False";
         this.mensaje = "";
         this.valida = "false";
         
         
         
         
     }

    public EstudianteBean() {
    }

    public EstudianteBean(Estudiante formato, List<Estudiante> listFormatos, Estudiante formatoSelected) {
        this.formato = formato;
        this.listFormatos = listFormatos;
        this.formatoSelected = formatoSelected;
    }

    public Estudiante getFormato() {
        return formato;
    }

    public void setFormato(Estudiante formato) {
        this.formato = formato;
    }

    public List<Estudiante> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<Estudiante> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public Estudiante getFormatoSelected() {
        return formatoSelected;
    }

    public void setFormatoSelected(Estudiante formatoSelected) {
        this.formatoSelected = formatoSelected;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEstadoCRUD() {
        return estadoCRUD;
    }

    public void setEstadoCRUD(String estadoCRUD) {
        this.estadoCRUD = estadoCRUD;
    }

    public String getValida() {
        return valida;
    }

    public void setValida(String valida) {
        this.valida = valida;
    }

    public String getBtnLeer() {
        return btnLeer;
    }

    public void setBtnLeer(String btnLeer) {
        this.btnLeer = btnLeer;
    }

    public String getBtnNuevo() {
        return btnNuevo;
    }

    public void setBtnNuevo(String btnNuevo) {
        this.btnNuevo = btnNuevo;
    }

    public String getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(String btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public String getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(String btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

  
    
    
    public void aceptar() {
       System.out.println("Aceptar.......  ");
        
    }
    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }
    
    
    
    public void selectFilter(Estudiante pEstudiante) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select ID codigo de la agenda  "+ pEstudiante.getCodigo() );
        
        if ( session != null ){
            try {
                
/*                if ( pEstudiante.getAgendaCode() == null || pFormatoIcbf.getAgendaCode().length() < 1 ){
                    pFormatoIcbf.setAgendaCode("4");
                }
*/                    
                
                this.listFormatos = session.selectList("Estudiante.selectFilter", pEstudiante);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Codigo "+ this.listFormatos.get(0).getCodigo()+" Nombre "+ this.listFormatos.get(0).getNombre() );                    
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
            
            Estudiante formatoParametro = new Estudiante();
            
            formatoParametro.setId(formatoSelected.getId());
            selectFilter(formatoParametro);
            
        } catch (Exception ex) {
            Logger.getLogger(EstudianteBean.class.getName()).log(Level.SEVERE, null, ex);
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
    
  // Actualiza Los datos de los estudiantes
    
    public String update()  {
        
        String redireccion = null;
        
        // this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Update Estudiantes  "+this.formatoSelected.getCodigo());
        
   
        
        if ( session != null ){
            try {
        
              if ( this.getEstadoCRUD().equals("Nuevo")  )
                {
                   session.insert("Estudiante.insert", this.formatoSelected);
                   System.out.println("Funcion Update - Insert .");
                   this.setMensaje("Estudiante Adicionado");
                   
                }   
                else
                {
                   session.update("Estudiante.update", this.formatoSelected);     
                   System.out.println("Funcion Update - Actualizar .");
                   this.setMensaje("Estudiante Actualizado");

                }    
                
                 this.btnLeer = "True";
                 this.btnNuevo = "True";
                 this.btnCancelar = "True";
                 this.btnGuardar = "False";
                            
                 this.setEstadoCRUD("Leer");
                 this.setValida("false");              
              
                session.commit();
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        return null;
    }
                
 
    // Buscar con filtros ----------
    public String selectFilterSelected()  {

        
       if ( this.formatoSelected == null ) 
       {
         this.formatoSelected = new Estudiante();  
         this.formatoSelected.limpiar();
         System.out.println("---------- selectFilterSelected - Estudiantes NULL");
           
       }    
       System.out.println("selectFilterSelected - Estudiantes ."+this.formatoSelected.getCodigo());

        
        String redireccion = null;
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Consulta Estudiantes");
        System.out.println("Buscar Codigo "+this.formatoSelected.getCodigo()+" Nombre  "+this.formatoSelected.getNombre());
       
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("Estudiante.selectFilter", this.formatoSelected);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Nombre Estudiante "+ this.listFormatos.get(0).getNombre()+" Codigo "+ this.listFormatos.get(0).getCodigo() );                    
                  this.formato = this.listFormatos.get(0);                  
                  this.formatoSelected = this.listFormatos.get(0);                  
                  redireccion = "";
                  this.mensaje = "" + this.listFormatos.size();          

                  this.setValida("True");
//                  this.setEstadoCRUD("Modificar");
                  this.setEstadoCRUD("Consulta");
                  this.setBtnCancelar("True");
                  this.setBtnGuardar("True");
                  this.setBtnLeer("False");
                  this.setBtnNuevo("True");
                  
                  
                  
                }
                else
                {
                  this.mensaje = "El item no fue encontrado. En la consulta";      
                  this.setValida("False");
                  this.setEstadoCRUD("Leer");
                  this.setBtnCancelar("True");
                  this.setBtnGuardar("False");
                  this.setBtnLeer("True");
                  this.setBtnNuevo("True");
                  
                  
                }    
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        return redireccion;
    }


    


// Adiciona el item deja en blanco el registro para adicionar.
    
    public String adicionar()  {
        
        String redireccion = null;
        this.formatoSelected.limpiar();
       
        
        this.setValida("true");
        this.setEstadoCRUD("Nuevo");
        this.setBtnCancelar("True");
        this.setBtnGuardar("True");
        this.setBtnLeer("False");
        this.setBtnNuevo("False");
        this.setMensaje("");
        
        System.out.println("Adicionar Registro Estudiante ");


        return null;
    }
    
    
      public void limpiar(){
           System.out.println("Limpiar filtros Estudiantes ."+this.formatoSelected.getCodigo());
           
          try {
              
              
              this.formatoSelected.limpiar();  
              this.formato.limpiar();
              this.listFormatos.clear();
              
              this.setValida("False");
              this.setEstadoCRUD("Leer");
              this.setBtnCancelar("True");
              this.setBtnGuardar("False");
              this.setBtnLeer("True");
              this.setBtnNuevo("True");
              

                            
        } catch (Exception ex) {
            Logger.getLogger(EstudianteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
      
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  Estudiante formato = new Estudiante();
  
  
  

  EstudianteBean formatoBean = new EstudianteBean();
  
  
  
  formatoBean.selectFilter(formato);
  
  

}

   
    
}
