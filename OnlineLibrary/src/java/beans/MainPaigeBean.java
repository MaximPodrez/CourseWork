/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import com.query.MainPaigeEJB;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;


/**
 *
 * @author maxim
 */

@ManagedBean(name = "mainPaige")
@ViewScoped
public class MainPaigeBean {
    @ManagedProperty(value = "#{login}")
    LoginBean loginController;
    private List<String> images;
    private List<DefaultStreamedContent> photos;
    @EJB
    MainPaigeEJB mainPage;
    
    @PostConstruct
    public void init() {
        photos = mainPage.photos();
    }
    
    public String validateName(String name)
    {
        return name.substring(0, name.indexOf("."));
    }
 
    public List<String> getImages() {
        return images;
    }
    
    public List<DefaultStreamedContent> getPhotos() {
        return photos;
    }
    
    public void logOut() throws IOException
    {
        loginController.setUsername(null);
        loginController.setPassword(null);
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }

    public LoginBean getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginBean loginController) {
        this.loginController = loginController;
    }
}
