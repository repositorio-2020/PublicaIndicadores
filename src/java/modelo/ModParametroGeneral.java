/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.ParametroGeneral;
import org.apache.ibatis.session.SqlSession;


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

public class ModParametroGeneral   {

    private ParametroGeneral formato;
    private List<ParametroGeneral> listFormatos; 
    private Map<String,String> listaParametros;
    

    

    public ModParametroGeneral() {
         System.out.println(" INIT Parametro General ");
         this.formato = new ParametroGeneral();
         this.listaParametros = new HashMap<String, String>();
    }
    
    

    public ParametroGeneral getFormato() {
        return formato;
    }

    public void setFormato(ParametroGeneral formato) {
        this.formato = formato;
    }

    public List<ParametroGeneral> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<ParametroGeneral> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public Map<String, String> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(Map<String, String> listaParametros) {
        this.listaParametros = listaParametros;
    }

    
    
    public void selectFilter(ParametroGeneral  pParametro ) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("------------------selectFilter---------------------------");
        System.out.println("Codigo Secuencial del parametro General  "+ pParametro.getId() );
        
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("ParametroGeneral.selectFilter", pParametro);
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
         System.out.println(" Desplegar Parametro General Numero Items"+this.listFormatos.size());
         
         for ( int i = 0 ; i < this.listFormatos.size() ; i++  ) 
         {
            System.out.println(" Nombre del parametro "+ this.listFormatos.get(i).getNombre());
                                       
         }    
     }

    // Lee la lista de valores del parametro seleccionado para desplegar en la vista.
    public Map<String, String> getListaValores( String codigoLista ) {
         System.out.println(" --------------------------------------------  getListaValores codigo filtro "+codigoLista);
         
         ParametroGeneral  parametro  = new ParametroGeneral();
         parametro.setId(codigoLista);
                  
        try {
            
            this.selectFilter(parametro);
            
        } catch (Exception ex) {
            System.out.println(" Error al leer la lista de parametros  "+ex.getMessage());
            
            Logger.getLogger(ModParametroGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
         this.listaParametros = new LinkedHashMap<String, String>();
         for ( int i = 0 ; i < this.listFormatos.size() ; i++  ) 
         {
            System.out.println("Numero "+i+" Nombre del parametro "+ this.listFormatos.get(i).getNombre() + " Codigo "+ this.listFormatos.get(i).getCodigo());
            this.listaParametros.put( this.listFormatos.get(i).getNombre(),this.listFormatos.get(i).getCodigo());

         }    
        return this.listaParametros; 
     }


    
public static void main(String arg[]) throws Exception {
  
    
  Map<String,String> listaEstado;      
  Map<String,String> listaCobertura;      
  Map<String,String> listaPeriodo;      
  ModParametroGeneral formatoModelo = new ModParametroGeneral();
  
  // parametro.setId("2");
  // formatoModelo.selectFilter(parametro);
  // formatoModelo.desplegar();
  
  // listaEstado = formatoModelo.getListaValores("2");
  // listaCobertura = formatoModelo.getListaValores("3");
  
   listaPeriodo = formatoModelo.getListaValores("6");
  

}

   
    
}

