/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import com.query.FileUploadEJB;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author maxim
 */

@ManagedBean(name = "fileUpload")
@ViewScoped
public class FileUploadBean {
    private UploadedFile pdfFile;
    private UploadedFile imageFile;
    private String name;
    private String genre;
    private String pages;
    private String year;
    private String author;
    @EJB
    private FileUploadEJB bookEJB = new FileUploadEJB();
    
    public void add() throws IOException
    {
        if(bookEJB.add(pdfFile.getContents(), imageFile.getContents(), name, genre, pages, year, author))
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("addBook.xhtml");
        }
    }
    
    /*public void upload() {
        if (pdfFile != null) {
            FacesMessage message = new FacesMessage("Successful", pdfFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            bookEJB.upload(pdfFile.getContents());
        }
    }*/
    
    public void show() throws IOException {
        bookEJB.show();
    }
    
    /*public DefaultStreamedContent im() throws IOException{
        return bookEJB.im();
    }*/
    
    public void open() throws IOException {
        bookEJB.open();
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public UploadedFile getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(UploadedFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    public UploadedFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(UploadedFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
