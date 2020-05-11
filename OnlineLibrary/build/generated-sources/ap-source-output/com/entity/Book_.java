package com.entity;

import com.entity.Author;
import com.entity.Orderok;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2020-05-11T14:31:17")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, Integer> pages;
    public static volatile SingularAttribute<Book, byte[]> data;
    public static volatile SingularAttribute<Book, Integer> year;
    public static volatile ListAttribute<Book, Orderok> orderCollection;
    public static volatile SingularAttribute<Book, Author> author;
    public static volatile SingularAttribute<Book, String> name;
    public static volatile SingularAttribute<Book, String> genre;
    public static volatile SingularAttribute<Book, byte[]> photo;

}