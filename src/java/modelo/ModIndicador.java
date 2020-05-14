/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Indicador;
import org.apache.ibatis.session.SqlSession;


import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
 


/**
 *
 * @author user1
 */

public class ModIndicador   {

    private Indicador formato;
    private List<Indicador> listFormatos; 
    private Map<String,String> listaParametros;

    public ModIndicador() {
         System.out.println(" INIT Indicadores ");
         this.formato = new Indicador();
         this.listaParametros = new HashMap<String, String>();
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

    public Map<String, String> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(Map<String, String> listaParametros) {
        this.listaParametros = listaParametros;
    }

    
    
    public void selectFilter(Indicador  pParametro ) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("------------------selectFilter---------------------------");
        System.out.println("Codigo Secuencial Indicador "+ pParametro.getId() );
        
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("Indicador.selectFilter", pParametro);
                if ( this.listFormatos.size() > 0 ) 
                {
                  System.out.println(" ------ > Tamaño de la lista Indicadores "+this.listFormatos.size());                    
                  this.formato = this.listFormatos.get(0);
                }
                else
                {
                  this.formato = null;                    
                  System.out.println(" ------ > No se encontro informacion de los Indicadores");                    
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
         System.out.println(" Desplegar Indicadores Numero Items"+this.listFormatos.size());
         
         for ( int i = 0 ; i < this.listFormatos.size() ; i++  ) 
         {
            System.out.println(" Nombre Indicador "+ this.listFormatos.get(i).getNombre());
                                       
         }    
     }

    // Lee la lista de valores del parametro seleccionado para desplegar en la vista.
    public Map<String, String> getListaValores(  ) {
         System.out.println(" --------------------------------------------  getListaValores codigo filtro ");
         
         Indicador  parametro  = new Indicador();
         parametro.setEstado("Activo");
                  
        try {
            
            this.selectFilter(parametro);
            
        } catch (Exception ex) {
            System.out.println(" Error al leer Indicadores "+ex.getMessage());
            
            Logger.getLogger(ModIndicador.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
         this.listaParametros = new LinkedHashMap<String, String>();
         for ( int i = 0 ; i < this.listFormatos.size() ; i++  ) 
         {
            System.out.println("Numero "+i+" Nombre del Indicador "+ this.listFormatos.get(i).getNombre() + " Codigo "+ this.listFormatos.get(i).getId());
            this.listaParametros.put( this.listFormatos.get(i).getNombre(),this.listFormatos.get(i).getCodigo());

         }    
        return this.listaParametros; 
     }

    
public static void main(String arg[]) throws Exception {
  
    
  Map<String,String> listaIndicador;      
  ModIndicador indicadorModelo = new ModIndicador();
  
  // parametro.setId("2");
  // formatoModelo.selectFilter(parametro);
  // formatoModelo.desplegar();
  
  listaIndicador = indicadorModelo.getListaValores();
  

}

   
    
}

