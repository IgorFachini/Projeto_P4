/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Livro;
import org.catolica.prog4.persistencia.interfaces.ILivroDAO;

/**
 *
 * @author Cyber
 */
public class LivroDAO extends LivroJpaController implements ILivroDAO {

    public LivroDAO(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public List<Livro> findAll() {
        return super.findLivroEntities();
    }
}
