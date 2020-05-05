/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import com.entity.Book;
import com.query.CatalogEJB;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;


/**
 *
 * @author maxim
 */

@ManagedBean(name = "catalog")
@ViewScoped
public class CatalogBean {
    private List<Book> books;
    private Book selectedBook;
    @ManagedProperty(value = "#{login}")
    LoginBean loginController;
    @EJB
    private CatalogEJB catalogEJB;
     
    @PostConstruct
    public void init() {
        books = catalogEJB.books();
    }
    
    public void addToBasket() throws IOException
    {
        catalogEJB.addToBasket(selectedBook, loginController.getUsername());
        FacesContext.getCurrentInstance().getExternalContext().redirect("catalog.xhtml");
    }
    
    public void addToBasket(Book prod) throws IOException
    {
        catalogEJB.addToBasket(prod, loginController.getUsername());
        FacesContext.getCurrentInstance().getExternalContext().redirect("catalog.xhtml");
    }
    
    public DefaultStreamedContent image(Book book) throws IOException
    {
        return catalogEJB.im(book.getPhoto(), book);
    }
    
    public void show() throws IOException {
        catalogEJB.show(selectedBook.getData());
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }
    
    public LoginBean getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginBean loginController) {
        this.loginController = loginController;
    }
}
