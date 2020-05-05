/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maxim
 */
@Entity
@Table(name = "orderok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderok.findAll", query = "SELECT o FROM Orderok o"),
    @NamedQuery(name = "Orderok.findByIdOrder", query = "SELECT o FROM Orderok o WHERE o.idOrder = :idOrder"),
    @NamedQuery(name = "Orderok.findByIdLogin", query = "SELECT o FROM Orderok o WHERE o.login = :login")})
public class Orderok implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_order")
    private int idOrder;
    @JoinColumn(name = "id_login", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Login login;
    @JoinColumn(name = "id_book", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Book product;

    public Orderok() {
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Book getProduct() {
        return product;
    }

    public void setProduct(Book product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idOrder;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orderok other = (Orderok) obj;
        if (this.idOrder != other.idOrder) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orderok{" + "idComment=" + idOrder + ", login=" + login + ", product=" + product + '}';
    }
}
