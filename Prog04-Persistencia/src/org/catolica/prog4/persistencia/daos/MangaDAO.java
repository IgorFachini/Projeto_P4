/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.catolica.prog4.persistencia.entities.Manga;
import org.catolica.prog4.persistencia.interfaces.IMangaDAO;

/**
 *
 * @author Cyber
 */
public class MangaDAO extends MangaJpaController implements IMangaDAO {

    public MangaDAO(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public List<Manga> findAll() {
        return super.findMangaEntities();
    }   
}
