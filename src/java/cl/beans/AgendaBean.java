/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import cl.mybatis.myBatisUtil;
import cl.mybatis.pojos.Agenda;
import org.apache.ibatis.session.SqlSession;


import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author user1
 */
@ManagedBean(name="AgendaBean")
@ViewScoped
// @RequestScoped

public class AgendaBean implements Serializable  {

    private Agenda agenda;
    private List<Agenda> listAgendas; 
    private Agenda agendaSelected; 
    
    
    @PostConstruct
    public void init() {
        System.out.println(" INIT ----------------------------------------------------------------------");
         agenda = new Agenda();
         agendaSelected = new Agenda();
        
     }
     
    
    
    /**
     * Creates a new instance of UsuarioBean
     */
    
    public AgendaBean() {
//        agenda = new Agenda();
//        agendaSelected = new Agenda();
//        this.selectAll();
        
    }

    
    
    
    public Agenda getAgenda() {
        return agenda;
    }

        
    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Agenda getAgendaSelected() {
        return agendaSelected;
    }

    public void setAgendaSelected(Agenda agendaSelected) {
        System.out.println("----------------------------------------------------------------------");
        
        this.agendaSelected = agendaSelected;
    }

    
    
    
    
    public List<Agenda> getListAgendas() {
        return listAgendas;
    }

    public void aceptar() {
        
       CargueBean cargueBean = new CargueBean();

       
       if ( agendaSelected.getEstado().equalsIgnoreCase("Agendado") == true )
       {
            FacesMessage message = new FacesMessage("Advertencia...", " Este item ya esta agendado...");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
           
       }
       
       
       cargueBean.generaFormato(agendaSelected.getId(),agendaSelected.getAnalistaMail(),agendaSelected.getAnalistaCode() );
       agendaSelected.setEstado("Agendado");
       this.actualizar( agendaSelected );
        
       System.out.println("Aceptar.......  "+agendaSelected.getId()+ " UA  "+agendaSelected.getUa()+ " Analista Mail "+agendaSelected.getAnalistaMail());
//       System.out.println("Aceptar.......  .............");
        
    }
    
    public void cancelar() {
       System.out.println("Cancelar.......  ");
        
    }
    
    
    public void setListAgendas(List<Agenda> listAgendas) {
        this.listAgendas = listAgendas;
    }
    
    
    public void selectAll(){
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("Ingreso selet all agenda");
        
        if ( session != null ){
            try {
                
                listAgendas = session.selectList("Agenda.selectAll");     
                this.agendaSelected = listAgendas.get(0);
                System.out.println("Ver 02 - Tamaño de la lista "+listAgendas.size()+ " Nombre 1 "+ listAgendas.get(1).getUa() );
                
                
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
    }

    
    public void selectFilter(Agenda pAgenda) throws Exception {
        SqlSession session = new myBatisUtil().getSession();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Ingreso Select ID");
        
        if ( session != null ){
            try {
                
                listAgendas = session.selectList("Agenda.selectFilter", pAgenda);
                if ( listAgendas.size() > 0 ) {
                  System.out.println(" ------ > selectFilter - Tamaño de la lista "+listAgendas.size()+ " Nombre 1 "+ listAgendas.get(0).getUa()+" id secue "+ listAgendas.get(0).getId() );                    
                }
                
                this.agenda = listAgendas.get(0);
                
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
                session.insert("Agenda.insert", agenda);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Agenda Creado"));
        return "index";
    }
    
    
    public String actualizar( Agenda pAgenda ){
        SqlSession session = new myBatisUtil().getSession();
        if ( session != null ){
            try {
                session.update("Agenda.update", pAgenda);
                session.commit();
            } finally {
                session.close();
            }
        }
        else {
            System.out.println("Error al crear la sesion.");
        }
        
        // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Agenda Creado"));
        return "index";
    }
 
       public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((Agenda) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println("---------------------------------------------------------------------- ONrOWeDIT ");

    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Agenda) event.getObject()).getId());
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
    
    
public static void main(String arg[]) throws Exception {
  
    
  System.out.println("Modelo Perfil - TEST ");
  
  Agenda agenda = new Agenda();
  
  
  

  AgendaBean agendaBean = new AgendaBean();
  
  
  // agenda.setId("2");
  agenda.setEstado("PENDIENTE");
  
  agendaBean.selectFilter(agenda);
  
  
 agendaBean.selectAll();
 List<Agenda> list = agendaBean.getListAgendas();

//System.out.print("Tamaño de la lista xx "+list.size());
//System.out.print("Direccion item - "+list.get(0).getDireccion());
  

/*
  
  for ( int i = 0; i < list.size() ; i++  ) {
      
      System.out.println("Item "+ i+"  Nro Item "+list.get(i).getId()+" Nombre "+list.get(i).getUa());
      
  }
  list.get(2).setEstado("AGENDADO");
  list.get(2).setFchVisita("2016-03-04");

  agendaBean.actualizar(list.get(2));
  
  System.out.print("Fin del proceso......... ");
  
*/

}

   
    
}
