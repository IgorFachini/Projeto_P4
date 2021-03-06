package org.catolica.prog4.persistencia.interfaces;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.catolica.prog4.persistencia.daos.exceptions.IllegalOrphanException;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Genero;

public interface IGeneroDAO {

    void create(Genero genero);

    void destroy(Long id) throws IllegalOrphanException,NonexistentEntityException;

    void edit(Genero genero) throws IllegalOrphanException,NonexistentEntityException, Exception;

    Genero findGenero(Long id);

    List<Genero> findGeneroEntities();

    List<Genero> findGeneroEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getGeneroCount();

    List<Genero> findAll();
}
