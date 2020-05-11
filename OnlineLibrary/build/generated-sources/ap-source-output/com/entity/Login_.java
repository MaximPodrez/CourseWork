package com.entity;

import com.entity.Comment;
import com.entity.Orderok;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2020-05-11T14:31:17")
@StaticMetamodel(Login.class)
public class Login_ { 

    public static volatile SingularAttribute<Login, String> password;
    public static volatile ListAttribute<Login, Orderok> orderCollection;
    public static volatile ListAttribute<Login, Comment> commentCollection;
    public static volatile SingularAttribute<Login, String> email;
    public static volatile SingularAttribute<Login, String> username;

}