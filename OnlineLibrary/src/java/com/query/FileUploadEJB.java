/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.query;

import com.entity.Author;
import com.entity.Book;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maxim
 */
@Stateless
@LocalBean
public class FileUploadEJB {
    @PersistenceContext(unitName = "OnlineLibraryPU")
    private EntityManager em;
    
    /*public void upload(byte[] arrr)
    {   
        try{
            Book comm = new Book();
            Author author = new Author();
            author.setName("Mark");
            List<Book> k = em.createNamedQuery("Book.findByAuthor", Book.class).setParameter("author", author).getResultList();

            //ArrayList<Book> ar = new ArrayList<>();
            author.setBookCollection(k);
            comm.setAuthor(author);
            comm.setName("Hello");
            comm.setData(arrr);
            comm.setGenre("fantastic");
            comm.setPages(15);
            comm.setYear(2019);
            comm.setPhoto("Hello.jpg");
            em.persist(comm);
            author.getBookCollection().add(comm);
            em.persist(comm);
            em.persist(author);
        }
        catch(Exception e)
        {
            return;
        }
        
    }*/
    
    public boolean add(byte[] pdf, String image, String name, String genre, String pages, String year, String auth)
    {
        try
        {
            Book comm = new Book();
            Author author = new Author();
            author.setName(auth);
            List<Book> k = em.createNamedQuery("Book.findByAuthor", Book.class).setParameter("author", author).getResultList();
            author.setBookCollection(k);
            comm.setAuthor(author);
            comm.setName(name);
            comm.setData(pdf);
            comm.setGenre(genre);
            comm.setPages(Integer.parseInt(pages));
            comm.setYear(Integer.parseInt(year));
            comm.setPhoto(image);
            em.persist(comm);
            //comm.setPhoto(image);
            author.getBookCollection().add(comm);
            em.persist(comm);
            if(author.getBookCollection().size() == 1)
            {
                em.persist(author);
            }
            return true;
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Something was wrong!"));
            return false;
        }
    }
    
    public void open() throws IOException
    {
        byte[] dataToSend;  
        
        
        Book list = em.createNamedQuery("Book.findByName", Book.class).setParameter("name", "Harry Potter").getSingleResult();
        dataToSend = list.getData();/*
        
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
        ServletOutputStream out = response.getOutputStream();  
                      
        response.setContentType("application/pdf");  
        response.setContentLength(dataToSend.length);  
        response.setHeader("Content-Disposition", "inline; filename=\"File.pdf\"");  
        out.write(dataToSend);  
        out.flush();  
        FacesContext.getCurrentInstance().responseComplete();*/
        OutputStream out = new FileOutputStream("/home/maxim/out.pdf");
        out.write(dataToSend);
        out.close();
    }
    
    public void show() throws IOException
    {
        byte[] dataToSend;  
        
        
        Book list = em.createNamedQuery("Book.findByName", Book.class).setParameter("name", "qw").getSingleResult();
        dataToSend = list.getData();
        
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
        ServletOutputStream out = response.getOutputStream();  
                      
        response.setContentType("application/pdf");  
        response.setContentLength(dataToSend.length);  
        response.setHeader("Content-Disposition",  
                            "inline; filename=\"File.pdf\"");  
        out.write(dataToSend);  
        out.flush();  
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    /*public DefaultStreamedContent im() throws IOException
    {
        byte[] dataToSend;  
        
        
        Book list = em.createNamedQuery("Book.findByName", Book.class).setParameter("name", "6.jpg").getSingleResult();
        dataToSend = list.getPhoto();
        InputStream is = new ByteArrayInputStream(dataToSend);        
        return new DefaultStreamedContent(is, "image/jpg");
    }*/
}
