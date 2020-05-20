/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Modelo;
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

public class ModModelo   {

    private Modelo formato;
    private List<Modelo> listFormatos; 
    private Map<String,String> listaModelos;

    public ModModelo() {
         System.out.println(" INIT Modelo ");
         this.formato = new Modelo();
         this.listaModelos = new HashMap<String, String>();
    }
    
    

    public Modelo getFormato() {
        return formato;
    }

    public void setFormato(Modelo formato) {
        this.formato = formato;
    }

    public List<Modelo> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<Modelo> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public Map<String, String> getListaParametros() {
        return listaModelos;
    }

    public void setListaParametros(Map<String, String> listaModelos) {
        this.listaModelos = listaModelos;
    }

    
    
    public void selectFilter(Modelo  pParametro ) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("------------------selectFilter---------------------------");
        System.out.println("Codigo Secuencial del parametro General  "+ pParametro.getMod_secue() );
        
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("Modelo.selectFilter", pParametro);
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
         System.out.println(" Desplegar Modeloses Numero Items"+this.listFormatos.size());
         
         for ( int i = 0 ; i < this.listFormatos.size() ; i++  ) 
         {
            System.out.println(" Nombre de la universidad "+ this.listFormatos.get(i).getMod_nombre());
                                       
         }    
     }

    // Lee la lista de valores del parametro seleccionado para desplegar en la vista.
    public Map<String, String> getListaValores(  ) {
         System.out.println(" --------------------------------------------  getListaValores codigo modelos ");
         
         Modelo  parametro  = new Modelo();
         parametro.limpiar();
         parametro.getMod_estado();
         parametro.setMod_estado("Activo");
                  
        try {
            
            this.selectFilter(parametro);
            
        } catch (Exception ex) {
            System.out.println(" Error al leer la lista de universidades  "+ex.getMessage());
            
            Logger.getLogger(ModModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
         this.listaModelos = new LinkedHashMap<String, String>();
         for ( int i = 0 ; i < this.listFormatos.size() ; i++  ) 
         {
            System.out.println("Numero "+i+" Nombre Modelos "+ this.listFormatos.get(i).getMod_nombre() + " Codigo "+ this.listFormatos.get(i).getMod_secue());
            this.listaModelos.put( this.listFormatos.get(i).getMod_nombre(),this.listFormatos.get(i).getMod_secue());

         }    
        return this.listaModelos; 
     }

    
public static void main(String arg[]) throws Exception {
  
    
  Map<String,String> listaModelos;      
  ModModelo modeloModelo = new ModModelo();
  
  // parametro.setId("2");
  // formatoModelo.selectFilter(parametro);
  // formatoModelo.desplegar();
  
  listaModelos = modeloModelo.getListaValores();

}

   
    
}

