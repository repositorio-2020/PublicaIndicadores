/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Transaccion;
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
@ManagedBean(name="TransaccionBean")
@ViewScoped
// @RequestScoped

public class TransaccionBean implements Serializable  {

    private Transaccion transaccion;
    private List<Transaccion> listTransacciones; 
    private Transaccion transaccionSelected; 

    
    public TransaccionBean(Transaccion transaccion, List<Transaccion> listTransacciones, Transaccion transaccionSelected ) {
        this.transaccion = transaccion;
        this.listTransacciones = listTransacciones;
        this.transaccionSelected = transaccionSelected;
    }
    
    public TransaccionBean( ) {
        this.transaccion = null;
        this.listTransacciones = null;
        this.transaccionSelected = null;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public List<Transaccion> getListTransacciones() {
        return listTransacciones;
    }

    public void setListTransacciones(List<Transaccion> listTransacciones) {
        this.listTransacciones = listTransacciones;
    }

    public Transaccion getTransaccionSelected() {
        return transaccionSelected;
    }

    public void setTransaccionSelected(Transaccion transaccionSelected) {
        this.transaccionSelected = transaccionSelected;
    }
    
    



    
    
    public void aceptar() {
       System.out.println("Aceptar.......  ");
        
    }
    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }
    

    public void selectFilter(Transaccion pTransaccion) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select ID");
        
        if ( session != null ){
            try {
                
                listTransacciones = session.selectList("Transaccion.selectFilter", pTransaccion);
                if ( listTransacciones.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+listTransacciones.size());
                }
                
                this.transaccion = listTransacciones.get(0);
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }
    
    
    

    public void selectAll(){
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Ingreso selet all agenda");
        
        if ( session != null ){
            try {
                
                this.listTransacciones = session.selectList("Tx.selectAll");  
                  System.out.println(" numerod e registros de cargue "+this.listTransacciones.size());
//                System.out.println("Ver 02 - Tamaño de la lista "+listAgendas.size()+ " Nombre 1 "+ listAgendas.get(1).getUa() );
                
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }
      
      
      
    public String insertar(Transaccion pTransaccion){
        return "Transaccion";
    }

    public String generaFormato( String pCodigo ){
        
        

     return "";
     
    }


    public String generaFormato( String pCodigo, String pMail, String pUsuario ){
        
        

     return "";
     
    }




      
   // ------------------------------------------------------------   
    
    
public static void main(String arg[]) throws Exception {
  
  
  
}

   
    
}
