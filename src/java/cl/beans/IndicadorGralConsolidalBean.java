/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;



import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

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
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;


/**
 *
 * @author user1
 */
@ManagedBean(name="IndicadorGralConsolidalBean")
@ViewScoped
// @RequestScoped

public class IndicadorGralConsolidalBean implements Serializable  {

    private IndicadorGeneral indicadorGeneral;
    private List<IndicadorGeneral> listIndicadorGeneral; 
    private IndicadorGeneral indicadorGeneralSelected; 

  
    private BarChartModel barModel;
    private BarChartModel barModelFranjas;
//    private HorizontalBarChartModel horizontalBarModel; 
        
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT ----------------------------------------------------------------------");
         indicadorGeneral = new IndicadorGeneral();
         indicadorGeneralSelected = new IndicadorGeneral();
         indicadorGeneralSelected.setPro_2_15_DOM("0");
         indicadorGeneralSelected.setPro_2_15_LUN("0");
         indicadorGeneralSelected.setPro_2_15_MAR("0");
         indicadorGeneralSelected.setPro_2_15_MIE("0");
         indicadorGeneralSelected.setPro_2_15_JUE("0");
         indicadorGeneralSelected.setPro_2_15_VIE("0");
         indicadorGeneralSelected.setPro_2_15_SAB("0");
 
         indicadorGeneralSelected.setPro_2_8_F0("0");
         indicadorGeneralSelected.setPro_2_8_F6("0");
         indicadorGeneralSelected.setPro_2_8_F12("0");
         indicadorGeneralSelected.setPro_2_8_F18("0");


    }
     
    
    
    /**
     * Creates a new instance of UsuarioBean
     */
    
    public IndicadorGralConsolidalBean() {
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
        System.out.println("NUevo Enero 23 - 2020 -------------------------------------------");
        System.out.println("Buscar Usuario "+this.indicadorGeneral.getNombreEstudiante());
       
        if ( session != null ){
            try {
                
                this.listIndicadorGeneral = session.selectList("IndicadorGralConsolida.selectFilter", this.indicadorGeneralSelected);
                if ( this.listIndicadorGeneral.size() > 0 ) {
                  this.indicadorGeneral = this.listIndicadorGeneral.get(0);    
                  this.indicadorGeneralSelected = this.listIndicadorGeneral.get(0); 
                  System.out.println("--- franja 0 lectura "+this.indicadorGeneralSelected.getPro_2_8_F0());
                  System.out.println("--- franja 1 lectura "+this.indicadorGeneralSelected.getPro_2_8_F6());
                  
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

    public BarChartModel getBarModel() {
        int lunes, martes, miercoles, jueves, viernes, sabado, domingo;
        
        
        lunes = 0; 
        martes = 0; 
        miercoles = 0;
        jueves = 0; 
        viernes = 0; 
        sabado = 0; 
        domingo  = 0;
        
        BarChartModel model = new BarChartModel();
 
        if ( this.getIndicadorGeneralSelected().getPro_2_15_LUN() != null ) 
        {
          lunes = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_15_LUN())));  
          martes = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_15_MAR()))); 
          miercoles = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_15_MIE()))); 
          jueves = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_15_JUE()))); 
          viernes = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_15_VIE()))); 
          sabado = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_15_SAB()))); 
          domingo  = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_15_DOM()))); 
            
        }       
        
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Dias No Habil");
        boys.set("Lunes", 0);
        boys.set("Martes", 0);
        boys.set("Miercoles", 0);
        boys.set("Jueves", 0);
        boys.set("Viernes", 0);
        boys.set("Sabado", sabado);
        boys.set("Domingo", domingo);
        
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Dias Habil");
        girls.set("Lunes", lunes);
        girls.set("Martes", martes);
        girls.set("Miercoles", miercoles);
        girls.set("Jueves", jueves);
        girls.set("Viernes", viernes);
        girls.set("Sabado", 0);
        girls.set("Domingo", 0);
 
        model.addSeries(boys);
        model.addSeries(girls);
 
        barModel = model;
        
        barModel.setTitle("Logs Diario "+this.getIndicadorGeneralSelected().getNombreEstudiante()+" Periodo "+this.getIndicadorGeneralSelected().getNroPeriodo());
        barModel.setLegendPosition("ne");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Dias Semana");
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Visitas");
        yAxis.setMin(0);
        yAxis.setMax(150);      
        
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public BarChartModel getBarModelFranjas() {
       int franja_1, franja_2, franja_3, franja_4 ;
        
        
        franja_1 = 0; 
        franja_2 = 0; 
        franja_3 = 0;
        franja_4 = 0; 
        
        BarChartModel model = new BarChartModel();

        System.out.println("Estudiante ------ "+this.getIndicadorGeneralSelected().getCodeEstudiante());
        System.out.println("Resultado Franja 01 ------ "+this.getIndicadorGeneralSelected().getPro_2_8_F0());
        System.out.println("Resultado Franja 06 ------ "+this.getIndicadorGeneralSelected().getPro_2_8_F6());
        System.out.println("Resultado Franja 18------ "+this.getIndicadorGeneralSelected().getPro_2_8_F12());
        System.out.println("Resultado Franja 24 ------ "+this.getIndicadorGeneralSelected().getPro_2_8_F18());
        
        
        
        
        if ( this.getIndicadorGeneralSelected().getInd_2_8_F0() != null ) 
        {
            
   
          franja_1 = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_8_F0())));  
          franja_2 = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_8_F6()))); 
          franja_3 = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_8_F12()))); 
          franja_4 = Integer.parseInt(String.format( "%.0f", Float.parseFloat(this.getIndicadorGeneralSelected().getPro_2_8_F18()))); 
            
        }       
        
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Franja Horarias");
        boys.set("0  - 6", franja_1);
        boys.set("6  - 12", franja_2);
        boys.set("12 - 18", franja_3);
        boys.set("18 - 24", franja_4);

 /*
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Franja");
        girls.set("Lunes", franja_1);
        girls.set("Martes", martes);
        girls.set("Miercoles", miercoles);
        girls.set("Jueves", jueves);
        girls.set("Viernes", viernes);
        girls.set("Sabado", 0);
        girls.set("Domingo", 0);
*/
 
        model.addSeries(boys);
 
        barModelFranjas = model;
        
        barModelFranjas.setTitle("Logs Diario "+this.getIndicadorGeneralSelected().getNombreEstudiante()+" Periodo "+this.getIndicadorGeneralSelected().getNroPeriodo());
        barModelFranjas.setLegendPosition("ne");
 
        Axis xAxis = barModelFranjas.getAxis(AxisType.X);
        xAxis.setLabel("Franjas");
 
        Axis yAxis = barModelFranjas.getAxis(AxisType.Y);
        yAxis.setLabel("Visitas");
        yAxis.setMin(0);
        yAxis.setMax(150);      
            
        
        return barModelFranjas;
    }

    public void setBarModelFranjas(BarChartModel barModelFranjas) {
        this.barModelFranjas = barModelFranjas;
    }
         
    
    
    
      public void limpiar(){
           System.out.println("Limpiar filtros usuario admin bean .");

          try {
              this.indicadorGeneralSelected.limpiar();
                            
        } catch (Exception ex) {
            Logger.getLogger(IndicadorGralConsolidalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }       

    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  IndicadorGeneral indicadorGeneral = new IndicadorGeneral();

  
  IndicadorGralConsolidalBean indGralBean = new IndicadorGralConsolidalBean();
  
   indGralBean.indicadorGeneralSelected.setAgno("2018");
  indGralBean.indicadorGeneralSelected.setNroPeriodo("50");

 // indGralBean.selectFilterSelected();
  
System.out.print("Tamaño de la lista xx ");

int x = 0;

x = Integer.parseInt(String.format( "%.0f", Float.parseFloat("7.000000")));

System.out.print("Direccion item x - "+x);
// System.out.print("Direccion item - "+String.format( "%.0f", Integer.parseInt("7.0000")));

}

   
    
}
