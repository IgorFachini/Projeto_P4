/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.catolica.prog4.persistencia.entities.Genero;

/**
 *
 * @author Cyber
 */
public class GeneroDAO extends GeneroJpaController {

    public GeneroDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
   
}
