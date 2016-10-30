package org.catolica.prog4.persistencia.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.catolica.prog4.persistencia.entities.Genero;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-30T17:23:26")
@StaticMetamodel(Livro.class)
public class Livro_ { 

    public static volatile SingularAttribute<Livro, Genero> Genero;
    public static volatile SingularAttribute<Livro, String> Autor;
    public static volatile SingularAttribute<Livro, Long> id;
    public static volatile SingularAttribute<Livro, String> Titulo;
    public static volatile SingularAttribute<Livro, Integer> AnoEdicao;
    public static volatile SingularAttribute<Livro, Double> Valor;

}