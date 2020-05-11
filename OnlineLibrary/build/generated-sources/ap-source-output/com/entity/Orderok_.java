package com.entity;

import com.entity.Book;
import com.entity.Login;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2020-05-11T14:31:17")
@StaticMetamodel(Orderok.class)
public class Orderok_ { 

    public static volatile SingularAttribute<Orderok, Integer> idOrder;
    public static volatile SingularAttribute<Orderok, Book> product;
    public static volatile SingularAttribute<Orderok, Login> login;

}