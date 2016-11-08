/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftd.educational.mvc.cmds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.daos.LivroDAO;
import org.catolica.prog4.persistencia.daos.exceptions.IllegalOrphanException;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Genero;
import org.catolica.prog4.persistencia.entities.Livro;
import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;

/**
 *
 * @author Cyber
 */
public class GeneroCmd extends AbstractWebCmd implements IWebCmd {
    private static final String ERRO = "erro";
    private static final String MENSAGEM = "msg";
    
    private static final String list = "/WEB-INF/views/generoViews/listarGeneros.jsp";
    private static final String INSERT_OR_EDIT = "/WEB-INF/views/generoViews/adicionar_editarGenero.jsp";
    private static final String detalhes = "/WEB-INF/views/generoViews/detalhesGenero.jsp";
    private static final String deletar = "/WEB-INF/views/generoViews/deletarGenero.jsp";
    private String forward = "";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
    private final GeneroDAO dao;
    private Long id;
    private Genero genero;

    public GeneroCmd() {

        dao = new GeneroDAO(factory);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setDefaultAppModel(request);
        id = null;
        genero = null;
        String action = "";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        switch (action) {
            case "detalhes":
                repassarParamentroParaTela(request, detalhes);
                break;
            case "deletar?":
                repassarParamentroParaTela(request, deletar);
                id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("livros", recuperarLivroRelacionadoGenero(id));
                break;
            case "excluido":
                id = Long.parseLong(request.getParameter("id"));
                try {
                    dao.destroy(id);
                    request.setAttribute(MENSAGEM, "Genero excluido com sucesso");
                } catch (NonexistentEntityException ex) {
                    //Logger.getLogger(GeneroCmd.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute(ERRO, "Erro ao excluir");
                }
                recarregarListagem(request);
                break;
            case "editar":

                request.setAttribute("tAdEd", "Editar");
                repassarParamentroParaTela(request, INSERT_OR_EDIT);

                break;
            case "editado":

                genero = new Genero();
                String generoId = request.getParameter("id");
                genero.setNome(request.getParameter("nome"));
                

                if (generoId == null || generoId.isEmpty()) {
                    dao.create(genero);
                    request.setAttribute(MENSAGEM, "Genero criado com sucesso");
                } else {
                    genero.setId(Long.parseLong(generoId));
                   // genero.setLivros(recuperarLivroRelacionadoGenero(Long.parseLong(generoId)));
                    try {
                        dao.edit(genero);
                        request.setAttribute(MENSAGEM, "Genero editado com sucesso");
                    } catch (Exception ex) {
                        Logger.getLogger(GeneroCmd.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute(ERRO, "Erro ao editar ou criar genero");
                    }
                }

                recarregarListagem(request);

                break;
            case "adicionar":
                request.setAttribute("tAdEd", "Adicionar");
                forward = INSERT_OR_EDIT;
                break;
            case "procurar":

                String conteudo = request.getParameter("cProcurar");
                if (conteudo == null || conteudo.isEmpty()) {
                    recarregarListagem(request);
                } else {
                    List<Genero> generos;
                    generos = new ArrayList<>();
                    generos = dao.findGenero(conteudo);
                    request.setAttribute("generos", generos);
                }

                forward = list;
                break;
            default:
                recarregarListagem(request);
                break;
        }

        return forward;
    }

    public void recarregarListagem(HttpServletRequest request) {
        forward = list;
        request.setAttribute("generos", this.allGeneros());
    }

    private List<Genero> allGeneros() {

        List<Genero> lst = dao.findGeneroEntities();

        return lst;
    }

    private void repassarParamentroParaTela(HttpServletRequest request, String tela) {
        id = Long.parseLong(request.getParameter("id"));
        genero = dao.findGenero(id);
        request.setAttribute("genero", genero);
        forward = tela;
    }

    private List<Livro> recuperarLivroRelacionadoGenero(Long idGenero) {
        EntityManager em = null;
        em = dao.getEntityManager();
        em.getTransaction().begin();
        Genero persistentGenero = em.find(Genero.class, idGenero);
        List<Livro> livros = persistentGenero.getLivros();

        return livros;
    }

}
