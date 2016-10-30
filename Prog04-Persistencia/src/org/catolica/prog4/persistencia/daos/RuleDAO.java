package org.catolica.prog4.persistencia.daos;

import javax.persistence.EntityManagerFactory;

public class RuleDAO extends RuleJpaController {
    
    public RuleDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
}
