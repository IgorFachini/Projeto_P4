/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

import org.catolica.prog4.persistencia.interfaces.IGeneroDAO;
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
}
