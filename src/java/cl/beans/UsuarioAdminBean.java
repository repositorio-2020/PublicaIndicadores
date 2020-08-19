/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.EstudianteIndicador;
import cl.mybatis.pojos.Usuario;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;
import modelo.ModParametroGeneral;
import modelo.ModPerfil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author user1
 */


@ManagedBean(name="UsuarioAdminBean")
@ViewScoped
// @RequestScoped
public class UsuarioAdminBean implements Serializable {

    private Usuario usuario;
    private List<Usuario> listUsuarios; 
    private Usuario usuarioSelected; 
    private String mensaje;  // Sacar los mensajes
    private String estadoCRUD;  // Estado Ventana Update - Insert
    private String valida;  // Indica si valida True / False
    private Map<String,String> estados = new HashMap<String, String>();
    private Map<String,String> perfiles = new HashMap<String, String>();
    
    private String btnLeer ;  // Indica si el boton se permite visualizar o no.
    private String btnNuevo ;  // Indica si el boton se permite visualizar o no.
    private String btnCancelar ;  // Indica si el boton se permite visualizar o no.
    private String btnGuardar ;  // Indica si el boton se permite visualizar o no.    
      
    @PostConstruct
    public void init() {
        System.out.println(" INIT Usuario AdminBean----------------------------------------------------------------------");
         usuario = new Usuario();
         usuarioSelected = new Usuario();
         
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
         
        ModPerfil perfilModelo = new ModPerfil();
        perfiles = perfilModelo.getListaValores();
         
        
     }
    
    
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioAdminBean() {
        usuario = new Usuario();
        usuarioSelected = new Usuario();
        
    }

    
    
    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
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

    
    
    
    public Map<String, String> getEstados() {
        return estados;
    }

    public void setEstados(Map<String, String> estados) {
        this.estados = estados;
    }

    public Map<String, String> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(Map<String, String> perfiles) {
        this.perfiles = perfiles;
    }

    
    
    
    
    public String guardar(){
        SqlSession session = new myBatisUtil().getSession();
        if ( session != null ){
            try {
                session.insert("Usuario.insert", usuario);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
       // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario Creado"));
        return "index";
    }
    
    public void selectFilter(Usuario pUsuario) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Buscar Usuario "+pUsuario.getCodigo());
        
        if ( session != null ){
            try {
                
                this.listUsuarios = session.selectList("Usuario.selectFilter", pUsuario);
                if ( this.listUsuarios.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listUsuarios.size()+ " Nombre Usuario "+ this.listUsuarios.get(0).getNombre()+" Codigo "+ this.listUsuarios.get(0).getCodigo() );                    
                  this.usuario = this.listUsuarios.get(0);                  
                  this.usuarioSelected = this.listUsuarios.get(0);                  
                }
                
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }
    
    
    
    public String selectFilterSelected()  {
        
        String redireccion = null;
        
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("123----------------------------------------------------------------------");
        System.out.println("Buscar Usuario "+this.usuarioSelected.getCodigo()+" Clave  "+this.usuarioSelected.getClave());
       // this.usuario.setCodigo("codigo");
       
        if ( session != null ){
            try {
                
                this.listUsuarios = session.selectList("Usuario.selectFilter", this.usuarioSelected);
                if ( this.listUsuarios.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listUsuarios.size()+ " Nombre Usuario "+ this.listUsuarios.get(0).getNombre()+" Codigo "+ this.listUsuarios.get(0).getCodigo() );                    
                  this.usuario = this.listUsuarios.get(0);                  
                  this.usuarioSelected = this.listUsuarios.get(0);                  
                  redireccion = null;

                  this.setValida("True");
                  this.setEstadoCRUD("Modificar");
                  this.setBtnCancelar("True");
                  this.setBtnGuardar("True");
                  this.setBtnLeer("False");
                  this.setBtnNuevo("True");

                }
                else
                {
                  this.mensaje = "El usuario no existe en el aplicativo..";   
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

    
// Upadte Procesos

   public String updateAllItem()  {
        
        String redireccion = null;
        
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("updateAllItem----------------------------------------------------------------------");
        System.out.println("Buscar Usuario "+this.usuarioSelected.getCodigo()+" Clave  "+this.usuarioSelected.getClave());
       
        if ( session != null ){
            try {
                session.update("Usuario.update", usuarioSelected);
                session.commit();  
                redireccion = "OK";
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        return redireccion;
    }
    

    // Actualiza el estado del usuario - Activo / Inactivo
    
    public String updateInactivo()  {
        
        String redireccion = null;
        
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Actualizar Estado "+this.usuarioSelected.getNombre() );
        if ( session != null ){
            try {
                
                this.usuarioSelected.setEstado("Inactivo");
                System.out.println("Usuario Inactivo "  );
                session.update("Usuario.update", this.usuarioSelected);
                session.commit();
                this.setMensaje("Usuario Inactivo");
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        return null;
    }

    // Actualiza el estado del usuario - Activo / Inactivo
    
    public String updateActivo()  {
        
        String redireccion = null;
        
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Actualizar Estado "+this.usuarioSelected.getNombre() );
        if ( session != null ){
            try {
                
                this.usuarioSelected.setEstado("Activo");
                System.out.println("Usuario Activo "  );
                session.update("Usuario.update", this.usuarioSelected);
                session.commit();
                this.setMensaje("Usuario Activo");
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        return null;
    }


        
    // Actualiza Los datos del usuario como nombre y perfil
    
    public String update()  {
        
        String redireccion = null;
        
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("UPDATE  ------------------------------------------- ");
        System.out.println("Nombre Usuario "+this.usuarioSelected.getNombre() );
        System.out.println("Nombre Usuario CRUD  "+this.getEstadoCRUD() );
        System.out.println("Nombre Usuario "+this.usuarioSelected.getCodigo() );
        
        
        if ( session != null ){
            try {
                
              if ( this.getEstadoCRUD().equals("Nuevo")  )
                {
                    
                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;                      
                    
                   this.usuarioSelected.setClave("P4$$w0rd2020");
                   session.insert("Usuario.insert", this.usuarioSelected);
                   System.out.println("Funcion Update - Insert .");
                   this.setMensaje("Usuario Adicionado");
                   
                }   

                {
                    
                   int codError;  
                   codError = this.existeCodigo();                   
                   if (codError > 0) return null;
                    
                   session.update("Usuario.update", this.usuarioSelected);     
                   System.out.println("Funcion Update - Actualizar .");
                   this.setMensaje("Usuario Actualizado");

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
        
        
        Usuario buscarItem = new Usuario();
        List<Usuario> listItems; 

        String redireccion = null;
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Verificar si codigo Usuario existe.");
        System.out.println("Buscar Codigo "+this.usuarioSelected.getCodigo());
        
        buscarItem.setCodigo(this.usuarioSelected.getCodigo());  // Set codigo de busqueda
        
        // this.setEstadoCRUD("Modificar");
        // Codigo del Item 
        // Id secuencial del item
                
        if ( session != null ){
            try {
                
                listItems = session.selectList("Usuario.selectFilter", buscarItem);
                System.out.println("Update Usuario ---- "+this.usuarioSelected.getCodigo() );
                System.out.println("Update Usuario ---- "+this.estadoCRUD );
                System.out.println("Update Tamaño de la lista  ---- "+listItems.size() );


                if ( this.getEstadoCRUD().equals("Modificar") ) {
                    return 0;
                }  // fin del modificar    
                if ( this.getEstadoCRUD().equals("Nuevo") ) {
                   if ( listItems.size() > 0 ) {
                      this.setMensaje("Nuevo - Codigo Usuario ya existe. Cambiar el codigo.");
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
    
   
      public void limpiar(){
           System.out.println("Limpiar filtros usuario admin bean .");

          try {
              
              
              this.usuarioSelected.limpiar();
              this.usuario.limpiar();
              this.listUsuarios.clear();
              

              this.setValida("False");
              this.setEstadoCRUD("Leer");
              this.setBtnCancelar("True");
              this.setBtnGuardar("False");
              this.setBtnLeer("True");
              this.setBtnNuevo("True");
              
              

                            
        } catch (Exception ex) {
            Logger.getLogger(UsuarioAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }                 
   
      
    // Adiciona el item deja en blanco el registro para adicionar.
    
    public String adicionar()  {   
        
        this.usuarioSelected.limpiar();       
        this.usuario.limpiar();
        if (!( this.listUsuarios == null)) {
           this.listUsuarios.clear(); 
        }

        System.out.println("Adicionar Usuario ");

        this.setValida("true");
        this.setEstadoCRUD("Nuevo");
        this.setBtnCancelar("True");
        this.setBtnGuardar("True");
        this.setBtnLeer("False");
        this.setBtnNuevo("False");
        this.setMensaje("");
        
        
        return "";
    }
      
      
      
      
      
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  Usuario usuario = new Usuario();
  
  usuario.setCodigo("79465403");
 // usuario.setEstado("A");
//  usuario.setNombre("xyz adicion");
//  usuario.setPerfil("1");

//  UsuarioBean usuarioBean = new UsuarioBean();
//  usuarioBean.setUsuario(usuario);
  
//  usuarioBean.guardar();

// Buscar ususario por codigo y clave
//  UsuarioBean usuarioBean = new UsuarioBean();

//  usuario.setClave("xyzff");
//  usuario.setCodigo("xyz002");

//  usuarioBean.selectFilter(usuario);

// Update Ususario  
  UsuarioAdminBean usuarioBean = new UsuarioAdminBean();
  usuarioBean.selectFilter(usuario);
  System.out.println("Usuario Encontrado 1 "+usuarioBean.getUsuarioSelected().getNombre());
  System.out.println("Usuario Encontrado 2 "+usuarioBean.getUsuario().getNombre());
  
  usuarioBean.getUsuarioSelected().setNombre("Cambio Name");
  
  usuarioBean.usuario.setNombre("CAMBIO ORIGINAL DRLA");
  
 
  
  // usuarioBean.updateAllItem();
  
//  usuarioBean.guardar();
  
  
}

   
    
}
