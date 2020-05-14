/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;


import org.primefaces.model.StreamedContent;


public class PictureBean {

    private StreamedContent myImage;

    public PictureBean() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("C:\\ICBF\\demo681028.jpg");
        myImage = new DefaultStreamedContent(inputStream, "image/jpg");
    }

    public StreamedContent getMyImage() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\ICBF\\demo681028.jpg");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PictureBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        myImage = new DefaultStreamedContent(inputStream, "image/jpg");        
        return myImage;
    }

    public void setMyImage(StreamedContent myImage) {
        this.myImage = myImage;
    }
}

