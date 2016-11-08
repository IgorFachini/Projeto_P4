package org.catolica.prog4.persistencia.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.catolica.prog4.persistencia.entities.Genero;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-06T23:01:05")
@StaticMetamodel(Livro.class)
public class Livro_ { 

    public static volatile SingularAttribute<Livro, Genero> genero;
    public static volatile SingularAttribute<Livro, String> titulo;
    public static volatile SingularAttribute<Livro, Integer> anoEdicao;
    public static volatile SingularAttribute<Livro, Long> id;
    public static volatile SingularAttribute<Livro, String> Valor;
    public static volatile SingularAttribute<Livro, String> autor;

}