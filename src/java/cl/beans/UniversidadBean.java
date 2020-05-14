/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Universidad;
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
import org.primefaces.model.UploadedFile;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 


/**
 *
 * @author user1
 */
@ManagedBean(name="UniversidadBean")
@ViewScoped
// @RequestScoped

public class UniversidadBean implements Serializable  {

    private Universidad formato;
    private List<Universidad> listFormatos; 
    private Universidad formatoSelected; 
    private UploadedFile file;
    private String mensaje;  // Sacar los mensajes
    private String estadoCRUD;  // Estado Ventana Update - Insert
    private String valida;  // Indica si valida True / False
    
    private String btnLeer ;  // Indica si el boton se permite visualizar o no.
    private String btnNuevo ;  // Indica si el boton se permite visualizar o no.
    private String btnCancelar ;  // Indica si el boton se permite visualizar o no.
    private String btnGuardar ;  // Indica si el boton se permite visualizar o no.
    
    
    private String estado;  
    private Map<String,String> estados = new HashMap<String, String>();


    

    
    @PostConstruct
    public void init() {
        System.out.println(" INIT Universidad----------------------------------------------------------------------");
         formato = new Universidad();
         formatoSelected = new Universidad();
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
  
  // parametro.setId("2");
  // formatoModelo.selectFilter(parametro);
  // formatoModelo.desplegar();
         
         
     }

    public UniversidadBean() {
    }

    public UniversidadBean(Universidad formato, List<Universidad> listFormatos, Universidad formatoSelected) {
        this.formato = formato;
        this.listFormatos = listFormatos;
        this.formatoSelected = formatoSelected;
    }

    public Universidad getFormato() {
        return formato;
    }

    public void setFormato(Universidad formato) {
        this.formato = formato;
    }

    public List<Universidad> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<Universidad> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public Universidad getFormatoSelected() {
        return formatoSelected;
    }

    public void setFormatoSelected(Universidad formatoSelected) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Map<String, String> getEstados() {
        return estados;
    }

    public void setEstados(Map<String, String> estados) {
        this.estados = estados;
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
    
    
    
    public void selectFilter(Universidad pUniversidad) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select ID codigo de la agenda  "+ pUniversidad.getCodigo() );
        
        if ( session != null ){
            try {
                
/*                if ( pUniversidad.getAgendaCode() == null || pFormatoIcbf.getAgendaCode().length() < 1 ){
                    pFormatoIcbf.setAgendaCode("4");
                }
*/                    
                
                this.listFormatos = session.selectList("Universidad.selectFilter", pUniversidad);
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
            
            Universidad formatoParametro = new Universidad();
            
            formatoParametro.setId(formatoSelected.getId());
            selectFilter(formatoParametro);
            
        } catch (Exception ex) {
            Logger.getLogger(UniversidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }

      
     // Actualiza Los datos de la universidad.
    
    public String update()  {
        
        String redireccion = null;
        
        // this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Paso 1 - Universidad  Ingreso opcion de actualizacion  ");
        System.out.println("Paso 2 - Universidad  Ingreso opcion de actualizacion "+this.getEstadoCRUD());
        
        System.out.println("Universidad  "+this.formatoSelected.getNombre());
        if ( session != null ){
            try {
                
              if ( this.getEstadoCRUD().equals("Nuevo")  )
                {
                    
                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;                    
                    
                   session.insert("Universidad.insert", this.formatoSelected);
                   System.out.println("Funcion Update - Insert .");
                   this.setMensaje("Universidad Adicionado");
                   
                }   
                else
                {
                    
                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;
                    
                   session.update("Universidad.update", this.formatoSelected);     
                   System.out.println("Funcion Update - Actualizar .");
                   this.setMensaje("Universidad Actualizado");

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

    
// Buscar si esta repetido el item 
// existeCodigo: Verifica si para la adicion o para la actualizacion el codigo se cambio y se repite con otro de los items.
// El codigo debe ser unico y no se debe repetir.    
// Retorna 
// 0: No encontro items con el mismo codigo
// 1: Si encontro items con el mismo codigo    
    
    
    public int existeCodigo()  {
        
        
        Universidad buscarItem = new Universidad();
        List<Universidad> listItems; 

        String redireccion = null;
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Verificar si codigo Universidad existe.");
        System.out.println("Buscar Codigo "+this.formatoSelected.getCodigo()+" Id Secue  "+this.formatoSelected.getId());
        
        buscarItem.setCodigo(this.formatoSelected.getCodigo());  // Set codigo de busqueda
        
        // this.setEstadoCRUD("Modificar");
        // Codigo del Item 
        // Id secuencial del item
                
        if ( session != null ){
            try {
                
                listItems = session.selectList("Universidad.selectFilter", buscarItem);
                if ( this.getEstadoCRUD().equals("Modificar") ) {
                   if ( listItems.size() >= 1 ) {
                       
                      for ( int i = 0 ; i < listItems.size() ; i++  ) 
                      {
                           System.out.println(" Codigo Indicador "+ this.listFormatos.get(i).getCodigo() + " ID "+ this.listFormatos.get(i).getCodigo() );
                           if ( ! this.formatoSelected.getId().equals(listItems.get(i).getId()) )  {  // Si el ID es diferente entonces el codigo es igual genera error
                              this.setMensaje("Modificacion - Codigo Universidad ya existe. Cambiar el codigo.");
                              return 1; 
                           }
                                       
                      } // Fin del for   
                      return 0;      
                   }                    
                }  // fin del modificar    
                if ( this.getEstadoCRUD().equals("Nuevo") ) {
                   if ( listItems.size() > 0 ) {
                      this.setMensaje("Nuevo - Codigo Universidad ya existe. Cambiar el codigo.");
                      return 1;      
                   }
                } // fin del nuevo     
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        return 0;
    }
    
    
    
// Buscar con filtros ----------
    public String selectFilterSelected()  {
        
        String redireccion = null;
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Consulta Universidad");
        System.out.println("Buscar Codigo "+this.formatoSelected.getCodigo()+" Nombre  "+this.formatoSelected.getNombre());
       
        
        
        
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("Universidad.selectFilter", this.formatoSelected);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Nombre Usuario "+ this.listFormatos.get(0).getNombre()+" Codigo "+ this.listFormatos.get(0).getCodigo() );                    
                  this.formato = this.listFormatos.get(0);                  
                  this.formatoSelected = this.listFormatos.get(0);                  
                  redireccion = "";
                  this.mensaje = "" + this.listFormatos.size();          

                  this.setValida("True");
                  this.setEstadoCRUD("Modificar");
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
                
                this.setMensaje("");

                
                
                
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
        
        String redireccion = null;
        this.formatoSelected.limpiar();
        this.listFormatos.clear();
        this.formato.limpiar();
        this.formatoSelected.setEstado("Activo");
        this.setValida("true");
        this.setEstadoCRUD("Nuevo");
        this.setBtnCancelar("True");
        this.setBtnGuardar("True");
        this.setBtnLeer("False");
        this.setBtnNuevo("False");
        this.setMensaje("");
       
        System.out.println("Adicionar Registro Universidad ");


        return null;
    }


      public void limpiar(){
           System.out.println("Limpiar filtros universidades .version 01 ");
           

          try {
              this.formatoSelected.limpiar();
              this.listFormatos.clear();
              this.formato.limpiar();
              
              this.setValida("False");
              this.setEstadoCRUD("Leer");
              this.setBtnCancelar("True");
              this.setBtnGuardar("False");
              this.setBtnLeer("True");
              this.setBtnNuevo("True");
              
        } catch (Exception ex) {
            Logger.getLogger(UniversidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
   
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  Universidad formato = new Universidad();
  
  
  

  UniversidadBean formatoBean = new UniversidadBean();
  
 
  formatoBean.selectFilter(formato);
  
  

}

   
    
}
