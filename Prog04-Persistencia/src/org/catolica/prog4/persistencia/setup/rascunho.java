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
import org.catolica.prog4.persistencia.daos.LivroDAO;
import org.catolica.prog4.persistencia.entities.Genero;
import org.catolica.prog4.persistencia.entities.Livro;

/**
 *
 * @author Cyber
 */
public class rascunho {
    
    public static void main(String[] args) {
        createLivrotest();
    }

    private static void createLivrotest() {
        System.out.println("\ncreateLivrotest...");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        LivroDAO dao = new LivroDAO(factory);
        List<Livro> lst = dao.findLivroEntities();
        
        GeneroDAO daoGnero = new GeneroDAO(factory);
        List<Genero> lstGenero = daoGnero.findGeneroEntities();
        

        Livro o = new Livro();
        o.setTitulo("Soldado");
        o.setAutor("SoldadoVelho");
        o.setAnoEdicao(1996);
        o.setValor(22.34);
        //o.setGeneroId(lstGenero.get(0).getId().intValue());
        o.setGenero(lstGenero.get(0));
       

    }

    private static void findAllLivrosTest() {
        System.out.println("\n findAllLivrosTest...");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        LivroDAO dao = new LivroDAO(factory);
        List<Livro> lst = dao.findLivroEntities();
        for (Livro o : lst) {
            System.out.println(o);
        }
    }

    private static void updateAllLivrosTest() {
        System.out.println("\nupdateAllLivroTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        LivroDAO dao = new LivroDAO(factory);
        List<Livro> lst = dao.findLivroEntities();
        for (Livro o : lst) {
            o.setAutor(o.getAutor() + ".");
            try {
                dao.edit(o);
            } catch (Exception ex) {
                //Logger.getLogger(TestarPersistencia.class.getName()).log(Level.)
            }
        }
    }

    private static void deleteAllLivroTest() {
        System.out.println("\ndeleteAllLivroTest");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        LivroDAO dao = new LivroDAO(factory);
        List<Livro> lst = dao.findLivroEntities();
        for (Livro o : lst) {
            try {
                dao.destroy(o.getId());
            } catch (Exception ex) {
                // Logger.getLogger(TestarPersistencia.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
}
