/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.daos;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cyber
 */
public class GeneroDAO extends GeneroJpaController{
     public GeneroDAO(EntityManagerFactory emf) {
        super(emf);
    }
}
