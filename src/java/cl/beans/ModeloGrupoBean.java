/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.ModeloGrupo;
import org.apache.ibatis.session.SqlSession;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import modelo.ModParametroGeneral;
import org.primefaces.model.UploadedFile;
 


/**
 *
 * @author user1
 */
@ManagedBean(name="ModeloGrupoBean")
@ViewScoped
// @RequestScoped

public class ModeloGrupoBean implements Serializable  {

    private ModeloGrupo formato;
    private List<ModeloGrupo> listFormatos; 
    private ModeloGrupo formatoSelected; 
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
        System.out.println(" INIT ModeloGrupo----------------------------------------------------------------------");
         formato = new ModeloGrupo();
         formatoSelected = new ModeloGrupo();
         this.estadoCRUD = "Leer";
         this.btnLeer = "True";
         this.btnNuevo = "True";
         this.btnCancelar = "True";
         this.btnGuardar = "False";
         this.mensaje = "";
         this.valida = "false";
         // Toma de la BD los listados del parametro de estados.
         ModParametroGeneral formatoModeloGrupo = new ModParametroGeneral();
         estados = formatoModeloGrupo.getListaValores("1");
  
  // parametro.setId("2");
  // formatoModeloGrupo.selectFilter(parametro);
  // formatoModeloGrupo.desplegar();
         
         
     }

    public ModeloGrupoBean() {
    }

    public ModeloGrupoBean(ModeloGrupo formato, List<ModeloGrupo> listFormatos, ModeloGrupo formatoSelected) {
        this.formato = formato;
        this.listFormatos = listFormatos;
        this.formatoSelected = formatoSelected;
    }

    public ModeloGrupo getFormato() {
        return formato;
    }

    public void setFormato(ModeloGrupo formato) {
        this.formato = formato;
    }

    public List<ModeloGrupo> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<ModeloGrupo> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public ModeloGrupo getFormatoSelected() {
        return formatoSelected;
    }

    public void setFormatoSelected(ModeloGrupo formatoSelected) {
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
    
    
    
    public void selectFilter(ModeloGrupo pModeloGrupo) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select Nombre ModeloGrupo  "+ pModeloGrupo.getMgru_nombre() );
        
        if ( session != null ){
            try {
                
/*                if ( pModeloGrupo.getAgendaCode() == null || pFormatoIcbf.getAgendaCode().length() < 1 ){
                    pFormatoIcbf.setAgendaCode("4");
                }
*/                    
                
                this.listFormatos = session.selectList("ModeloGrupo.selectFilter", pModeloGrupo);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Codigo "+ this.listFormatos.get(0).getMgru_secue()+" Nombre "+ this.listFormatos.get(0).getMgru_nombre() );                    
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
            
            ModeloGrupo formatoParametro = new ModeloGrupo();
            
            formatoParametro.setMod_secue(formatoSelected.getMod_secue());
            selectFilter(formatoParametro);
            
        } catch (Exception ex) {
            Logger.getLogger(ModeloGrupoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }

      
     // Actualiza Los datos de la universidad.
    
    public String update()  {
        
        String redireccion = null;
        
        // this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Paso 1 - ModeloGrupo  Ingreso opcion de actualizacion  ");
        System.out.println("Paso 2 - ModeloGrupo  Ingreso opcion de actualizacion "+this.getEstadoCRUD());
        
        System.out.println("ModeloGrupo  "+this.formatoSelected.getMgru_nombre());
        if ( session != null ){
            try {
                
              if ( this.getEstadoCRUD().equals("Nuevo")  )
                {
                    
                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;                    
                    
                   session.insert("ModeloGrupo.insert", this.formatoSelected);
                   System.out.println("Funcion Update - Insert .");
                   this.setMensaje("ModeloGrupo Adicionado");
                   
                }   
                else
                {
                    
                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;
                    
                   session.update("ModeloGrupo.update", this.formatoSelected);     
                   System.out.println("Funcion Update - Actualizar .");
                   this.setMensaje("ModeloGrupo Actualizado");

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
        
        
        ModeloGrupo buscarItem = new ModeloGrupo();
        List<ModeloGrupo> listItems; 

        String redireccion = null;
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Verificar si codigo ModeloGrupo existe.");
        System.out.println("Buscar Codigo "+this.formatoSelected.getMgru_nombre()+" Id Secue  "+this.formatoSelected.getMod_secue());
        
        buscarItem.setMod_secue(this.formatoSelected.getMod_secue());  // Set codigo de busqueda
        
        // this.setEstadoCRUD("Modificar");
        // Codigo del Item 
        // Id secuencial del item
                
        if ( session != null ){
            try {
                
                listItems = session.selectList("ModeloGrupo.selectFilter", buscarItem);
                if ( this.getEstadoCRUD().equals("Modificar") ) {
                   if ( listItems.size() >= 1 ) {
                       
                      for ( int i = 0 ; i < listItems.size() ; i++  ) 
                      {
                           System.out.println(" Codigo Indicador "+ this.listFormatos.get(i).getMod_secue() + " ID "+ this.listFormatos.get(i).getMod_secue() );
                           if ( ! this.formatoSelected.getMod_secue().equals(listItems.get(i).getMod_secue()) )  {  // Si el ID es diferente entonces el codigo es igual genera error
                              this.setMensaje("Modificacion - Codigo ModeloGrupo ya existe. Cambiar el codigo.");
                              return 1; 
                           }
                                       
                      } // Fin del for   
                      return 0;      
                   }                    
                }  // fin del modificar    
                if ( this.getEstadoCRUD().equals("Nuevo") ) {
                   if ( listItems.size() > 0 ) {
                      this.setMensaje("Nuevo - Codigo ModeloGrupo ya existe. Cambiar el codigo.");
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
        System.out.println("Consulta ModeloGrupo");
        System.out.println("Buscar Codigo "+this.formatoSelected.getMod_secue()+" Nombre  "+this.formatoSelected.getMgru_nombre());
       
        
        
        
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("ModeloGrupo.selectFilter", this.formatoSelected);
                if ( this.listFormatos.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listFormatos.size()+ " Nombre Usuario "+ this.listFormatos.get(0).getMgru_nombre()+" Codigo "+ this.listFormatos.get(0).getMod_secue() );                    
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
        this.setValida("true");
        this.setEstadoCRUD("Nuevo");
        this.setBtnCancelar("True");
        this.setBtnGuardar("True");
        this.setBtnLeer("False");
        this.setBtnNuevo("False");
        this.setMensaje("");
       
        System.out.println("Adicionar Registro ModeloGrupo ");


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
            Logger.getLogger(ModeloGrupoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
   
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("ModeloGrupo Perfil - TEST ");
  
  ModeloGrupo formato = new ModeloGrupo();
  
  
  

  ModeloGrupoBean formatoBean = new ModeloGrupoBean();
  
 
  formatoBean.selectFilter(formato);
  
  

}

   
    
}
