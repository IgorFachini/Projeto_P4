package org.catolica.prog4.persistencia.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.catolica.prog4.persistencia.entities.Genero;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-13T16:35:32")
@StaticMetamodel(Manga.class)
public class Manga_ { 

    public static volatile SingularAttribute<Manga, Genero> genero;
    public static volatile SingularAttribute<Manga, String> valor;
    public static volatile SingularAttribute<Manga, String> titulo;
    public static volatile SingularAttribute<Manga, Long> id;
    public static volatile SingularAttribute<Manga, String> descricao;

}