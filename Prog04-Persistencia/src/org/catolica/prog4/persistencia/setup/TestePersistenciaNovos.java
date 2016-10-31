/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.setup;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.entities.Genero;

/**
 *
 * @author Cyber
 */
public class TestePersistenciaNovos {

    public static void main(String[] args) {
        createGenerotest();
        findAllGenerosTest();
        //updateAllGenerosTest();
        //findAllGenerosTest();
        //deleteAllGeneroTest();
        //findAllGenerosTest();

    }

    public static void createGenerotest() {
        System.out.println("\ncreateGenerotest...");
        String[] data = {"Game", "Guerra", "Locura"};
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        GeneroDAO dao = new GeneroDAO(factory);
        List<Genero> lst = dao.findGeneroEntities();
        for (int i = 0; i < data.length; i++) {
            Genero o = new Genero();
            o.setNome(data[i]);
            dao.create(o);
        }
    }

    private static void findAllGenerosTest() {
        System.out.println("\n findAllGenerosTest...");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        GeneroDAO dao = new GeneroDAO(factory);
        List<Genero> lst = dao.findGeneroEntities();
        for (Genero o : lst) {
            System.out.println(o);
        }
    }

    private static void updateAllGenerosTest() {
        System.out.println("\nupdateAllGeneroTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        GeneroDAO dao = new GeneroDAO(factory);
        List<Genero> lst = dao.findGeneroEntities();
        for (Genero o : lst) {
            o.setNome(o.getNome() + ".");
            try {
                dao.edit(o);
            } catch (Exception ex) {
                //Logger.getLogger(TestarPersistencia.class.getName()).log(Level.)
            }
        }
    }

    private static void deleteAllGeneroTest() {
        System.out.println("\ndeleteAllGeneroTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        GeneroDAO dao = new GeneroDAO(factory);
        List<Genero> lst = dao.findGeneroEntities();
        for (Genero o : lst) {
            try {
                dao.destroy(o.getId());
            } catch (Exception ex) {
                // Logger.getLogger(TestarPersistencia.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
}
