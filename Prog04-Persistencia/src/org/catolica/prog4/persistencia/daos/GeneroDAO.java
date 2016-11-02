/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.catolica.prog4.persistencia.entities.Genero;

/**
 *
 * @author Cyber
 */
public class GeneroDAO extends GeneroJpaController implements IGeneroDAO {

    public GeneroDAO(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public List<Genero> findAll() {
        return super.findGeneroEntities();
    }

    @Override
    public List<Genero> findGenero(String pesquisa) throws NoResultException {
        EntityManager em = getEntityManager();
        List<Genero> generos = null;
        generos = new ArrayList<>();
        try {
            generos = em.createNamedQuery("Genero.procurar")
                    //.setParameter("id", pesquisa)
                    //.setParameter("id", "%" + pesquisa + "%")
                    .setParameter("Nome", "%" + pesquisa + "%")
                    .setMaxResults(10)
                    .getResultList();
        } finally {
            em.close();
        }

        return generos;

        //return generos;
    }

}
