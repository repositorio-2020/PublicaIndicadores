/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Calendario;
import org.apache.ibatis.session.SqlSession;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import modelo.ModParametroGeneral;
import modelo.ModUniversidad;
import org.primefaces.model.UploadedFile;
 


/**
 *
 * @author user1
 */
@ManagedBean(name="CalendarioBean")
@ViewScoped
// @RequestScoped

public class CalendarioBean implements Serializable  {

    private Calendario formato;
    private List<Calendario> listFormatos; 
    private Calendario formatoSelected; 
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
        System.out.println(" INIT Calendario----------------------------------------------------------------------");
         formato = new Calendario();
         formatoSelected = new Calendario();
  
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

    public CalendarioBean() {
    }

    public CalendarioBean(Calendario formato, List<Calendario> listFormatos, Calendario formatoSelected) {
        this.formato = formato;
        this.listFormatos = listFormatos;
        this.formatoSelected = formatoSelected;
    }

    public Calendario getFormato() {
        return formato;
    }

    public void setFormato(Calendario formato) {
        this.formato = formato;
    }

    public List<Calendario> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<Calendario> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public Calendario getFormatoSelected() {
        return formatoSelected;
    }

    public void setFormatoSelected(Calendario formatoSelected) {
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

    public String getEstadoCRUD() {
        return estadoCRUD;
    }

    public void setEstadoCRUD(String estadoCRUD) {
        this.estadoCRUD = estadoCRUD;
    }

    
    
    
    public void selectFilter(Calendario pCalendario) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select nombre del indicador  "+ pCalendario.getNombre() );
        
        if ( session != null ){
            try {
                
/*                if ( pCalendario.getAgendaCode() == null || pFormatoIcbf.getAgendaCode().length() < 1 ){
                    pFormatoIcbf.setAgendaCode("4");
                }
*/                    
                
                this.listFormatos = session.selectList("Calendario.selectFilter", pCalendario);
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
            
            Calendario formatoParametro = new Calendario();
            
            formatoParametro.setId(formatoSelected.getId());
            selectFilter(formatoParametro);
            
        } catch (Exception ex) {
            Logger.getLogger(CalendarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }

      
       // Actualiza Los datos de los programas
    
    public String update()  {
        
        String redireccion = null;
        
        // this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Calendario  "+this.formatoSelected.getNombre() );
        if ( session != null ){
            try {

              if ( this.getEstadoCRUD().equals("Nuevo")  )
                {

                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;
                    
                   String pattern = "yyyy-MM-dd";
                   SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                   String fchIniStr = simpleDateFormat.format(this.formatoSelected.getFchInicialOut());
                   String fchFinStr = simpleDateFormat.format(this.formatoSelected.getFchFinalOut());
                                       
                   this.formatoSelected.setFchFinal(fchFinStr);
                   this.formatoSelected.setFchInicial(fchIniStr);
                   
                    
                   System.out.println("Funcion  Insert Fch Inicial  ."+this.formatoSelected.getFchInicial());
                   System.out.println("Funcion  Insert Fch Final  ."+this.formatoSelected.getFchFinal());
                   
                   session.insert("Calendario.insert", this.formatoSelected);
                   System.out.println("Funcion Update - Insert .");
                   this.setMensaje("Calendario Adicionado");
                   
                }   
              else
                {
                   System.out.println("----------------- Calendario  "  +  this.formatoSelected.getUniversidadId() ); 
                 
                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;                 
                 
                   String pattern = "yyyy-MM-dd";
                   SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                   String fchIniStr = simpleDateFormat.format(this.formatoSelected.getFchInicialOut());
                   String fchFinStr = simpleDateFormat.format(this.formatoSelected.getFchFinalOut());
                                       
                   this.formatoSelected.setFchFinal(fchFinStr);
                   this.formatoSelected.setFchInicial(fchIniStr);
                   
                    
                   System.out.println("Funcion Update  Fch Inicial  ."+this.formatoSelected.getFchInicial());
                   System.out.println("Funcion Update  Fch Final  ."+this.formatoSelected.getFchFinal());
                 
                 
                 session.update("Calendario.update", this.formatoSelected);     
                 System.out.println("Funcion Update - Actualizar .");
                   this.setMensaje("Calendario Actualizado");

                }    

                 this.btnLeer = "True";
                 this.btnNuevo = "True";
                 this.btnCancelar = "True";
                 this.btnGuardar = "False";                            
                 this.setEstadoCRUD("Leer");
                 this.setValida("false");              
              
                session.commit();
                
                
            } catch (Exception e ) {
                System.out.println("Error crear Calendario ."+e.getMessage());    
                
            } 
            
            
            finally {
                
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
        
        
        Calendario buscarItem = new Calendario();
        List<Calendario> listItems; 

        
        String redireccion = null;
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Verificar si codigo Calendario existe.");
        System.out.println("Buscar Codigo "+this.formatoSelected.getCodigo()+" Id Secue  "+this.formatoSelected.getId());
        
        buscarItem.setCodigo(this.formatoSelected.getCodigo());  // Set codigo de busqueda
        
        // this.setEstadoCRUD("Modificar");
        // Codigo del Item 
        // Id secuencial del item
                
        if ( session != null ){
            try {
                
                listItems = session.selectList("Calendario.selectFilter", buscarItem);
                if ( this.getEstadoCRUD().equals("Modificar") ) {
                   if ( listItems.size() >= 1 ) {
                       
                      for ( int i = 0 ; i < listItems.size() ; i++  ) 
                      {
                           System.out.println(" Codigo Indicador "+ this.listFormatos.get(i).getCodigo() + " ID "+ this.listFormatos.get(i).getCodigo() );
                           if ( ! this.formatoSelected.getId().equals(listItems.get(i).getId()) )  {  // Si el ID es diferente entonces el codigo es igual genera error
                              this.setMensaje("Modificacion - Codigo Calendario ya existe. Cambiar el codigo.");
                              return 1; 
                           }
                                       
                      } // Fin del for   
                      return 0;      
                   }                    
                }  // fin del modificar    
                if ( this.getEstadoCRUD().equals("Nuevo") ) {
                   if ( listItems.size() > 0 ) {
                      this.setMensaje("Nuevo - Codigo Programa ya existe. Cambiar el codigo.");
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
        
         SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
         Date fecha1 = null;
         Date fecha2 = null;
         
        String redireccion = null;
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Consulta Calendario");
        System.out.println("Buscar Codigo "+this.formatoSelected.getCodigo()+" Nombre  "+this.formatoSelected.getNombre());
       
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("Calendario.selectFilter", this.formatoSelected);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Nombre Usuario "+ this.listFormatos.get(0).getNombre()+" Codigo "+ this.listFormatos.get(0).getCodigo() );                    
                  this.formato = this.listFormatos.get(0);                  
                  this.formatoSelected = this.listFormatos.get(0);  
                    try {
                        fecha1 = formato.parse(this.formato.getFchInicial());
                        fecha2 = formato.parse(this.formato.getFchFinal());
                    } catch (ParseException ex) {
                        Logger.getLogger(CalendarioBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  this.formato.setFchInicialOut(fecha1); 
                  this.formato.setFchFinalOut(fecha2); 
                  
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
        this.listFormatos.clear();
        this.formato.limpiar();       

        this.formatoSelected.setEstado("Activo");        
        this.formatoSelected.setUniversidadId("1");


        
        this.setValida("true");
        this.setEstadoCRUD("Nuevo");
        this.setBtnCancelar("True");
        this.setBtnGuardar("True");
        this.setBtnLeer("False");
        this.setBtnNuevo("False");
        this.setMensaje("");


        
        System.out.println("Adicionar Registro Calendario ");


        return null;
    }
    
    
      public void limpiar(){
           System.out.println("Limpiar filtros Calendario.");

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
            Logger.getLogger(CalendarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }           
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  Calendario formato = new Calendario();
  
  
  

  CalendarioBean formatoBean = new CalendarioBean();
  
  
  
  formatoBean.selectFilter(formato);
  
  

}

   
    
}
