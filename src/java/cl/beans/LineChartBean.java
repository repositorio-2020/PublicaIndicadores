
package cl.beans;


import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;


@ManagedBean
@ViewScoped
public class LineChartBean implements Serializable {
    private LineChartModel lineModel;
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;

    @PostConstruct
    public void init() {

        createBarModels();
/*
        lineModel = new LineChartModel();
        LineChartSeries s = new LineChartSeries();
        s.setLabel("Population");

        s.set(1, 5.20);
        s.set(2, 19.63);
        s.set(3, 59.01);
        s.set(4, 139.76);
        s.set(5, 300.4);
        s.set(6, 630);

        lineModel.addSeries(s);
        lineModel.setLegendPosition("e");
        Axis y = lineModel.getAxis(AxisType.Y);
        y.setMin(0.5);
        y.setMax(700);
        y.setLabel("Millions");

        Axis x = lineModel.getAxis(AxisType.X);
        x.setMin(0);
        x.setMax(7);
        x.setTickInterval("1");
        x.setLabel("Number of Years");
*/
    }

    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Dias No Habil");
        boys.set("Lunes", 0);
        boys.set("Martes", 0);
        boys.set("Miercoles", 0);
        boys.set("Jueves", 0);
        boys.set("Viernes", 0);
        boys.set("Sabado", 220);
        boys.set("Domingo", 120);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Dias Habil");
        girls.set("Lunes", 52);
        girls.set("Martes", 60);
        girls.set("Miercoles", 110);
        girls.set("Jueves", 135);
        girls.set("Viernes", 120);
        girls.set("Sabado", 0);
        girls.set("Domingo", 0);
 
        model.addSeries(boys);
        model.addSeries(girls);
 
        return model;
    }
    
    
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }
 
    private void createBarModel() {
        barModel = initBarModel();
 
        barModel.setTitle("Logs Diario Estudiante");
        barModel.setLegendPosition("ne");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Dias Semana");
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Visitas");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
 
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 50);
        boys.set("2005", 96);
        boys.set("2006", 44);
        boys.set("2007", 55);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 82);
        girls.set("2007", 35);
        girls.set("2008", 120);
 
        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);
 
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
 
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        xAxis.setMin(0);
        xAxis.setMax(200);
 
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");
    }
    
    
    
    public LineChartModel getLineModel() {
        System.out.println(" Mode line .................. ");
        return lineModel;
    }
    
    public BarChartModel getBarModel() {
        System.out.println(" Mode bAR .................. ");
        return barModel;
    }
 
 /**
  * Instrucciones para llamar la grafica en el html
  
  *     <h2>PrimeFaces Linear Chart Example</h2>
    <p:chart type="line" model="#{lineChartBean.lineModel}" style="height:400px;width:600px"/>
<br /><br />

  * 
  * 
  * 
  */   
    
    
}
