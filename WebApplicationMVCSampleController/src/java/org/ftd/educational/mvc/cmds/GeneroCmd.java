/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftd.educational.mvc.cmds;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.entities.Genero;
import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;

/**
 *
 * @author Cyber
 */
public class GeneroCmd extends AbstractWebCmd implements IWebCmd {

    private GeneroDAO dao;
    public static final String list = "/WEB-INF/views/listarGeneros.jsp";
    public static final String INSERT_OR_EDIT = "/WEB-INF/views/adicionar_editarGenero.jsp";
    public String forward = "";

    public GeneroCmd() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        dao = new GeneroDAO(factory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setDefaultAppModel(request);

        String action = "";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        if (action.equalsIgnoreCase("editar")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Genero genero = dao.findGenero(id);
            request.setAttribute("genero", genero);
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("editado")) {
            Genero genero = new Genero();
            String generoId = request.getParameter("id");
            genero.setNome(request.getParameter("nome"));

            if (generoId == null || generoId.isEmpty()) {
                dao.create(genero);
            } else {
                genero.setId(Long.parseLong(generoId));
                try {
                    dao.edit(genero);
                } catch (Exception ex) {
                    Logger.getLogger(GeneroCmd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            recarregarListagem(request);

        } else if (action.equalsIgnoreCase("adicionar")) {
            forward = INSERT_OR_EDIT;
        } else {
            recarregarListagem(request);
        }

        //RequestDispatcher view = request.getRequestDispatcher(forward);
        //view.forward(request, response);
        return forward;
    }

    public void recarregarListagem(HttpServletRequest request) {
        forward = list;
        request.setAttribute("generos", this.allGeneros());
    }

    private List<Genero> allGeneros() {

        List<Genero> lst = dao.findGeneroEntities();
        //Iterator<Genero> itr = lst.iterator();
        //lst.get(0).getNome()
        return lst;
    }

}
