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
 * Proposito:
 * Para un estudiante acceder a la informacion de todos los indicadores desde el periodo 1 hasta 
 * el periodo n.
 * Esta consulta nos permite ver los indicadores el comportamiento del promedio para todos los
 * indicadores por un estudiante.
 * La idea es que en esta tablero de control podamos observar de manera grafica todos los indicadores.
 * Esta es la propuesta de la UNAD y Tadeo en donde con barras se indica el comportamiento de todos los
 * indicadores.
 * 
 * 
 */
@ManagedBean(name="EstudianteSemestreBean")
@ViewScoped
// @RequestScoped

public class EstudianteSemestreBean implements Serializable  {

    private IndicadorGeneral indicadorGeneral;
    private List<IndicadorGeneral> listIndicadorGeneral; 
    private IndicadorGeneral indicadorGeneralSelected; 

  
    private BarChartModel barModel;
    private BarChartModel barModelFranjas;
    
    private LineChartModel animatedMod_ind2_2;  // Logs x Estudiante en la semana.
    private BarChartModel  animatedMod_ind2_6;  // Logs Dias Habiles en la semana
    private BarChartModel  animatedMod_ind2_7;  // Logs Fin de Semana en la semana
    private BarChartModel  animatedMod_ind2_10;  // Logs IP Diferentes.
    private BarChartModel  animatedMod_ind2_11;  // Frecuencia de acceso
    private BarChartModel  animatedMod_ind2_5;  // Visitas al material del curso
    private BarChartModel  animatedMod_ind2_13;  // Numero sesiones promedio en la semana
    private BarChartModel  animatedMod_ind2_12;  // Interaccion con otros
    private BarChartModel  animatedMod_ind2_17;  // Eventos que modifica el sistema 
    private BarChartModel  animatedMod_ind2_3;  // Sesiones Promedio por semana

    private LineChartModel animatedMod_ind2_67;  // Compara 6 vs 7
    private LineChartModel animatedMod_ind2_fra;  // Compara Franjas semestre
    private LineChartModel animatedMod_ind2_sem;  // Compara Habiles Semestre
    
    
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
         
         createAnimatedModels();
         
         
         
         

    }
     
    
    
    /**
     * Creates a new instance of UsuarioBean
     */
    
    public EstudianteSemestreBean() {
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
                
                this.listIndicadorGeneral = session.selectList("IndicadorGralConsolida.selectFilterSemestre", this.indicadorGeneralSelected);
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
            Logger.getLogger(EstudianteSemestreBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      }       

// ---- Componentes Graficos  ----------------------------------------
// ---------------------- Init Modelos de las graficas Models -----------------------------------------------
      
    private LineChartModel initLineModelInd2_2() {
        LineChartModel model = new LineChartModel();
        
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                LineChartSeries series1 = new LineChartSeries();
                series1.setLabel("Logs Semana 1");
                series1.set(1, 0);
                series1.set(2, 0);
                series1.set(3, 0);
                series1.set(4, 0);
                series1.set(5, 0);

                LineChartSeries series2 = new LineChartSeries();
                series2.setLabel("Logs Semana 2");

                series2.set(1, 0);
                series2.set(2, 0);
                series2.set(3, 0);
                series2.set(4, 0);
                series2.set(5, 0);

                model.addSeries(series1);
                model.addSeries(series2);
        }
        else
        {
                LineChartSeries seriesInd2_2 = new LineChartSeries();
                seriesInd2_2.setLabel("Logs Semanal");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                int semana = 0;    
                semana = Integer.parseInt(this.listIndicadorGeneral.get(i).getNroPeriodo());
                seriesInd2_2.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_2())); 
                // seriesInd2_2.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_2())); 
                // serieInd2_6.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), i); 
                System.out.print("x Ind 2_2 Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Promedio "+this.listIndicadorGeneral.get(i).getPro_2_2());

                } 
               model.addSeries(seriesInd2_2);
                

        }    
        
        return model;
    }


    private LineChartModel initLineModelInd6_7() {
        LineChartModel model = new LineChartModel();
        
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                LineChartSeries series1 = new LineChartSeries();
                series1.setLabel("Logs Semana 1");
                series1.set(1, 0);
                series1.set(2, 0);
                series1.set(3, 0);
                series1.set(4, 0);
                series1.set(5, 0);


                LineChartSeries series2 = new LineChartSeries();
                series2.setLabel("Logs Semana 2");

                series2.set(1, 0);
                series2.set(2, 0);
                series2.set(3, 0);
                series2.set(4, 0);
                series2.set(5, 0);

                model.addSeries(series1);
                model.addSeries(series2);
        }
        else
        {
                LineChartSeries seriesInd6 = new LineChartSeries();
                LineChartSeries seriesInd7 = new LineChartSeries();
                seriesInd6.setLabel("Dia Habil");
                seriesInd7.setLabel("Fin Semana");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                    
                int semana = 0;    
                semana = Integer.parseInt(this.listIndicadorGeneral.get(i).getNroPeriodo());
                    
                seriesInd6.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_6())); 
                seriesInd7.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_7())); 
                // seriesInd2_2.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_2())); 
                // serieInd2_6.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), i); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());

                } 
               model.addSeries(seriesInd6);
               model.addSeries(seriesInd7);
                

        }    
        
        return model;
    }


    // Comparacion de las franjas en el semestre 
    private LineChartModel initLineModelInd2_fra() {
        LineChartModel model = new LineChartModel();
        
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                LineChartSeries series1 = new LineChartSeries();
                series1.setLabel("Franjas 1");
                series1.set(1, 0);
                series1.set(2, 0);
                series1.set(3, 0);
                series1.set(4, 0);
                series1.set(5, 0);

                LineChartSeries series2 = new LineChartSeries();
                series2.setLabel("Frnjas 2");

                series2.set(1, 0);
                series2.set(2, 0);
                series2.set(3, 0);
                series2.set(4, 0);
                series2.set(5, 0);

                model.addSeries(series1);
                model.addSeries(series2);
        }
        else
        {
                LineChartSeries seriesInd2F1 = new LineChartSeries();
                LineChartSeries seriesInd2F2 = new LineChartSeries();
                LineChartSeries seriesInd2F3 = new LineChartSeries();
                LineChartSeries seriesInd2F4 = new LineChartSeries();
                seriesInd2F1.setLabel("Franja 0 - 6");
                seriesInd2F2.setLabel("Franja 6 - 12");
                seriesInd2F3.setLabel("Franja 12 -18");
                seriesInd2F4.setLabel("Franja 18 - 24");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                    
                int semana = 0;    
                semana = Integer.parseInt(this.listIndicadorGeneral.get(i).getNroPeriodo());
                    
                seriesInd2F1.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_8_F0())); 
                seriesInd2F2.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_8_F6())); 
                seriesInd2F3.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_8_F12())); 
                seriesInd2F4.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_8_F18())); 
                // seriesInd2_2.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_2())); 
                // serieInd2_6.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), i); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());

                } 
               model.addSeries(seriesInd2F1);
               model.addSeries(seriesInd2F2);
               model.addSeries(seriesInd2F3);
               model.addSeries(seriesInd2F4);
                

        }    
        
        return model;
    }


    // Comparacion de los dias habieles durante el semestre
    
    private LineChartModel initLineModelInd2_sem() {
        LineChartModel model = new LineChartModel();
        
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                LineChartSeries series1 = new LineChartSeries();
                series1.setLabel("Lunes");
                series1.set(1, 0);
                series1.set(2, 0);
                series1.set(3, 0);
                series1.set(4, 0);
                series1.set(5, 0);

                LineChartSeries series2 = new LineChartSeries();
                series2.setLabel("Martes");

                series2.set(1, 0);
                series2.set(2, 0);
                series2.set(3, 0);
                series2.set(4, 0);
                series2.set(5, 0);

                model.addSeries(series1);
                model.addSeries(series2);
        }
        else
        {
                LineChartSeries seriesInd2Lun = new LineChartSeries();
                LineChartSeries seriesInd2Mar = new LineChartSeries();
                LineChartSeries seriesInd2Mie = new LineChartSeries();
                LineChartSeries seriesInd2Jue = new LineChartSeries();
                LineChartSeries seriesInd2Vie = new LineChartSeries();
               // LineChartSeries seriesInd2Sab = new LineChartSeries();
               // LineChartSeries seriesInd2Dom = new LineChartSeries();
                seriesInd2Lun.setLabel("Lunes");
                seriesInd2Mar.setLabel("Martes");
                seriesInd2Mie.setLabel("Miercoles");
                seriesInd2Jue.setLabel("Jueves");
                seriesInd2Vie.setLabel("Viernes");
                //seriesInd2Sab.setLabel("Sabado");
                //seriesInd2Dom.setLabel("Domingo");
                
                
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                    
                int semana = 0;    
                semana = Integer.parseInt(this.listIndicadorGeneral.get(i).getNroPeriodo());
    
                    
                seriesInd2Lun.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_15_LUN())); 
                seriesInd2Mar.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_15_MAR())); 
                seriesInd2Mie.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_15_MIE())); 
                seriesInd2Jue.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_15_JUE())); 
                seriesInd2Vie.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_15_VIE())); 
                //seriesInd2Sab.set(i, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_15_SAB())); 
                //seriesInd2Dom.set(i, Double.parseDouble(this.listIndicadorGeneral.get(i).getInd_2_15_DOM())); 
                // seriesInd2_2.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_2())); 
                // serieInd2_6.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), i); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());

                } 
               model.addSeries(seriesInd2Lun);
               model.addSeries(seriesInd2Mar);
               model.addSeries(seriesInd2Mie);
               model.addSeries(seriesInd2Jue);
               model.addSeries(seriesInd2Vie);
               //model.addSeries(seriesInd2Sab);
               //model.addSeries(seriesInd2Dom);
                

        }    
        
        return model;
    }

















    
    private BarChartModel initBarModelInd2_6() {
        BarChartModel model = new BarChartModel();
 
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) {

            // Valores por default 
            
                ChartSeries boys = new ChartSeries();
                boys.setLabel("Boys");
                boys.set("2004", 0);
                boys.set("2005", 0);
                boys.set("2006", 0);
                boys.set("2007", 0);
                boys.set("2008", 0);

                ChartSeries girls = new ChartSeries();
                girls.setLabel("Girls");
                girls.set("2004", 0);
                girls.set("2005", 0);
                girls.set("2006", 0);
                girls.set("2007", 0);
                girls.set("2008", 0);
           
                model.addSeries(boys);
                model.addSeries(girls);
            
        }
        else
        {
            // Tomar la informacion directamente de la base de datos - Tomar el Valor Mayor en el ciclo.
                ChartSeries serieInd2_6 = new ChartSeries();
                serieInd2_6.setLabel("2.6. Nro. Logs x Estudiente-Habiles - Semestre");
                
            System.out.print("------------------- Colocar valores en la grafica ----------------------------- Tamaño de la lista  " + this.listIndicadorGeneral.size() );    
            for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
            {
                int semana = 0;    
                semana = Integer.parseInt(this.listIndicadorGeneral.get(i).getNroPeriodo());

                serieInd2_6.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_6())); 
                // serieInd2_6.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), i); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_6());

            } 
           model.addSeries(serieInd2_6);
        }    
        return model;
    }
    
private BarChartModel initBarModelInd2_7() {
        BarChartModel model = new BarChartModel();
 
            System.out.print("----initBarModelInd2_7  Colocar valores en la grafica -----------------------------  "  );    
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) {

            // Valores por default 
            
                ChartSeries boys = new ChartSeries();
                boys.setLabel("Boys");
                
                
                boys.set("2004", 0);
                boys.set("2005", 0);
                boys.set("2006", 0);
                boys.set("2007", 0);
                boys.set("2008", 0);

                ChartSeries girls = new ChartSeries();
                girls.setLabel("Girls");
                girls.set("2004", 0);
                girls.set("2005", 0);
                girls.set("2006", 0);
                girls.set("2007", 0);
                girls.set("2008", 0);
           
                model.addSeries(boys);
                model.addSeries(girls);
            
        }
        else
        {
            // Tomar la informacion directamente de la base de datos - Tomar el Valor Mayor en el ciclo.
                ChartSeries serieInd2_7 = new ChartSeries();
                serieInd2_7.setLabel("2.7. Nro. Logs x Fin Semana - Semestre");
                
            System.out.print("------------------- Colocar valores en la grafica ----------------------------- Tamaño de la lista  " + this.listIndicadorGeneral.size() );    
            for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
            {
                serieInd2_7.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_7())); 
                // serieInd2_6.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), i); 
                //System.out.print("2.7 ---- Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_6());

            } 
            
           model.addSeries(serieInd2_7);
        }    
        return model;
    }
      
    private BarChartModel initBarModelInd2_10() {
        BarChartModel model = new BarChartModel();
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                ChartSeries boys = new ChartSeries();
                boys.setLabel("Boys");
                boys.set("2004", 0);
                boys.set("2005", 0);
                boys.set("2006", 0);
                boys.set("2007", 0);
                boys.set("2008", 0);

                ChartSeries girls = new ChartSeries();
                girls.setLabel("Girls");
                girls.set("2004", 0);
                girls.set("2005", 0);
                girls.set("2006", 0);
                girls.set("2007", 0);
                girls.set("2008", 0);           
                model.addSeries(boys);
                model.addSeries(girls);
        }
        else
        {
                ChartSeries serieInd2_10 = new ChartSeries();
                serieInd2_10.setLabel("2.10. IP Diferentes Semana - Semestre");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                serieInd2_10.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_10())); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());
                } 
               model.addSeries(serieInd2_10);               
        }            
        return model;
    }

    private BarChartModel initBarModelInd2_11() {
        BarChartModel model = new BarChartModel();
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                ChartSeries boys = new ChartSeries();
                boys.setLabel("Boys");
                boys.set("2004", 0);
                boys.set("2005", 0);
                boys.set("2006", 0);
                boys.set("2007", 0);
                boys.set("2008", 0);

                ChartSeries girls = new ChartSeries();
                girls.setLabel("Girls");
                girls.set("2004", 0);
                girls.set("2005", 0);
                girls.set("2006", 0);
                girls.set("2007", 0);
                girls.set("2008", 0);           
                model.addSeries(boys);
                model.addSeries(girls);
        }
        else
        {
                ChartSeries serieInd2 = new ChartSeries();
                serieInd2.setLabel("2.11. Frecuencia de Acceso Semana - Semestre");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                serieInd2.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_11())); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());
                } 
               model.addSeries(serieInd2);               
        }            
        return model;
    }

    private BarChartModel initBarModelInd2_5() {
        BarChartModel model = new BarChartModel();
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                ChartSeries boys = new ChartSeries();
                boys.setLabel("Boys");
                boys.set("2004", 0);
                boys.set("2005", 0);
                boys.set("2006", 0);
                boys.set("2007", 0);
                boys.set("2008", 0);

                ChartSeries girls = new ChartSeries();
                girls.setLabel("Girls");
                girls.set("2004", 0);
                girls.set("2005", 0);
                girls.set("2006", 0);
                girls.set("2007", 0);
                girls.set("2008", 0);           
                model.addSeries(boys);
                model.addSeries(girls);
        }
        else
        {
                ChartSeries serieInd2 = new ChartSeries();
                serieInd2.setLabel("2.5. Visitas Material Curso Semana - Semestre");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                serieInd2.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_5())); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());
                } 
               model.addSeries(serieInd2);               
        }            
        return model;
    }

    private BarChartModel initBarModelInd2_13() {
        BarChartModel model = new BarChartModel();
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                ChartSeries boys = new ChartSeries();
                boys.setLabel("Boys");
                boys.set("2004", 0);
                boys.set("2005", 0);
                boys.set("2006", 0);
                boys.set("2007", 0);
                boys.set("2008", 0);

                ChartSeries girls = new ChartSeries();
                girls.setLabel("Girls");
                girls.set("2004", 0);
                girls.set("2005", 0);
                girls.set("2006", 0);
                girls.set("2007", 0);
                girls.set("2008", 0);           
                model.addSeries(boys);
                model.addSeries(girls);
        }
        else
        {
                ChartSeries serieInd2 = new ChartSeries();
                serieInd2.setLabel("2.13. Nro Sesiones Promedio Semana - Semestre");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                serieInd2.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_13())); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());
                } 
               model.addSeries(serieInd2);               
        }            
        return model;
    }
    

    private BarChartModel initBarModelInd2_12() {
        BarChartModel model = new BarChartModel();
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                ChartSeries boys = new ChartSeries();
                boys.setLabel("Boys");
                boys.set("2004", 0);
                boys.set("2005", 0);
                boys.set("2006", 0);
                boys.set("2007", 0);
                boys.set("2008", 0);

                ChartSeries girls = new ChartSeries();
                girls.setLabel("Girls");
                girls.set("2004", 0);
                girls.set("2005", 0);
                girls.set("2006", 0);
                girls.set("2007", 0);
                girls.set("2008", 0);           
                model.addSeries(boys);
                model.addSeries(girls);
        }
        else
        {
                ChartSeries serieInd2 = new ChartSeries();
                serieInd2.setLabel("2.12. Interaccion con Otros Semana - Semestre");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                serieInd2.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_12())); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());
                } 
               model.addSeries(serieInd2);               
        }            
        return model;
    }
    

    private BarChartModel initBarModelInd2_17() {
        BarChartModel model = new BarChartModel();
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                ChartSeries boys = new ChartSeries();
                boys.setLabel("Boys");
                boys.set("2004", 0);
                boys.set("2005", 0);
                boys.set("2006", 0);
                boys.set("2007", 0);
                boys.set("2008", 0);

                ChartSeries girls = new ChartSeries();
                girls.setLabel("Girls");
                girls.set("2004", 0);
                girls.set("2005", 0);
                girls.set("2006", 0);
                girls.set("2007", 0);
                girls.set("2008", 0);           
                model.addSeries(boys);
                model.addSeries(girls);
        }
        else
        {
                ChartSeries serieInd2 = new ChartSeries();
                serieInd2.setLabel("2.17. Eventos Modifica Sistema Semana - Semestre");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                serieInd2.set(this.listIndicadorGeneral.get(i).getNroPeriodo(), Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_17())); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());
                } 
               model.addSeries(serieInd2);               
        }            
        return model;
    }

    private BarChartModel initBarModelInd2_3() {
        BarChartModel model = new BarChartModel();
        
        if ( this.listIndicadorGeneral == null || this.listIndicadorGeneral.size() < 1 ) 
        {
                ChartSeries boys = new ChartSeries();
                //boys.setLabel("Boys");
                boys.set("2004", 0);
                boys.set("2005", 0);
                boys.set("2006", 0);
                boys.set("2007", 0);
                boys.set("2008", 0);

                ChartSeries girls = new ChartSeries();
                //girls.setLabel("Girls");
                girls.set("2004", 0);
                girls.set("2005", 0);
                girls.set("2006", 0);
                girls.set("2007", 0);
                girls.set("2008", 0);           
                model.addSeries(boys);
                model.addSeries(girls);
        }
        else
        {
                ChartSeries serieInd2 = new ChartSeries();
                serieInd2.setLabel("2.3. Sesion Tiempo Promedio por Semana - Semestre");
                for ( int i = 0; i < this.listIndicadorGeneral.size()  ; i++ )
                {
                    
                int semana = 0;    
                semana = Integer.parseInt(this.listIndicadorGeneral.get(i).getNroPeriodo());
   
                serieInd2.set(semana, Double.parseDouble(this.listIndicadorGeneral.get(i).getPro_2_3_TIME())); 
               // System.out.print("Valor por periodo Nro Periodo  "+this.listIndicadorGeneral.get(i).getNroPeriodo()+" Indice "+i+"  Valor Periodo "+this.listIndicadorGeneral.get(i).getInd_2_2());
                } 
                serieInd2.setLabel("Semanas");
               model.addSeries(serieInd2);               
        }            
        return model;
    }
    
// ---------------------- Create Models -----------------------------------------------
    private void createAnimatedModels() {
        
        
        
        this.animatedMod_ind2_2 = initLineModelInd2_2();
        this.animatedMod_ind2_2.setTitle("Title - 2.2. Nro. Logs Semanal - Semestre X1");
        this.animatedMod_ind2_2.setAnimate(true);
        this.animatedMod_ind2_2.setLegendPosition("se");
        Axis yAxis = this.animatedMod_ind2_2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Labellllll");
      //  yAxis.setMax(600);

        this.animatedMod_ind2_fra = initLineModelInd2_fra();
        this.animatedMod_ind2_fra.setTitle("Comparativo Franja Horaria");
        this.animatedMod_ind2_fra.setAnimate(true);
        this.animatedMod_ind2_fra.setLegendPosition("se");
        yAxis = this.animatedMod_ind2_fra.getAxis(AxisType.Y);
        yAxis.setMin(0);
    //    yAxis.setMax(150);

        this.animatedMod_ind2_sem = initLineModelInd2_sem();
        this.animatedMod_ind2_sem.setTitle("Comparativo Dias de la Semana");
        this.animatedMod_ind2_sem.setAnimate(true);
        this.animatedMod_ind2_sem.setLegendPosition("se");
        yAxis = this.animatedMod_ind2_sem.getAxis(AxisType.Y);
        yAxis.setMin(0);
     //   yAxis.setMax(150);


        this.animatedMod_ind2_67 = initLineModelInd6_7();
        this.animatedMod_ind2_67.setTitle("Title Compara Semana Vs Fin de Semana");
        this.animatedMod_ind2_67.setAnimate(true);
        this.animatedMod_ind2_67.setLegendPosition("se");
        yAxis = this.animatedMod_ind2_67.getAxis(AxisType.Y);
        yAxis.setMin(0);
   //     yAxis.setMax(150);


        
        this.animatedMod_ind2_6 = initBarModelInd2_6();
        this.animatedMod_ind2_6.setTitle("Title - 2.6. Nro. Logs Dias Habiles- Semestre X1");
        this.animatedMod_ind2_6.setAnimate(true);
        this.animatedMod_ind2_6.setLegendPosition("ne");
        yAxis = this.animatedMod_ind2_6.getAxis(AxisType.Y);
        yAxis.setMin(0);
     //   yAxis.setMax(150);
        

        this.animatedMod_ind2_7 = initBarModelInd2_7();
        this.animatedMod_ind2_7.setTitle("Title - 2.7. Nro. Logs Dias Fin Semana- Semestre X1");
        this.animatedMod_ind2_7.setAnimate(true);
        this.animatedMod_ind2_7.setLegendPosition("ne");
        yAxis = this.animatedMod_ind2_7.getAxis(AxisType.Y);
        yAxis.setMin(0);
     //   yAxis.setMax(150);

        this.animatedMod_ind2_10 = initBarModelInd2_10();
        this.animatedMod_ind2_10.setTitle("Title - 2.10. IP Diferentes Semana- Semestre X1");
        this.animatedMod_ind2_10.setAnimate(true);
        this.animatedMod_ind2_10.setLegendPosition("ne");
        yAxis = this.animatedMod_ind2_10.getAxis(AxisType.Y);
        yAxis.setMin(0);
 //       yAxis.setMax(150);

        this.animatedMod_ind2_11 = initBarModelInd2_11();
        this.animatedMod_ind2_11.setTitle("Title - 2.11. Frecuencia Acceso Semana- Semestre X1");
        this.animatedMod_ind2_11.setAnimate(true);
        this.animatedMod_ind2_11.setLegendPosition("ne");
        yAxis = this.animatedMod_ind2_11.getAxis(AxisType.Y);
        yAxis.setMin(0);
  //      yAxis.setMax(150);

        this.animatedMod_ind2_5 = initBarModelInd2_5();
        this.animatedMod_ind2_5.setTitle("Title - 2.5. Visitas al material del curso Semana- Semestre X1");
        this.animatedMod_ind2_5.setAnimate(true);
        this.animatedMod_ind2_5.setLegendPosition("ne");
        yAxis = this.animatedMod_ind2_5.getAxis(AxisType.Y);
        yAxis.setMin(0);
  //      yAxis.setMax(150);

        this.animatedMod_ind2_13 = initBarModelInd2_13();
        this.animatedMod_ind2_13.setTitle("Title - 2.13. Numero sesiones promedio Semana- Semestre X1");
        this.animatedMod_ind2_13.setAnimate(true);
        this.animatedMod_ind2_13.setLegendPosition("ne");
        yAxis = this.animatedMod_ind2_13.getAxis(AxisType.Y);
        yAxis.setMin(0);
    //    yAxis.setMax(150);

        this.animatedMod_ind2_12 = initBarModelInd2_12();
        this.animatedMod_ind2_12.setTitle("Title - 2.12. Interaccion con otros Semana- Semestre X1");
        this.animatedMod_ind2_12.setAnimate(true);
        this.animatedMod_ind2_12.setLegendPosition("ne");
        yAxis = this.animatedMod_ind2_12.getAxis(AxisType.Y);
        yAxis.setMin(0);
   //     yAxis.setMax(150);


        this.animatedMod_ind2_17 = initBarModelInd2_17();
        this.animatedMod_ind2_17.setTitle("Title - 2.17. Eventos Modifica Sistema Semana- Semestre X1");
        this.animatedMod_ind2_17.setAnimate(true);
        this.animatedMod_ind2_17.setLegendPosition("ne");
        yAxis = this.animatedMod_ind2_17.getAxis(AxisType.Y);
        yAxis.setMin(0);
  //      yAxis.setMax(150);

        this.animatedMod_ind2_3 = initBarModelInd2_3();
        this.animatedMod_ind2_3.setTitle("Title - 2.3. Sesion Semana Tiempo - Semestre X1");
        this.animatedMod_ind2_3.setAnimate(true);
        this.animatedMod_ind2_3.setLegendPosition("ne");
        yAxis = this.animatedMod_ind2_3.getAxis(AxisType.Y);
        yAxis.setMin(0);
  //      yAxis.setMax(200);
        
    }

    public LineChartModel getAnimatedMod_ind2_2() {


        System.out.println("--------------------------------- getAnimatedMod_ind2_2 ------------------------------------- ");
        
        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_2 = initLineModelInd2_2();
        this.animatedMod_ind2_2.setTitle("2.2. Número de LOGS generados por el estudiante en la semana.{Nl}(14) "+ nameEstudiante );
        this.animatedMod_ind2_2.setAnimate(true);
        this.animatedMod_ind2_2.setLegendPosition("ne");
        this.animatedMod_ind2_2.setSeriesColors("58BA27,F52F2F");
        
        
        Axis yAxis = this.animatedMod_ind2_2.getAxis(AxisType.Y);
        yAxis.setMin(0);
    //    yAxis.setMax(150);
                
        
        return animatedMod_ind2_2;
    }

    public void setAnimatedMod_ind2_2(LineChartModel animatedMod_ind2_2) {
        this.animatedMod_ind2_2 = animatedMod_ind2_2;
    }

    public LineChartModel getAnimatedMod_ind2_fra() {
        System.out.println("--------------------------------- getAnimatedMod_ind2_fra ------------------------------------- ");
        
        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_fra = initLineModelInd2_fra();
        this.animatedMod_ind2_fra.setTitle("Comparativo Franjas Horarias "+ nameEstudiante );
        this.animatedMod_ind2_fra.setAnimate(true);
        this.animatedMod_ind2_fra.setLegendPosition("ne");
        // this.animatedMod_ind2_fra.setSeriesColors("58BA27,F52F2F");
        
        
        Axis yAxis = this.animatedMod_ind2_fra.getAxis(AxisType.Y);
        yAxis.setMin(0);
       // yAxis.setMax(200);
        
        return animatedMod_ind2_fra;
    }

    public void setAnimatedMod_ind2_fra(LineChartModel animatedMod_ind2_fra) {
        this.animatedMod_ind2_fra = animatedMod_ind2_fra;
    }

    public LineChartModel getAnimatedMod_ind2_sem() {
        
        System.out.println("--------------------------------- getAnimatedMod_ind2_sem ------------------------------------- ");
        
        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_sem = initLineModelInd2_sem();
        this.animatedMod_ind2_sem.setTitle("Comparativo Dias de la Semana "+ nameEstudiante );
        this.animatedMod_ind2_sem.setAnimate(true);
        this.animatedMod_ind2_sem.setLegendPosition("ne");
        // this.animatedMod_ind2_fra.setSeriesColors("58BA27,F52F2F");
        
        
        Axis yAxis = this.animatedMod_ind2_sem.getAxis(AxisType.Y);
        yAxis.setMin(0);
       // yAxis.setMax(200);
        
        return animatedMod_ind2_sem;
    }

    public void setAnimatedMod_ind2_sem(LineChartModel animatedMod_ind2_sem) {
        this.animatedMod_ind2_sem = animatedMod_ind2_sem;
    }

    
    
    
    
    
    
    
    
    
    public BarChartModel getAnimatedMod_ind2_6() {
                            
          System.out.println("--------------------------------- getAnimatedMod_ind2_6 ------------------------------------- ");
        
        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_6 = initBarModelInd2_6();
        this.animatedMod_ind2_6.setTitle("2.6 Número de logs generados en los días habiles de la semana x Estudiante {Ls} (16)"+ nameEstudiante );
        this.animatedMod_ind2_6.setAnimate(true);
        this.animatedMod_ind2_6.setLegendPosition("ne");
        Axis yAxis = this.animatedMod_ind2_6.getAxis(AxisType.Y);
        yAxis.setMin(0);
    //    yAxis.setMax(100);
        
        
        return animatedMod_ind2_6;
    }

    public void setAnimatedMod_ind2_6(BarChartModel animatedMod_ind2_6) {
        this.animatedMod_ind2_6 = animatedMod_ind2_6;
    }

    public BarChartModel getAnimatedMod_ind2_7() {
        
        System.out.println("--------------------------------- getAnimatedMod_ind2_7 ------------------------------------- ");
        
        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_7 = initBarModelInd2_7();
        this.animatedMod_ind2_7.setTitle("2.7 Número de logs generados en los fines de semana por el Estudiante {Lf}(18)"+ nameEstudiante );
        this.animatedMod_ind2_7.setAnimate(true);
        this.animatedMod_ind2_7.setLegendPosition("ne");
        Axis yAxis = this.animatedMod_ind2_7.getAxis(AxisType.Y);
        yAxis.setMin(0);
      //  yAxis.setMax(40);
        
        return animatedMod_ind2_7;
    }

    public void setAnimatedMod_ind2_7(BarChartModel animatedMod_ind2_7) {
        this.animatedMod_ind2_7 = animatedMod_ind2_7;
    }

    public BarChartModel getAnimatedMod_ind2_10() {
        
            String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_10 = initBarModelInd2_10();
        this.animatedMod_ind2_10.setTitle("2.10. Radio IP (Pa). IP Diferentes Conexion.IP diferentes de conexion en la semana por estudiante."+ nameEstudiante );
        this.animatedMod_ind2_10.setAnimate(true);
        this.animatedMod_ind2_10.setLegendPosition("ne");
        Axis yAxis = this.animatedMod_ind2_10.getAxis(AxisType.Y);
        yAxis.setMin(0);
    //    yAxis.setMax(20);
        

        return animatedMod_ind2_10;
    }

    public void setAnimatedMod_ind2_10(BarChartModel animatedMod_ind2_10) {
        this.animatedMod_ind2_10 = animatedMod_ind2_10;
    }

    public BarChartModel getAnimatedMod_ind2_11() {

        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_11 = initBarModelInd2_11();
        this.animatedMod_ind2_11.setTitle("2.11. Access frequency. "+ nameEstudiante );
        this.animatedMod_ind2_11.setAnimate(true);
        this.animatedMod_ind2_11.setLegendPosition("ne");
        Axis yAxis = this.animatedMod_ind2_11.getAxis(AxisType.Y);
        yAxis.setMin(0);
     //   yAxis.setMax(30);

        return animatedMod_ind2_11;
    }

    public void setAnimatedMod_ind2_11(BarChartModel animatedMod_ind2_11) {
        this.animatedMod_ind2_11 = animatedMod_ind2_11;
    }

    public BarChartModel getAnimatedMod_ind2_5() {

        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_5 = initBarModelInd2_5();
        this.animatedMod_ind2_5.setTitle("2.5 Número de visitas a los materiales del curso {Vm} por el estudiante en la semana."+ nameEstudiante );
        this.animatedMod_ind2_5.setAnimate(true);
        this.animatedMod_ind2_5.setLegendPosition("ne");
        Axis yAxis = this.animatedMod_ind2_5.getAxis(AxisType.Y);
        yAxis.setMin(0);
    //    yAxis.setMax(30);

        return animatedMod_ind2_5;
    }

    public void setAnimatedMod_ind2_5(BarChartModel animatedMod_ind2_5) {
        this.animatedMod_ind2_5 = animatedMod_ind2_5;
    }

    public BarChartModel getAnimatedMod_ind2_13() {

        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_13 = initBarModelInd2_13();
        this.animatedMod_ind2_13.setTitle("2.13. Número de sesiones promedio por semana. Ingresos en la semana por el estudiante.(loggedin)"+ nameEstudiante );
        this.animatedMod_ind2_13.setAnimate(true);
        this.animatedMod_ind2_13.setLegendPosition("ne");
        Axis yAxis = this.animatedMod_ind2_13.getAxis(AxisType.Y);
        yAxis.setMin(0);
    //    yAxis.setMax(40);

        return animatedMod_ind2_13;
    }

    public void setAnimatedMod_ind2_13(BarChartModel animatedMod_ind2_13) {
        this.animatedMod_ind2_13 = animatedMod_ind2_13;
    }

    public BarChartModel getAnimatedMod_ind2_12() {

        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_12 = initBarModelInd2_12();
        this.animatedMod_ind2_12.setTitle("2.12 Número de interacciones promedio semanal con otros miembros del curso."+ nameEstudiante );
        this.animatedMod_ind2_12.setAnimate(true);
        this.animatedMod_ind2_12.setLegendPosition("ne");
        Axis yAxis = this.animatedMod_ind2_12.getAxis(AxisType.Y);
        yAxis.setMin(0);
    //    yAxis.setMax(30);

        return animatedMod_ind2_12;
    }

    public void setAnimatedMod_ind2_12(BarChartModel animatedMod_ind2_12) {
        this.animatedMod_ind2_12 = animatedMod_ind2_12;
    }

    public BarChartModel getAnimatedMod_ind2_17() {

        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_17 = initBarModelInd2_17();
        this.animatedMod_ind2_17.setTitle("2.17. Número de eventos en que modifica el sistema el estudiante por semana."+ nameEstudiante );
        this.animatedMod_ind2_17.setAnimate(true);
        this.animatedMod_ind2_17.setLegendPosition("ne");
        Axis yAxis = this.animatedMod_ind2_17.getAxis(AxisType.Y);
        yAxis.setMin(0);
    //    yAxis.setMax(50);


        return animatedMod_ind2_17;
    }

    public void setAnimatedMod_ind2_17(BarChartModel animatedMod_ind2_17) {
        this.animatedMod_ind2_17 = animatedMod_ind2_17;
    }

    public BarChartModel getAnimatedMod_ind2_3() {

        String nameEstudiante = "";
        
        if ( this.getListIndicadorGeneral() == null || this.getListIndicadorGeneral().size() < 1 ) nameEstudiante = " Vacio"; 
        else nameEstudiante = this.getListIndicadorGeneral().get(0).getNombreEstudiante();
        
        this.animatedMod_ind2_3 = initBarModelInd2_3();
        this.animatedMod_ind2_3.setTitle("2.3 Tiempo Total gastado por estudiante en el CMS en la semana.{TP}"+ nameEstudiante );
        this.animatedMod_ind2_3.setAnimate(true);
        this.animatedMod_ind2_3.setLegendPosition("ne");
        Axis yAxis = this.animatedMod_ind2_3.getAxis(AxisType.Y);
        yAxis.setMin(0);
     //   yAxis.setMax(600);



        return animatedMod_ind2_3;
    }

    public void setAnimatedMod_ind2_3(BarChartModel animatedMod_ind2_3) {
        this.animatedMod_ind2_3 = animatedMod_ind2_3;
    }

    public LineChartModel getAnimatedMod_ind2_67() {
        
        this.animatedMod_ind2_67 = initLineModelInd6_7();
        this.animatedMod_ind2_67.setTitle("Compara ingreso Dias Habiles Vs No Habiles");
        this.animatedMod_ind2_67.setAnimate(true);
        this.animatedMod_ind2_67.setLegendPosition("se");
        this.animatedMod_ind2_67.setSeriesColors("58BA27,A30303");

        Axis yAxis = this.animatedMod_ind2_67.getAxis(AxisType.Y);
        yAxis.setMin(0);
   //     yAxis.setMax(150);
        
        
        
        
        
        return animatedMod_ind2_67;
    }

    public void setAnimatedMod_ind2_67(LineChartModel animatedMod_ind2_67) {
        
        this.animatedMod_ind2_67 = animatedMod_ind2_67;
    }

   

 
    
      
    
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  IndicadorGeneral indicadorGeneral = new IndicadorGeneral();

  
  EstudianteSemestreBean indGralBean = new EstudianteSemestreBean();
  
   indGralBean.indicadorGeneralSelected.setAgno("2018");
   indGralBean.indicadorGeneralSelected.setNroPeriodo("50");
   indGralBean.indicadorGeneralSelected.setCodeEstudiante("166");
 
   indGralBean.selectFilterSelected();
  
System.out.print("Tamaño de la lista xx "+indGralBean.getListIndicadorGeneral().size());

int x = 0;

//x = Integer.parseInt(String.format( "%.0f", Float.parseFloat("7.000000")));

//System.out.print("Direccion item x - "+x);
// System.out.print("Direccion item - "+String.format( "%.0f", Integer.parseInt("7.0000")));

}

   
    
}
