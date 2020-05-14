package cl.beans;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean
public class FileUploadView {
   private String destination="C:\\ICBF\\";           
           
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            try {
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                System.out.println("nombre del archivo cargado "   );
                copyFile(file.getFileName(), file.getInputstream()  );
            } catch (IOException ex) {
                Logger.getLogger(FileUploadView.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR AL SALVAR ARCHIVO EN DISCO  "+ file.getFileName()   );
                
            }
        }
    }
    
  public void copyFile(String fileName, InputStream in) {
           try {
             
             
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination + fileName));
             
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
             
                in.close();
                out.flush();
                out.close();
             
                System.out.println("New file created!.... "+destination + fileName);
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    
    
    
}
