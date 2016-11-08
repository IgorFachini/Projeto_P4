/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.catolica.prog4.persistencia.entities.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.catolica.prog4.persistencia.daos.exceptions.IllegalOrphanException;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Genero;

/**
 *
 * @author Cyber
 */
public class GeneroJpaController implements Serializable {

    public GeneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) {
        if (genero.getLivros() == null) {
            genero.setLivros(new ArrayList<Livro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Livro> attachedLivros = new ArrayList<Livro>();
            for (Livro livrosLivroToAttach : genero.getLivros()) {
                livrosLivroToAttach = em.getReference(livrosLivroToAttach.getClass(), livrosLivroToAttach.getId());
                attachedLivros.add(livrosLivroToAttach);
            }
            genero.setLivros(attachedLivros);
            em.persist(genero);
            for (Livro livrosLivro : genero.getLivros()) {
                Genero oldGeneroOfLivrosLivro = livrosLivro.getGenero();
                livrosLivro.setGenero(genero);
                livrosLivro = em.merge(livrosLivro);
                if (oldGeneroOfLivrosLivro != null) {
                    oldGeneroOfLivrosLivro.getLivros().remove(livrosLivro);
                    oldGeneroOfLivrosLivro = em.merge(oldGeneroOfLivrosLivro);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero persistentGenero = em.find(Genero.class, genero.getId());
            List<Livro> livrosOld = persistentGenero.getLivros();
            List<Livro> livrosNew = genero.getLivros();
            List<String> illegalOrphanMessages = null;
            for (Livro livrosOldLivro : livrosOld) {
                if (!livrosNew.contains(livrosOldLivro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Livro " + livrosOldLivro + " since its genero field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Livro> attachedLivrosNew = new ArrayList<Livro>();
            for (Livro livrosNewLivroToAttach : livrosNew) {
                livrosNewLivroToAttach = em.getReference(livrosNewLivroToAttach.getClass(), livrosNewLivroToAttach.getId());
                attachedLivrosNew.add(livrosNewLivroToAttach);
            }
            livrosNew = attachedLivrosNew;
            genero.setLivros(livrosNew);
            genero = em.merge(genero);
            for (Livro livrosNewLivro : livrosNew) {
                if (!livrosOld.contains(livrosNewLivro)) {
                    Genero oldGeneroOfLivrosNewLivro = livrosNewLivro.getGenero();
                    livrosNewLivro.setGenero(genero);
                    livrosNewLivro = em.merge(livrosNewLivro);
                    if (oldGeneroOfLivrosNewLivro != null && !oldGeneroOfLivrosNewLivro.equals(genero)) {
                        oldGeneroOfLivrosNewLivro.getLivros().remove(livrosNewLivro);
                        oldGeneroOfLivrosNewLivro = em.merge(oldGeneroOfLivrosNewLivro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = genero.getId();
                if (findGenero(id) == null) {
                    throw new NonexistentEntityException("The genero with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws  NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero genero;
            try {
                genero = em.getReference(Genero.class, id);
                genero.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Livro> livrosOrphanCheck = genero.getLivros();
            for (Livro livrosOrphanCheckLivro : livrosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Genero (" + genero + ") cannot be destroyed since the Livro " + livrosOrphanCheckLivro + " in its livros field has a non-nullable genero field.");
            }
            if (illegalOrphanMessages != null) {
                //throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Genero findGenero(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genero> rt = cq.from(Genero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
