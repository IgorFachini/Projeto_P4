/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.catolica.prog4.persistencia.daos.LivroDAO;
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.daos.LivroDAO;
import org.catolica.prog4.persistencia.entities.Genero;
import org.catolica.prog4.persistencia.entities.Genero;
import org.catolica.prog4.persistencia.entities.Livro;

/**
 *
 * @author Cyber
 */
public class rascunho {
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
}

  
