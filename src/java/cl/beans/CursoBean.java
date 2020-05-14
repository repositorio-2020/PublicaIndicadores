/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Curso;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.apache.ibatis.session.SqlSession;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import modelo.ModParametroGeneral;
import modelo.ModUniversidad;
import org.primefaces.model.UploadedFile;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 


/**
 *
 * @author user1
 */
@ManagedBean(name="CursoBean")
@ViewScoped
// @RequestScoped

public class CursoBean implements Serializable  {

    private Curso formato;
    private List<Curso> listFormatos; 
    private Curso formatoSelected; 
    private UploadedFile file;
    private String mensaje;  // Sacar los mensajes
    private String estadoCRUD;  // Estado Ventana Update - Insert
    private String valida;  // Indica si valida True / False
    private Map<String,String> estados = new HashMap<String, String>();
    private Map<String,String> universidades = new HashMap<String, String>();
   
  private String btnLeer ;  // Indica si el boton se permite visualizar o no.
    private String btnNuevo ;  // Indica si el boton se permite visualizar o no.
    private String btnCancelar ;  // Indica si el boton se permite visualizar o no.
    private String btnGuardar ;  // Indica si el boton se permite visualizar o no.    
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT Curso----------------------------------------------------------------------");
         formato = new Curso();
         formatoSelected = new Curso();
         
         this.estadoCRUD = "Leer";
         this.btnLeer = "True";
         this.btnNuevo = "True";
         this.btnCancelar = "True";
         this.btnGuardar = "False";
         this.mensaje = "";
         this.valida = "false";
         
         
         // Toma de la BD los listados del parametro de estados.
         ModParametroGeneral formatoModelo = new ModParametroGeneral();
         estados = formatoModelo.getListaValores("1");
         
         ModUniversidad universidadModelo = new ModUniversidad();
         universidades = universidadModelo.getListaValores();

         
         
     }

    public CursoBean() {
    }

    public CursoBean(Curso formato, List<Curso> listFormatos, Curso formatoSelected) {
        this.formato = formato;
        this.listFormatos = listFormatos;
        this.formatoSelected = formatoSelected;
    }

    public Curso getFormato() {
        return formato;
    }

    public void setFormato(Curso formato) {
        this.formato = formato;
    }

    public List<Curso> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<Curso> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public Curso getFormatoSelected() {
        return formatoSelected;
    }

    public void setFormatoSelected(Curso formatoSelected) {
        this.formatoSelected = formatoSelected;
    }

  
    public void query() {
       System.out.println("Aceptar.......  ");
       this.formatoSelected.setDescripcion("");
       this.formatoSelected.setNombre("");
        
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

    
    
    
    public Map<String, String> getEstados() {
        return estados;
    }

    public void setEstados(Map<String, String> estados) {
        this.estados = estados;
    }

    public Map<String, String> getUniversidades() {
        return universidades;
    }

    public void setUniversidades(Map<String, String> universidades) {
        this.universidades = universidades;
    }
    


    
    public void aceptar() {
       System.out.println("Aceptar.......  ");
        
    }



    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
    public void selectFilter(Curso pCurso) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select ID codigo de la agenda  "+ pCurso.getNombre() );
        
        if ( session != null ){
            try {
                
                    
                
                this.listFormatos = session.selectList("Curso.selectFilter", pCurso);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Codigo "+ this.listFormatos.get(0).getDescripcion()+" Nombre "+ this.listFormatos.get(0).getNombre() );                    
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
            
            Curso formatoParametro = new Curso();
            
            formatoParametro.setId(formatoSelected.getId());
            selectFilter(formatoParametro);
            
        } catch (Exception ex) {
            Logger.getLogger(CursoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }

     // Actualiza Los datos de los programas
    
    public String update()  {
        
        String redireccion = null;
        
        // this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Curso  "+this.formatoSelected.getNombre());
        if ( session != null ){
            try {
                
              if ( this.getEstadoCRUD().equals("Nuevo")  )
                {
                   session.insert("Curso.insert", this.formatoSelected);
                   System.out.println("--------------------------------------------Funcion Update - Insert .");
                   this.setMensaje("Curso Adicionado");
                   
                }   
                else
                {
                   session.update("Curso.update", this.formatoSelected);     
                   System.out.println("Funcion Update - Actualizar .");
                   this.setMensaje("Curso Actualizado");

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
        
        String redireccion = null;
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Consulta Curso");
        System.out.println("Buscar Codigo "+this.formatoSelected.getCodigo()+" Nombre  "+this.formatoSelected.getNombre());
       
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("Curso.selectFilter", this.formatoSelected);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Nombre Usuario "+ this.listFormatos.get(0).getNombre()+" Codigo "+ this.listFormatos.get(0).getCodigo() );                    
                  this.formato = this.listFormatos.get(0);                  
                  this.formatoSelected = this.listFormatos.get(0);                  
                  redireccion = "";
                  this.mensaje = "" + this.listFormatos.size();          

                  this.setValida("True");
                  this.setEstadoCRUD("Consulta");
//                  this.setEstadoCRUD("Modificar");
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


        // -- Dispone de las variables para realizar la adicion
        public String adicionar()  {
        
        this.formatoSelected.limpiar();
        this.formatoSelected.setCodEquiva("0000");
        this.formatoSelected.setEstado("Activo");                   
        
        this.setValida("true");
        this.setEstadoCRUD("Nuevo");
        this.setBtnCancelar("True");
        this.setBtnGuardar("True");
        this.setBtnLeer("False");
        this.setBtnNuevo("False");
        this.setMensaje("");
        
       
        System.out.println("Adicionar Registro Curso ");


        return null;
    }

                
      public void limpiar(){
           System.out.println("Limpiar filtros Curso.");

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
            Logger.getLogger(EstudianteIndicadorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }           
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  Curso formato = new Curso();
  
  
  

  CursoBean formatoBean = new CursoBean();
  
  
  
  formatoBean.selectFilter(formato);
  
  

}

   
    
}
