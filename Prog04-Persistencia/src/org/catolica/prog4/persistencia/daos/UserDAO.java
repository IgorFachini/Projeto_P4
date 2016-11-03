package org.catolica.prog4.persistencia.daos;

import org.catolica.prog4.persistencia.interfaces.IUserDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.catolica.prog4.persistencia.entities.User;

public class UserDAO extends UserJpaController implements IUserDAO {
    
    public UserDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
    @Override
    public List<User> findAll() {
        return super.findUserEntities();
    }
    
    @Override
    public User findUser(String email, String passwd) throws NoResultException {
        EntityManager em = getEntityManager();
        try {
            return (User) em.createNamedQuery("User.authenticate")
                    .setParameter("email", email)
                    .setParameter("senha", passwd)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }    
    
}
