package org.catolica.prog4.persistencia.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.catolica.prog4.persistencia.entities.Livro;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-07T19:12:14")
@StaticMetamodel(Genero.class)
public class Genero_ { 

    public static volatile ListAttribute<Genero, Livro> livros;
    public static volatile SingularAttribute<Genero, Long> id;
    public static volatile SingularAttribute<Genero, String> Nome;

}