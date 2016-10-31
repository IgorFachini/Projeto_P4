package org.catolica.prog4.persistencia.setup;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.catolica.prog4.persistencia.daos.RuleDAO;
import org.catolica.prog4.persistencia.daos.UserDAO;
import org.catolica.prog4.persistencia.entities.Rule;
import org.catolica.prog4.persistencia.entities.User;


public class TestarPersistencia {

    public static void main(String[] args) {
        //deleteAllUsersTest();
        //deleteAllRuleTest();
        
        createUserTest();
        TestePersistenciaNovos.createGenerotest();
        //createRuletest();
        findAllUsersTest();
        //findAllRulesTest();

        updateAllUsersTest();

        //findAllUsersTest();
       if(autenticar("Fabio@prog4.net", "Fabio#12345") != null){
           System.out.println("Achou");
       } else{
           System.out.println("Nao achou");
       }
        

        //findAllUsersTest();
        //findAllRulesTest();

    }

    private static void findAllUsersTest() {
        System.out.println("\nfindAllUsersTest...");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        UserDAO dao = new UserDAO(factory);
        List<User> lst = dao.findAll();
        for (User o : lst) {
            System.out.println(o);

        }
    }

    private static void findAllRulesTest() {
        System.out.println("\n findAllRulesTest...");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        RuleDAO dao = new RuleDAO(factory);
        List<Rule> lst = dao.findRuleEntities();
        for (Rule o : lst) {
            System.out.println(o);
        }
    }

    private static void createUserTest() {
        System.out.println("\ncreateTest...");
        String[] data = {"Fabio", "João", "José", "Galateo", "Bill"};
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        UserDAO dao = new UserDAO(factory);
        List<User> lst = dao.findUserEntities();
        for (int i = 0; i < data.length; i++) {
            User o = new User();
            o.setNome(data[i]);
            o.setEmail(data[i] + "@" + "prog4.net");
            o.setSenha(data[i] + "#12345");
            dao.create(o);
        }
        User o = new User();
        o.setNome("1");
        o.setEmail("1");
        o.setSenha("1");
        dao.create(o);
    }

    private static void createRuletest() {
        System.out.println("\ncreateRuletest...");
        String[] data = {"Administrador", "Comprador", "Vendedor", "Gerente", "Diretor"};
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        RuleDAO dao = new RuleDAO(factory);
        List<Rule> lst = dao.findRuleEntities();
        for (int i = 0; i < data.length; i++) {
            Rule o = new Rule();
            o.setNome(data[i]);
            dao.create(o);
        }
    }
    //==========================================================================================
    
    //========================================================================================== 
     
    

    private static void updateAllUsersTest() {
        System.out.println("\nupdateAllUsersTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        UserDAO dao = new UserDAO(factory);
        List<User> lst = dao.findAll();
        for (User o : lst) {
            o.setNome(o.getNome() + ".");
            try {
                dao.edit(o);
            } catch (Exception ex) {
                //Logger.getLogger(TestarPersistencia.class.getName()).log(Level.)
            }
        }
    }

    private static void deleteAllUsersTest() {
        System.out.println("\ndeleteAllUsersTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        UserDAO dao = new UserDAO(factory);
        List<User> lst = dao.findAll();
        for (User o : lst) {
            try {
                dao.destroy(o.getId());
            } catch (Exception ex) {
                //Logger.getLogger(TestarPersistencia.class.getName()).log(Level.)
            }
        }
    }

    private static void deleteAllRuleTest() {
        System.out.println("\ndeleteAllRuleTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        RuleDAO dao = new RuleDAO(factory);
        List<Rule> lst = dao.findRuleEntities();
        for (Rule o : lst) {
            try {
                dao.destroy(o.getId());
            } catch (Exception ex) {
               // Logger.getLogger(TestarPersistencia.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }

    private static User autenticar(String email, String passwd) {
        final String PERSISTENCE_UNIT_NAME = "PersistenciaPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        UserDAO dao = new UserDAO(factory);
        User user;
        try {
            user = dao.findUser(email, passwd);
        } catch (NoResultException e) {
            user = null;
        }

        return user;
    }

}
