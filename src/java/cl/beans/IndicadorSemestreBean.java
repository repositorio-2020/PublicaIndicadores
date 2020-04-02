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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;


/**
 *
 * @author user1
 */
@ManagedBean(name="IndicadorSemestreBean")
@ViewScoped


public class IndicadorSemestreBean implements Serializable  {

    private EstudianteIndicador indicadorSemestre;
    private List<EstudianteIndicador> listIndicadorSemestres; 
    private EstudianteIndicador indicadorSemestreSelected; 
    private Map<String,String> indicadores = new HashMap<String, String>();
    private Map<String,String> periodos = new HashMap<String, String>();
    private Map<String,String> agnos = new HashMap<String, String>();

    
    private BarChartModel barModel;

    
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT ----------------------------------------------------------------------");
         indicadorSemestre = new EstudianteIndicador();
         indicadorSemestreSelected = new EstudianteIndicador();

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
    
    public IndicadorSemestreBean() {
//        estudianteIndicador = new IndicadorSemestre();
//        estudianteIndicadorSelected = new IndicadorSemestre();
//        this.selectAll();
        
    }

    
    
    
    public EstudianteIndicador getIndicadorSemestre() {
        return indicadorSemestre;
    }

        
    public void setIndicadorSemestre(EstudianteIndicador estudianteIndicador) {
        this.indicadorSemestre = estudianteIndicador;
    }

    public EstudianteIndicador getIndicadorSemestreSelected() {
        return indicadorSemestreSelected;
    }

    public void setIndicadorSemestreSelected(EstudianteIndicador estudianteIndicadorSelected) {
        System.out.println("----------------------------------------------------------------------");
        
        this.indicadorSemestreSelected = estudianteIndicadorSelected;
    }

    
    public List<EstudianteIndicador> getListIndicadorSemestres() {
        return listIndicadorSemestres;
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

    public BarChartModel getBarModel() {

        int intPeriodo = 33;
        int valor = 0;
        String strPeriodo = ""; 
        
        
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Comportamiento x Periodo");
         
        if (  listIndicadorSemestres == null || listIndicadorSemestres.size() == 0 ) {

            boys.set("Periodo",0);

            model.addSeries(boys);
 
            barModel = model;
        
            barModel.setTitle("Logs Diario ");
            barModel.setLegendPosition("ne");
 
            Axis xAxis = barModel.getAxis(AxisType.X);
            xAxis.setLabel("Nro Periodo");
 
            Axis yAxis = barModel.getAxis(AxisType.Y);
            yAxis.setLabel("Vlr Promedio");
            yAxis.setMin(0);
            yAxis.setMax(300);      
            return barModel;

            
        }
        
        for ( int i = 0 ; i < listIndicadorSemestres.size() ; i++ )   
        {
            intPeriodo +=  1;
            strPeriodo = "" + intPeriodo;
            valor = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.listIndicadorSemestres.get(i).getValorPromedio())));
            boys.set(strPeriodo,valor);
            
        }
        
        
 
        model.addSeries(boys);
 
        barModel = model;
        
        barModel.setTitle("Logs Diario "+this.getIndicadorSemestreSelected().getEstudianteNombre());
        barModel.setLegendPosition("ne");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Nro Periodo");
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Vlr Promedio");
        yAxis.setMin(0);
        yAxis.setMax(300);      
        
        
        
        
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

     
    
    
    public void aceptar() {
        
       CargueBean cargueBean = new CargueBean();

       /*
       if ( estudianteIndicadorSelected.getEstado().equalsIgnoreCase("IndicadorSemestredo") == true )
       {
            FacesMessage message = new FacesMessage("Advertencia...", " Este item ya esta estudianteIndicadordo...");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
           
       }
       */
       
        
       System.out.println("Aceptar.......  "+indicadorSemestreSelected.getIndicadorNombre()+ " Estudiante  "+indicadorSemestreSelected.getEstudianteNombre()+ " Indicador "+indicadorSemestreSelected.getIndicadorNombre());
//       System.out.println("Aceptar.......  .............");
        
    }
    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }
    
    
    public void setListIndicadorSemestres(List<EstudianteIndicador> listIndicadorSemestres) {
        this.listIndicadorSemestres = listIndicadorSemestres;
    }
    
    
    public void selectAll(){
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Ingreso selet all estudianteIndicador");
        
        if ( session != null ){
            try {
                
                listIndicadorSemestres = session.selectList("IndicadorSemestre.selectAll");     
                this.indicadorSemestreSelected = listIndicadorSemestres.get(0);
                System.out.println("Ver 02 - Tamaño de la lista "+listIndicadorSemestres.size()+ " id Transaccion "+ listIndicadorSemestres.get(1).getEstudianteCodigo() );
                
                
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
                
                System.out.println(" Estudiante  "+ indicadorSemestreSelected.getEstudianteCodigo() );
                
                try {
                    selectFilter(indicadorSemestreSelected);
                } catch (Exception ex) {
                    Logger.getLogger(IndicadorSemestreBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }

    public void selectFilter(EstudianteIndicador pIndicadorSemestre) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select ID");
        
        if ( session != null ){
            try {
                
                listIndicadorSemestres = session.selectList("IndicadorSemestre.selectFilter", pIndicadorSemestre);
                if ( listIndicadorSemestres.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+listIndicadorSemestres.size()+ " Nombre Estudiante "+ listIndicadorSemestres.get(0).getEstudianteNombre()+" Indicador "+ listIndicadorSemestres.get(0).getIndicadorNombre() );                    
                }
                
                this.indicadorSemestre = listIndicadorSemestres.get(0) ;
                
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
                
                indicadorSemestreSelected = session.selectOne("IndicadorSemestre.selectFilter");
                System.out.println(" Estudiante  "+ indicadorSemestreSelected.getEstudianteCodigo() );
                
                try {
                    selectFilter(indicadorSemestreSelected);
                } catch (Exception ex) {
                    Logger.getLogger(IndicadorSemestreBean.class.getName()).log(Level.SEVERE, null, ex);
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
                session.insert("IndicadorSemestre.insert", indicadorSemestre);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "IndicadorSemestre Creado"));
        return "index";
    }
    
    
    public String actualizar( EstudianteIndicador pIndicadorSemestre ){
        SqlSession session = new myBatisUtil().getSession();
        if ( session != null ){
            try {
                session.update("IndicadorSemestre.update", pIndicadorSemestre);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "IndicadorSemestre Creado"));
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
           System.out.println("1. - > Toma la informacion de captura del estudiante. codigo "+this.indicadorSemestreSelected.getEstudianteCodigo());
           this.indicadorSemestreSelected.setEstudianteNombre("ACTUALIZADO "+this.indicadorSemestreSelected.getEstudianteCodigo());
           
           
          EstudianteBean estudianteBean;
           estudianteBean = new EstudianteBean();
           
           Estudiante estudiante = new Estudiante();
           
           estudiante.setCodigo(this.indicadorSemestreSelected.getEstudianteCodigo());
          try {
              estudianteBean.selectFilter(estudiante); 
              // this.estudianteIndicadorSelected.limpiar();
              this.indicadorSemestreSelected.setEstudianteNombre(estudianteBean.getFormatoSelected().getNombre());
              
                            
        } catch (Exception ex) {
            Logger.getLogger(IndicadorSemestreBean.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      }           

    
 
      public void limpiar(){
           System.out.println("Limpiar filtros IndicadorSemestre .");

          try {
              this.indicadorSemestreSelected.limpiar();
                            
        } catch (Exception ex) {
            Logger.getLogger(IndicadorSemestreBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }           
       
    
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  EstudianteIndicador estudianteIndicador = new EstudianteIndicador();
  

  IndicadorSemestreBean estudianteIndicadorBean = new IndicadorSemestreBean();
  
  
  
  
  // estudianteIndicador.setId("2");
  // estudianteIndicador.setEstado("PENDIENTE");
  
  
  // estudianteIndicador.setEstudianteCodigo("111609");
  
  estudianteIndicador.setIndicadorCodigo("C_2_6");
  
  
  estudianteIndicadorBean.selectFilter(estudianteIndicador);
  
  
 // estudianteIndicadorBean.selectAll();
 List<EstudianteIndicador> list = estudianteIndicadorBean.getListIndicadorSemestres();

System.out.print("Tamaño de la lista xx "+list.size());
//System.out.print("Direccion item - "+list.get(0).getDireccion());
  

}

   
    
}
