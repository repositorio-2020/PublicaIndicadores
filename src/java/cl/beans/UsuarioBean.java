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
import java.util.List;
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
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author user1
 */


@ManagedBean(name="UsuarioBean")
@SessionScoped
// @RequestScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario;
    private List<Usuario> listUsuarios; 
    private Usuario usuarioSelected; 
    private String mensaje;  // Sacar los mensajes
    private String estadoCRUD;  // Sacar los mensajes
    private String valida;  // Indica si valida True / False
    
    
  
      
    @PostConstruct
    public void init() {
        System.out.println(" INIT Usuario Bean----------------------------------------------------------------------");
         usuario = new Usuario();
         usuarioSelected = new Usuario();
         this.estadoCRUD = "";
         this.mensaje = "";
         this.valida = "true";
         
        
     }    
    
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
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
                }
                
                this.usuario = this.listUsuarios.get(0);
                
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
                this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("123----------------------------------------------------------------------");
        System.out.println("Buscar Usuario "+this.usuarioSelected.getCodigo()+" Clave  "+this.usuarioSelected.getClave());
       
        if ( session != null ){
            try {
                
                this.listUsuarios = session.selectList("Usuario.selectFilter", this.usuarioSelected);
                if ( this.listUsuarios.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listUsuarios.size()+ " Nombre Usuario "+ this.listUsuarios.get(0).getNombre()+" Codigo "+ this.listUsuarios.get(0).getCodigo() );                    
                  this.usuario = this.listUsuarios.get(0);                  
                  this.usuarioSelected = this.listUsuarios.get(0);                  
                  redireccion = "index.xhtml";
                }
                else
                {
                  this.mensaje = "El usuario no existe en el aplicativo..";          
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
    
    // Actualiza la clave pero antes la verifica
    
    public String updateClave()  {
        
        String redireccion = null;
        
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Actualizar Clave 1 "+this.usuario.getClave1()+ " Clave 2 "+this.usuario.getClave2()  );
        if ( ! this.usuario.getClave1().equals(this.usuario.getClave2())  )  
        {
         this.setMensaje("La clave nueva y la confirmacion, son diferentes. Intente de nuevo");
         return null;   
        }
        if ( session != null ){
            try {
                
                this.usuario.setClave(this.usuario.getClave1());
                System.out.println("Usuario update a realizar el proceso "  );
                session.update("updateClave", this.usuario);
                session.commit();
                this.setMensaje("Actualizada la clave en la Base Datos");
                
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
    
    public String updateInactivo()  {
        
        String redireccion = null;
        
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Actualizar Estado "+this.usuario.getNombre() );
        if ( session != null ){
            try {
                
                this.usuario.setEstado("Inactivo");
                System.out.println("Usuario Inactivo "  );
                session.update("update", this.usuario);
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
        System.out.println("Actualizar Estado "+this.usuario.getNombre() );
        if ( session != null ){
            try {
                
                this.usuario.setEstado("Activo");
                System.out.println("Usuario Activo "  );
                session.update("update", this.usuario);
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
    
    public String updateUsuario()  {
        
        String redireccion = null;
        
        this.setMensaje("");
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Nombre Usuario "+this.usuario.getNombre() );
        if ( session != null ){
            try {
                
                session.update("update", this.usuario);
                session.commit();
                this.setMensaje("Usuario Actualizado");
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        return null;
    }
        
    
      public void limpiar(){
           System.out.println("Limpiar filtros usuario .");

          try {
              this.usuarioSelected.limpiar();
                            
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }    
        
    
    public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  Usuario usuario = new Usuario();
  
  usuario.setClave("xyzff");
  usuario.setCodigo("xyz002");
 // usuario.setEstado("A");
//  usuario.setNombre("xyz adicion");
//  usuario.setPerfil("1");

//  UsuarioBean usuarioBean = new UsuarioBean();
//  usuarioBean.setUsuario(usuario);
  
//  usuarioBean.guardar();

// Buscar ususario por codigo y clave
  UsuarioBean usuarioBean = new UsuarioBean();

  usuario.setClave("xyzff");
  usuario.setCodigo("xyz002");

  usuarioBean.selectFilter(usuario);

}

   
    
}
