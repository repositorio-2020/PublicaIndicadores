/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;




import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Estudiante;
import cl.mybatis.pojos.EstudianteIndicador;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.apache.ibatis.session.SqlSession;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.faces.bean.ManagedBean;
import modelo.ModIndicador;
import modelo.ModParametroGeneral;


/**
 *
 * @author user1
 */
@ManagedBean(name="EstudianteIndicadorBean")
@ViewScoped


public class EstudianteIndicadorBean implements Serializable  {

    private EstudianteIndicador estudianteIndicador;
    private List<EstudianteIndicador> listEstudianteIndicadors; 
    private EstudianteIndicador estudianteIndicadorSelected; 
    private Map<String,String> indicadores = new HashMap<String, String>();
    private Map<String,String> periodos = new HashMap<String, String>();
    private Map<String,String> agnos = new HashMap<String, String>();

    
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT ----------------------------------------------------------------------");
         estudianteIndicador = new EstudianteIndicador();
         estudianteIndicadorSelected = new EstudianteIndicador();

         // Toma de la BD los listados parametros periodos.
         ModParametroGeneral formatoModelo = new ModParametroGeneral();
         periodos = formatoModelo.getListaValores("6");
         
         // Toma de la BD los listados parametros Años.
         agnos = formatoModelo.getListaValores("5");

         ModIndicador indicadorModelo = new ModIndicador();
         indicadores = indicadorModelo.getListaValores();
         
         
         
         
     }
     
    
    
    /**
     * Creates a new instance of UsuarioBean
     */
    
    public EstudianteIndicadorBean() {
//        estudianteIndicador = new EstudianteIndicador();
//        estudianteIndicadorSelected = new EstudianteIndicador();
//        this.selectAll();
        
    }

    
    
    
    public EstudianteIndicador getEstudianteIndicador() {
        return estudianteIndicador;
    }

        
    public void setEstudianteIndicador(EstudianteIndicador estudianteIndicador) {
        this.estudianteIndicador = estudianteIndicador;
    }

    public EstudianteIndicador getEstudianteIndicadorSelected() {
        return estudianteIndicadorSelected;
    }

    public void setEstudianteIndicadorSelected(EstudianteIndicador estudianteIndicadorSelected) {
        System.out.println("----------------------------------------------------------------------");
        
        this.estudianteIndicadorSelected = estudianteIndicadorSelected;
    }

    
    public List<EstudianteIndicador> getListEstudianteIndicadors() {
        return listEstudianteIndicadors;
    }

    public Map<String, String> getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(Map<String, String> indicadores) {
        this.indicadores = indicadores;
    }

    public Map<String, String> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(Map<String, String> periodos) {
        this.periodos = periodos;
    }

    public Map<String, String> getAgnos() {
        return agnos;
    }

    public void setAgnos(Map<String, String> agnos) {
        this.agnos = agnos;
    }

     
    
    
    public void aceptar() {
        
       CargueBean cargueBean = new CargueBean();

       /*
       if ( estudianteIndicadorSelected.getEstado().equalsIgnoreCase("EstudianteIndicadordo") == true )
       {
            FacesMessage message = new FacesMessage("Advertencia...", " Este item ya esta estudianteIndicadordo...");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
           
       }
       */
       
        
       System.out.println("Aceptar.......  "+estudianteIndicadorSelected.getIndicadorNombre()+ " Estudiante  "+estudianteIndicadorSelected.getEstudianteNombre()+ " Indicador "+estudianteIndicadorSelected.getIndicadorNombre());
//       System.out.println("Aceptar.......  .............");
        
    }
    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }
    
    
    public void setListEstudianteIndicadors(List<EstudianteIndicador> listEstudianteIndicadors) {
        this.listEstudianteIndicadors = listEstudianteIndicadors;
    }
    
    
    public void selectAll(){
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Ingreso selet all estudianteIndicador");
        
        if ( session != null ){
            try {
                
                listEstudianteIndicadors = session.selectList("EstudianteIndicador.selectAll");     
                this.estudianteIndicadorSelected = listEstudianteIndicadors.get(0);
                System.out.println("Ver 02 - Tamaño de la lista "+listEstudianteIndicadors.size()+ " id Transaccion "+ listEstudianteIndicadors.get(1).getEstudianteCodigo() );
                
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }

    public void selectFilterX(){
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Ingreso select Filter X");
        
        if ( session != null ){
            try {
                
                System.out.println(" Estudiante  "+ estudianteIndicadorSelected.getEstudianteCodigo() );
                
                try {
                    selectFilter(estudianteIndicadorSelected);
                } catch (Exception ex) {
                    Logger.getLogger(EstudianteIndicadorBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }

    public void selectFilter(EstudianteIndicador pEstudianteIndicador) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select ID");
        
        if ( session != null ){
            try {
                
                listEstudianteIndicadors = session.selectList("EstudianteIndicador.selectFilter", pEstudianteIndicador);
                if ( listEstudianteIndicadors.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+listEstudianteIndicadors.size()+ " Nombre Estudiante "+ listEstudianteIndicadors.get(0).getEstudianteNombre()+" Indicador "+ listEstudianteIndicadors.get(0).getIndicadorNombre() );                    
                }
                
                this.estudianteIndicador = listEstudianteIndicadors.get(0);
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }
    

    public void selectFilter( ){
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Ingreso selet all estudianteIndicador");
        
        if ( session != null ){
            try {
                
                estudianteIndicadorSelected = session.selectOne("EstudianteIndicador.estudianteIndicadorSelected");
                System.out.println(" Estudiante  "+ estudianteIndicadorSelected.getEstudianteCodigo() );
                
                try {
                    selectFilter(estudianteIndicadorSelected);
                } catch (Exception ex) {
                    Logger.getLogger(EstudianteIndicadorBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }




    
    public String guardar(){
        SqlSession session = new myBatisUtil().getSession();
        if ( session != null ){
            try {
                session.insert("EstudianteIndicador.insert", estudianteIndicador);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "EstudianteIndicador Creado"));
        return "index";
    }
    
    
    public String actualizar( EstudianteIndicador pEstudianteIndicador ){
        SqlSession session = new myBatisUtil().getSession();
        if ( session != null ){
            try {
                session.update("EstudianteIndicador.update", pEstudianteIndicador);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "EstudianteIndicador Creado"));
        return "index";
    }
 
       public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((EstudianteIndicador) event.getObject()).getIdTransaccion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println("---------------------------------------------------------------------- ONrOWeDIT ");

    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((EstudianteIndicador) event.getObject()).getIdTransaccion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println("---------------------------------------------------------------------- CANCEL ");
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }
                System.out.println("---------------------------------------------------------------------- edit ");
        
    }
    

      public void actualizaEstudiante(){
           System.out.println("1. - > Toma la informacion de captura del estudiante. codigo "+this.estudianteIndicadorSelected.getEstudianteCodigo());
           this.estudianteIndicadorSelected.setEstudianteNombre("ACTUALIZADO "+this.estudianteIndicadorSelected.getEstudianteCodigo());
           
           
          EstudianteBean estudianteBean;
           estudianteBean = new EstudianteBean();
           
           Estudiante estudiante = new Estudiante();
           
           estudiante.setCodigo(this.estudianteIndicadorSelected.getEstudianteCodigo());
          try {
              estudianteBean.selectFilter(estudiante); 
              // this.estudianteIndicadorSelected.limpiar();
              this.estudianteIndicadorSelected.setEstudianteNombre(estudianteBean.getFormatoSelected().getNombre());
              
                            
        } catch (Exception ex) {
            Logger.getLogger(EstudianteIndicadorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      }           

    
 
      public void limpiar(){
           System.out.println("Limpiar filtros EstudianteIndicador .");

          try {
              this.estudianteIndicadorSelected.limpiar();
                            
        } catch (Exception ex) {
            Logger.getLogger(EstudianteIndicadorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }           
       
    
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  EstudianteIndicador estudianteIndicador = new EstudianteIndicador();
  

  EstudianteIndicadorBean estudianteIndicadorBean = new EstudianteIndicadorBean();
  
  
  
  
  // estudianteIndicador.setId("2");
  // estudianteIndicador.setEstado("PENDIENTE");
  
  
  // estudianteIndicador.setEstudianteCodigo("111609");
  
  estudianteIndicador.setIndicadorCodigo("C16");
  
  
  estudianteIndicadorBean.selectFilter(estudianteIndicador);
  
  
 // estudianteIndicadorBean.selectAll();
 List<EstudianteIndicador> list = estudianteIndicadorBean.getListEstudianteIndicadors();

System.out.print("Tamaño de la lista xx "+list.size());
//System.out.print("Direccion item - "+list.get(0).getDireccion());
  

}

   
    
}
