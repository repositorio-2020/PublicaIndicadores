/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.IndicadorGeneral;
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


/**
 *
 * @author user1
 */
@ManagedBean(name="IndicadorGeneralBean")
@ViewScoped
// @RequestScoped

public class IndicadorGeneralBean implements Serializable  {

    private IndicadorGeneral indicadorGeneral;
    private List<IndicadorGeneral> listIndicadorGeneral; 
    private IndicadorGeneral indicadorGeneralSelected; 
    
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT ----------------------------------------------------------------------");
         indicadorGeneral = new IndicadorGeneral();
         indicadorGeneralSelected = new IndicadorGeneral();
        
     }
     
    
    
    /**
     * Creates a new instance of UsuarioBean
     */
    
    public IndicadorGeneralBean() {
//        estudianteIndicador = new EstudianteIndicador();
//        estudianteIndicadorSelected = new EstudianteIndicador();
//        this.selectAll();
 indicadorGeneral = new IndicadorGeneral();
         indicadorGeneralSelected = new IndicadorGeneral();
        
    }

    public IndicadorGeneral getIndicadorGeneral() {
        return indicadorGeneral;
    }

    public void setIndicadorGeneral(IndicadorGeneral indicadorGeneral) {
        this.indicadorGeneral = indicadorGeneral;
    }

    public List<IndicadorGeneral> getListIndicadorGeneral() {
        return listIndicadorGeneral;
    }

    public void setListIndicadorGeneral(List<IndicadorGeneral> listIndicadorGeneral) {
        this.listIndicadorGeneral = listIndicadorGeneral;
    }

    public IndicadorGeneral getIndicadorGeneralSelected() {
        return indicadorGeneralSelected;
    }

    public void setIndicadorGeneralSelected(IndicadorGeneral indicadorGeneralSelected) {
        this.indicadorGeneralSelected = indicadorGeneralSelected;
    }

        public String selectFilterSelected()  {
        
        String redireccion = null;
        
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("123----------------------------------------------------------------------");
        System.out.println("Buscar Usuario "+this.indicadorGeneral.getNombreEstudiante());
       
        if ( session != null ){
            try {
                
                this.listIndicadorGeneral = session.selectList("IndicadorGeneral.selectFilter", this.indicadorGeneralSelected);
                if ( this.listIndicadorGeneral.size() > 0 ) {
                  this.indicadorGeneral = this.listIndicadorGeneral.get(0);      
                  System.out.println("--- Numero de filas de la lista "+this.listIndicadorGeneral.size());
                  
                  redireccion = "";
                }
                else
                {
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
           System.out.println("Limpiar filtros IndicadorGeneralBean .");

          try {
              this.indicadorGeneralSelected.limpiar();
                            
        } catch (Exception ex) {
            Logger.getLogger(IndicadorGeneralBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }       

    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  IndicadorGeneral indicadorGeneral = new IndicadorGeneral();

  
  IndicadorGeneralBean indGralBean = new IndicadorGeneralBean();
  
   indGralBean.indicadorGeneralSelected.setAgno("2018");
  indGralBean.indicadorGeneralSelected.setNroPeriodo("50");

  indGralBean.selectFilterSelected();
  
System.out.print("Tamaño de la lista xx ");
//System.out.print("Direccion item - "+list.get(0).getDireccion());

}

   
    
}
