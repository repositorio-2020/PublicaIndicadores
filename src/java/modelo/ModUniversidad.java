/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Universidad;
import org.apache.ibatis.session.SqlSession;


import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
 


/**
 *
 * @author user1
 */

public class ModUniversidad   {

    private Universidad formato;
    private List<Universidad> listFormatos; 
    private Map<String,String> listaUniversidad;

    public ModUniversidad() {
         System.out.println(" INIT Universidades ");
         this.formato = new Universidad();
         this.listaUniversidad = new HashMap<String, String>();
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

    public Map<String, String> getListaParametros() {
        return listaUniversidad;
    }

    public void setListaParametros(Map<String, String> listaUniversidad) {
        this.listaUniversidad = listaUniversidad;
    }

    
    
    public void selectFilter(Universidad  pParametro ) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("------------------selectFilter---------------------------");
        System.out.println("Codigo Secuencial del parametro General  "+ pParametro.getId() );
        
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("Universidad.selectFilter", pParametro);
                if ( this.listFormatos.size() > 0 ) 
                {
                  System.out.println(" ------ > Tamaño de la lista Parametros "+this.listFormatos.size());                    
                  this.formato = this.listFormatos.get(0);
                }
                else
                {
                  this.formato = null;                    
                  System.out.println(" ------ > No se encontro informacion de los parametros Generales");                    
                }
                    
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }
    
    public void desplegar() {
         System.out.println(" Desplegar Universidades Numero Items"+this.listFormatos.size());
         
         for ( int i = 0 ; i < this.listFormatos.size() ; i++  ) 
         {
            System.out.println(" Nombre de la universidad "+ this.listFormatos.get(i).getNombre());
                                       
         }    
     }

    // Lee la lista de valores del parametro seleccionado para desplegar en la vista.
    public Map<String, String> getListaValores(  ) {
         System.out.println(" --------------------------------------------  getListaValores codigo universidad ");
         
         Universidad  parametro  = new Universidad();
         parametro.setEstado("Activo");
                  
        try {
            
            this.selectFilter(parametro);
            
        } catch (Exception ex) {
            System.out.println(" Error al leer la lista de universidades  "+ex.getMessage());
            
            Logger.getLogger(ModUniversidad.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
         this.listaUniversidad = new LinkedHashMap<String, String>();
         for ( int i = 0 ; i < this.listFormatos.size() ; i++  ) 
         {
            System.out.println("Numero "+i+" Nombre Universidad "+ this.listFormatos.get(i).getNombre() + " Codigo "+ this.listFormatos.get(i).getId());
            this.listaUniversidad.put( this.listFormatos.get(i).getNombre(),this.listFormatos.get(i).getId());

         }    
        return this.listaUniversidad; 
     }

    
public static void main(String arg[]) throws Exception {
  
    
  Map<String,String> listaUniversidades;      
  ModUniversidad universidadModelo = new ModUniversidad();
  
  // parametro.setId("2");
  // formatoModelo.selectFilter(parametro);
  // formatoModelo.desplegar();
  
  listaUniversidades = universidadModelo.getListaValores();

}

   
    
}

