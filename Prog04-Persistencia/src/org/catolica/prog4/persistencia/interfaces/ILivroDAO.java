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
import org.catolica.prog4.persistencia.entities.Livro;

/**
 *
 * @author Cyber
 */
public interface ILivroDAO {
      //Padrões gerados:
    void create(Livro livro);

    void destroy(Long id) throws NonexistentEntityException;

    void edit(Livro livro) throws NonexistentEntityException, Exception;

    Livro findLivro(Long id);

    List<Livro> findLivroEntities();

    List<Livro> findLivroEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getLivroCount();

    List<Livro> findAll();
    
    List<Livro> findLivro(String pesquisa) throws NoResultException;
}
