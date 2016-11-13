package org.catolica.prog4.persistencia.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.daos.LivroDAO;

import org.catolica.prog4.persistencia.daos.UserDAO;
import org.catolica.prog4.persistencia.entities.Genero;
import org.catolica.prog4.persistencia.entities.Livro;
import org.catolica.prog4.persistencia.entities.User;


public class TestarPersistencia {

    public static void main(String[] args) {
        //deleteAllUsersTest();
        //deleteAllRuleTest();
        
        //createUserTest();
        TestePersistenciaNovos.createGenerotest();
        createLivroTest();
        //createRuletest();
        //findAllUsersTest();
        //findAllRulesTest();

        //updateAllUsersTest();

        //findAllUsersTest();
       if(autenticar("Fabio@prog4.net", "Fabio#12345") != null){
           System.out.println("Achou");
       } else{
           System.out.println("Nao achou");
       }
        

        //findAllUsersTest();
        //findAllRulesTest();

    }
     private static void createLivroTest() {
        System.out.println("\n createLivroTest...");
        Map<String, String> datas = new HashMap<>(8);
        datas.put("Soldado", "Guerra");
        datas.put("Maria", "Romance");
        datas.put("Skrym", "RPG");
        

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        LivroDAO livroDao = new LivroDAO(factory);
        List<Genero> generos = new GeneroDAO(factory).findAll();
        livroDao.findLivroEntities();
        for (Map.Entry<String, String> data : datas.entrySet()) {
            Livro o = new Livro();
            o.setTitulo(data.getKey());
            o.setAutor("teste");
            o.setAnoEdicao(2000);
            o.setValor("22");
            for (Genero genero : generos) {
                if (genero.getNome().equalsIgnoreCase(data.getValue())) {
                    o.setGenero(genero);
                    break;
                }
            }
            livroDao.create(o);
        }
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
