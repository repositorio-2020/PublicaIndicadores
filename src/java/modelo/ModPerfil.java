/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Perfil;
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

public class ModPerfil   {

    private Perfil formato;
    private List<Perfil> listFormatos; 
    private Map<String,String> listaParametros;

    public ModPerfil() {
         System.out.println(" INIT Perfil ");
         this.formato = new Perfil();
         this.listaParametros = new HashMap<String, String>();
    }
    
    

    public Perfil getFormato() {
        return formato;
    }

    public void setFormato(Perfil formato) {
        this.formato = formato;
    }

    public List<Perfil> getListFormatos() {
        return listFormatos;
    }

    public void setListFormatos(List<Perfil> listFormatos) {
        this.listFormatos = listFormatos;
    }

    public Map<String, String> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(Map<String, String> listaParametros) {
        this.listaParametros = listaParametros;
    }

    
    
    public void selectFilter(Perfil  pParametro ) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("------------------selectFilter---------------------------");
        System.out.println("Codigo Secuencial Perfiles  "+ pParametro.getPerfilCodigo() );
        
        if ( session != null ){
            try {
                
                this.listFormatos = session.selectList("Perfil.selectFilter", pParametro);
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
            System.out.println(" Nombre del parametro "+ this.listFormatos.get(i).getPerfilNombre());
                                       
         }    
     }

    // Lee la lista de valores del parametro seleccionado para desplegar en la vista.
    public Map<String, String> getListaValores(  ) {
         System.out.println(" --------------------------------------------  getListaValores Perfiles ");
         
         Perfil  parametro  = new Perfil();

         try {
            
            this.selectFilter(parametro);
            
        } catch (Exception ex) {
            System.out.println(" Error al leer la lista de perfiles "+ex.getMessage());
            
            Logger.getLogger(ModPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
         this.listaParametros = new LinkedHashMap<String, String>();
         for ( int i = 0 ; i < this.listFormatos.size() ; i++  ) 
         {
            System.out.println("Numero "+i+" Nombre del parametro "+ this.listFormatos.get(i).getPerfilNombre() + " Codigo "+ this.listFormatos.get(i).getPerfilCodigo());
            this.listaParametros.put( this.listFormatos.get(i).getPerfilNombre(),this.listFormatos.get(i).getPerfilCodigo());

         }    
        return this.listaParametros; 
     }

    
public static void main(String arg[]) throws Exception {
  
    
  Map<String,String> listaPerfil;      
  ModPerfil perfilModelo = new ModPerfil();
  
  // parametro.setId("2");
  // formatoModelo.selectFilter(parametro);
  // formatoModelo.desplegar();
  
  listaPerfil = perfilModelo.getListaValores();

}

   
    
}

