/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Perfil;
import java.io.Serializable;
import java.util.List;
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


@ManagedBean(name="PerfilBean")
@SessionScoped
// @RequestScoped
public class PerfilBean implements Serializable {

    private Perfil perfil;
    private List<Perfil> listPerfil; 
    private Perfil perfilSelected; 
    private String mensaje;  // Sacar los mensajes
    
    /**
     * Creates a new instance of PerfilBean
     */
    public PerfilBean() {
        perfil = new Perfil();
        perfilSelected = new Perfil();
        
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil pPerfil) {
        this.perfil = pPerfil;
    }

    public List<Perfil> getListPerfil() {
        return listPerfil;
    }

    public void setListPerfil(List<Perfil> plistPerfil) {
        this.listPerfil = plistPerfil;
    }

    public Perfil getPerfilSelected() {
        return perfilSelected;
    }

    public void setPerfilSelected(Perfil pPerfilSelected) {
        this.perfilSelected = pPerfilSelected;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String pMensaje) {
        this.mensaje = pMensaje;
    }
    
    
    
    
    public void selectFilter(Perfil pPerfil) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Buscar Perfil "+pPerfil.getPerfilNombre());
        
        if ( session != null ){
            try {
                
                this.listPerfil = session.selectList("Perfil.selectFilter", pPerfil);
                if ( this.listPerfil.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listPerfil.size()+ " Nombre Perfil "+ this.listPerfil.get(0).getPerfilNombre()+" Codigo "+ this.listPerfil.get(0).getPerfilCodigo() );                    
                  this.perfil = this.listPerfil.get(0);
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
        System.out.println("Buscar Perfil "+this.perfilSelected.getPerfilCodigo()+" Clave  "+this.perfilSelected.getPerfilNombre());
       
        if ( session != null ){
            try {
                
                this.listPerfil = session.selectList("Perfil.selectFilter", this.perfilSelected);
                if ( this.listPerfil.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+this.listPerfil.size()+ " Nombre Perfil "+ this.listPerfil.get(0).getPerfilNombre()+" Codigo "+ this.listPerfil.get(0).getPerfilCodigo() );                    
                  this.perfil = this.listPerfil.get(0);                  
                  this.perfilSelected = this.listPerfil.get(0);                  
                  redireccion = "";
                }
                else
                {
                  this.mensaje = "El perfil no existe en el aplicativo..";          
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
    
            
      public void limpiar(){
           System.out.println("Limpiar filtros usuario admin bean .");

          try {
              this.perfilSelected.limpiar();
              this.listPerfil.clear();
                            
        } catch (Exception ex) {
     
        }
      }        
    
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  Perfil perfil = new Perfil();
  
  perfil.setPerfilCodigo("");
  perfil.setPerfilNombre("");
 // perfil.setEstado("A");
//  perfil.setNombre("xyz adicion");
//  perfil.setPerfil("1");

//  PerfilBean perfilBean = new PerfilBean();
//  perfilBean.setPerfil(perfil);
  
//  perfilBean.guardar();

// Buscar ususario por codigo y clave
  PerfilBean perfilBean = new PerfilBean();

  perfil.setPerfilCodigo("");
  perfil.setPerfilNombre("");
  
  perfilBean.selectFilter(perfil);

}

   
    
}
