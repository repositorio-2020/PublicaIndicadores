/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Indicador;
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
@ManagedBean(name="IndicadorBean")
@ViewScoped
// @RequestScoped

public class IndicadorBean implements Serializable  {

    private Indicador formato;
    private List<Indicador> listFormatos; 
    private Indicador formatoSelected; 
    private UploadedFile file;
    private String mensaje;  // Sacar los mensajes
    private String estadoCRUD;  // Estado Ventana Update - Insert
    private String valida;  // Indica si valida True / False
    private Map<String,String> estados = new HashMap<String, String>();
    private Map<String,String> tipoIndicador = new HashMap<String, String>();
    private Map<String,String> cobertura = new HashMap<String, String>();
    private Map<String,String> tipoPeriodo = new HashMap<String, String>();

    private String btnLeer ;  // Indica si el boton se permite visualizar o no.
    private String btnNuevo ;  // Indica si el boton se permite visualizar o no.
    private String btnCancelar ;  // Indica si el boton se permite visualizar o no.
    private String btnGuardar ;  // Indica si el boton se permite visualizar o no.
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT Indicador----------------------------------------------------------------------");
         formato = new Indicador();
         formatoSelected = new Indicador();

         this.estadoCRUD = "Leer";
         this.btnLeer = "True";
         this.btnNuevo = "True";
         this.btnCancelar = "True";
         this.btnGuardar = "False";
         this.mensaje = "";
         this.valida = "false";

         
         ModParametroGeneral formatoModelo = new ModParametroGeneral();
         estados = formatoModelo.getListaValores("1");
         tipoIndicador = formatoModelo.getListaValores("2");
         cobertura = formatoModelo.getListaValores("3");
         tipoPeriodo = formatoModelo.getListaValores("4");
         
         
        
     }

    public IndicadorBean() {
    }

    public IndicadorBean(Indicador formato, List<Indicador> listFormatos, Indicador formatoSelected) {
        this.formato = formato;
        this.listFormatos = listFormatos;
        this.formatoSelected = formatoSelected;
    }

    public Indicador getFormato() {
        return formato;
    }

    public void setFormato(Indicador formato) {
        this.formato = formato;
    }

    public List<Indicador> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<Indicador> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public Indicador getFormatoSelected() {
        return formatoSelected;
    }

    public void setFormatoSelected(Indicador formatoSelected) {
        this.formatoSelected = formatoSelected;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getValida() {
        return valida;
    }

    public void setValida(String valida) {
        this.valida = valida;
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

    public String getBtnLeer() {
        return btnLeer;
    }

    public void setBtnLeer(String btnLeer) {
        this.btnLeer = btnLeer;
    }

    
    
    
    public Map<String, String> getEstados() {
        return estados;
    }

    public void setEstados(Map<String, String> estados) {
        this.estados = estados;
    }

    public Map<String, String> getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(Map<String, String> tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public Map<String, String> getCobertura() {
        return cobertura;
    }

    public void setCobertura(Map<String, String> cobertura) {
        this.cobertura = cobertura;
    }

    public Map<String, String> getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(Map<String, String> tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

  
    
    
    public void aceptar() {
       System.out.println("Aceptar.......  ");
        
    }
    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }

    public String getEstadoCRUD() {
        return estadoCRUD;
    }

    public void setEstadoCRUD(String estadoCRUD) {
        this.estadoCRUD = estadoCRUD;
    }
    
    
    
    public void selectFilter(Indicador pIndicador) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select nombre del indicador  "+ pIndicador.getNombre() );
        
        if ( session != null ){
            try {
                
/*                if ( pIndicador.getAgendaCode() == null || pFormatoIcbf.getAgendaCode().length() < 1 ){
                    pFormatoIcbf.setAgendaCode("4");
                }
*/                    
                
                this.listFormatos = session.selectList("Indicador.selectFilter", pIndicador);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Codigo "+ this.listFormatos.get(0).getId()+" Nombre "+ this.listFormatos.get(0).getNombre() );                    
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
            
            Indicador formatoParametro = new Indicador();
            
            formatoParametro.setId(formatoSelected.getId());
            selectFilter(formatoParametro);
            
        } catch (Exception ex) {
            Logger.getLogger(IndicadorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }

      
       // Actualiza Los datos de los programas
    
    public String update()  {
        
        String redireccion = null;
        
        // this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Indicador  "+this.formatoSelected.getNombre());
        if ( session != null ){
            try {


              if ( this.getEstadoCRUD().equals("Nuevo")  )
                {

                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;
                    
                    
                    session.insert("Indicador.insert", this.formatoSelected);
                   System.out.println("Funcion Update - Insert .");
                   this.setMensaje("Indicador Adicionado");
                   
                }   
              else
                {

                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;
                    
                    session.update("Indicador.update", this.formatoSelected);     
                   System.out.println("Funcion Update - Actualizar .");
                   this.setMensaje("Indicador Actualizado");

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
        
        
        Indicador buscarItem = new Indicador();
        List<Indicador> listItems; 

        
        String redireccion = null;
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Verificar si codigo Indicador existe.");
        System.out.println("Buscar Codigo "+this.formatoSelected.getCodigo()+" Id Secue  "+this.formatoSelected.getId());
        
        buscarItem.setCodigo(this.formatoSelected.getCodigo());  // Set codigo de busqueda
        
        // this.setEstadoCRUD("Modificar");
        // Codigo del Item 
        // Id secuencial del item
                
        if ( session != null ){
            try {
                
                listItems = session.selectList("Indicador.selectFilter", buscarItem);
                if ( this.getEstadoCRUD().equals("Modificar") ) {
                   if ( listItems.size() >= 1 ) {
                       
                      for ( int i = 0 ; i < listItems.size() ; i++  ) 
                      {
                           System.out.println(" Codigo Indicador "+ this.listFormatos.get(i).getCodigo() + " ID "+ this.listFormatos.get(i).getCodigo() );
                           if ( ! this.formatoSelected.getId().equals(listItems.get(i).getId()) )  {  // Si el ID es diferente entonces el codigo es igual genera error
                              this.setMensaje("Modificacion - Codigo Indicador ya existe. Cambiar el codigo.");
                              return 1; 
                           }
                                       
                      } // Fin del for   
                      return 0;      
                   }                    
                }  // fin del modificar    
                if ( this.getEstadoCRUD().equals("Nuevo") ) {
                   if ( listItems.size() > 0 ) {
                      this.setMensaje("Nuevo - Codigo Indicador ya existe. Cambiar el codigo.");
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
                
                this.listFormatos = session.selectList("Indicador.selectFilter", this.formatoSelected);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Nombre Indicador "+ this.listFormatos.get(0).getNombre()+" Codigo "+ this.listFormatos.get(0).getCodigo() );                    
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
        this.formatoSelected.setEstado("Activo");
        this.formatoSelected.setTipoIndicador("Ingreso");
        this.formatoSelected.setTipoPeriodo("SEMANAL");
        this.formatoSelected.setCobertura("ESTUDIANTE");
        
        this.setValida("true");
        this.setEstadoCRUD("Nuevo");
        this.setBtnCancelar("True");
        this.setBtnGuardar("True");
        this.setBtnLeer("False");
        this.setBtnNuevo("False");
        this.setMensaje("");
        
       
        System.out.println("Adicionar Registro Indicador ");


        return null;
    }

    
    
    
      public void limpiar(){
           System.out.println("Limpiar filtros IndicadorBean .");

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
            Logger.getLogger(IndicadorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }           
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  Indicador formato = new Indicador();
  
  
  

  IndicadorBean formatoBean = new IndicadorBean();
  
  
  
  formatoBean.selectFilter(formato);
  
  

}

   
    
}
