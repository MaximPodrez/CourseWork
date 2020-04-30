/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.query;

import com.entity.Login;
import com.entity.Orderok;
import com.entity.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
public class CatalogEJB {
    @PersistenceContext(unitName = "OnlineLibraryPU")
    private EntityManager em;
    
    public List<Book> books()
    {
       List<Book> k = em.createNamedQuery("Book.findAll", Book.class).getResultList();
       return k;
    }
    
    public List<Book> basketBooks(String username)
    {
       Login log = em.createNamedQuery("Login.findByUsername", Login.class).setParameter("username", username).getSingleResult();
       List<Orderok> list;
       List<Book> basketList = new ArrayList();
       list = log.getOrderCollection();
        for (Orderok list1 : list) {
            basketList.add(list1.getProduct());
        }
       return basketList;
    }
    
    public void delete(Book product, String username)
    {
        Login log = em.createNamedQuery("Login.findByUsername", Login.class).setParameter("username", username).getSingleResult();
        List<Orderok> list;
        list = log.getOrderCollection();
        for(int i = 0; i < list.size(); i++)
        {
            if(log.getOrderCollection().get(i).getProduct().getName().equals(product.getName()))
                log.getOrderCollection().remove(i);
        }
    }
    
    public void addToBasket(Book product, String username)
    {
        int p = 0;
        Login log = em.createNamedQuery("Login.findByUsername", Login.class).setParameter("username", username).getSingleResult();
        for(int i = 0; i < log.getOrderCollection().size(); i++)
        {
            if(log.getOrderCollection().get(i).getProduct().equals(product)){
                p++;
                break;
            }
        }
            
        if(p == 0)
        {
            Orderok order = new Orderok();
            order.setLogin(log);
            order.setProduct(product);
            log.getOrderCollection().add(order);
        }
    }
    
    public void show(byte[] dataToSend) throws IOException
    {
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
}
