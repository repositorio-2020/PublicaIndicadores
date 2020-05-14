/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.mybatis.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author David Lara 
 * @version 20191029
 * @see pojo para el almacenamiento de Estudiantes
 * 
 */

public class Estudiante implements Serializable  {

private String id;
private String codigo;
private String nombre;
private String codEquiva;



    public Estudiante() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodEquiva() {
        return codEquiva;
    }

    public void setCodEquiva(String codEquiva) {
        this.codEquiva = codEquiva;
    }


      public void limpiar() {  
        this.codigo = "";
        this.id = "";
        this.nombre = "";
        this.codEquiva = "";
    }
    
    
}
