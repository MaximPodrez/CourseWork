/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.query;

import com.entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maxim
 */

@Stateless
@LocalBean
public class MainPaigeEJB {
    @PersistenceContext(unitName = "OnlineLibraryPU")
    private EntityManager em;
    
    public List<String> images()
    {
       Book book = new Book();
       List<Book> k = em.createNamedQuery("Book.findAll", Book.class).getResultList();
       List<String> list = new ArrayList();
       for (Book bk : k) {
            list.add(bk.getPhoto());
        }
       return list;
    }
}
