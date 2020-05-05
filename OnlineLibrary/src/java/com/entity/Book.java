/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maxim
 */
@Entity
@Table(name = "book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findByName", query = "SELECT b FROM Book b WHERE b.name = :name"),
    @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author"),
    @NamedQuery(name = "Book.findByGenre", query = "SELECT b FROM Book b WHERE b.genre = :genre"),
    @NamedQuery(name = "Book.findByYear", query = "SELECT b FROM Book b WHERE b.year = :year"),
    @NamedQuery(name = "Book.findByPages", query = "SELECT b FROM Book b WHERE b.pages = :pages")})
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "data")
    private byte[] data;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "genre")
    private String genre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pages")
    private int pages;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    private List<Orderok> orderCollection;
    @JoinColumn(name = "author", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Author author;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public Book(String name, byte[] data, byte[] photo, String genre, int year, int pages) {
        this.name = name;
        this.data = data;
        this.photo = photo;
        this.genre = genre;
        this.year = year;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Orderok> getOrderCollection() {
        return orderCollection;
    }

    public void setOrderCollection(List<Orderok> orderCollection) {
        this.orderCollection = orderCollection;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Book[ name=" + name + " ]";
    }
    
}
