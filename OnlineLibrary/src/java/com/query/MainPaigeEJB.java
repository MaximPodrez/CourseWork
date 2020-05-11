/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.query;

import com.entity.Book;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author maxim
 */

@Stateless
@LocalBean
public class MainPaigeEJB {
    @PersistenceContext(unitName = "OnlineLibraryPU")
    private EntityManager em;
    
    public List<DefaultStreamedContent> photos()
    {
        List<Book> k = em.createNamedQuery("Book.findAll", Book.class).getResultList();
        List<DefaultStreamedContent> list = new ArrayList();
        for (Book bk : k) {
            InputStream is = new ByteArrayInputStream(bk.getPhoto());        
            list.add(new DefaultStreamedContent(is, "image/jpg"));
        }
        return list;
    }
}
