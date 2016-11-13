/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.interfaces;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Manga;

/**
 *
 * @author Cyber
 */
public interface IMangaDAO {
    
       void create(Manga manga);

    void destroy(Long id) throws NonexistentEntityException;

    void edit(Manga manga) throws NonexistentEntityException, Exception;

    Manga findManga(Long id);

    List<Manga> findMangaEntities();

    List<Manga> findMangaEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getMangaCount();

    List<Manga> findAll();
    
    
    
}
