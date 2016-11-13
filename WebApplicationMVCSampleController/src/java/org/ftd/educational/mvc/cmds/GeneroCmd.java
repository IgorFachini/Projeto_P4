/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftd.educational.mvc.cmds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.daos.exceptions.IllegalOrphanException;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Genero;
import org.catolica.prog4.persistencia.entities.Livro;
import org.catolica.prog4.persistencia.entities.Manga;
import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;
import org.ftd.educational.mvc.utils.util;

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
    private EntityManager em = null;

    public GeneroCmd() {

        dao = new GeneroDAO(factory);
        em = dao.getEntityManager();
        em.getTransaction().begin();

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
            case "adicionar":
                request.setAttribute("tAdEd", "Adicionar");
                forward = INSERT_OR_EDIT;
                break;
            case "detalhes":
                repassarParamentroParaTela(request, detalhes);
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
                    genero.setLivros(recuperarLivroRelacionadoGenero(Long.parseLong(generoId)));
                    genero.setMangas(recuperarMangaRelacionadoGanero(Long.parseLong(generoId)));
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

            case "deletar?":
                repassarParamentroParaTela(request, deletar);
                if (recuperarLivroRelacionadoGenero(id).size() > 0) {
                    request.setAttribute("botaoDesabilitado", "disabled");
                   

                    request.setAttribute("msgLivros", recuperarLivroRelacionadoGenero(id).size() + " livro(s)");

                }

                if (recuperarMangaRelacionadoGanero(id).size() > 0) {
                    request.setAttribute("botaoDesabilitado", "disabled");
                    if (recuperarLivroRelacionadoGenero(id).size() > 0) {
                        request.setAttribute("e", " e");
                    }
                    request.setAttribute("msgMangas", recuperarMangaRelacionadoGanero(id).size() + " manga(s)");

                }
                if (!recuperarMangaRelacionadoGanero(id).isEmpty() || !recuperarLivroRelacionadoGenero(id).isEmpty()) {
                    request.setAttribute("esseGenero", " relacionado a esse genero");
                }

                if (recuperarMangaRelacionadoGanero(id).isEmpty() && recuperarLivroRelacionadoGenero(id).isEmpty()) {

                    request.setAttribute("botaoDesabilitado", "");
                    request.setAttribute("msgLivros", "");
                    request.setAttribute("msgMangas", "");
                    request.setAttribute("e", "");

                }
                break;
            case "excluido":
                id = Long.parseLong(request.getParameter("id"));
                try {
                    dao.destroy(id);
                    request.setAttribute(MENSAGEM, "Genero excluido com sucesso");
                } catch (NonexistentEntityException ex) {
                    //Logger.getLogger(GeneroCmd.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute(ERRO, "Erro ao excluir");
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(GeneroCmd.class.getName()).log(Level.SEVERE, null, ex);
                }
                recarregarListagem(request);
                break;

            case "procurar":

                String conteudo = request.getParameter("cProcurar");
                if (conteudo == null || conteudo.isEmpty()) {
                    recarregarListagem(request);
                } else {
                    List<Genero> generosFiltrados = new ArrayList<>();

                    allGeneros().stream().filter((p) -> ((util.tryParseLong(conteudo) && Long.parseLong(conteudo) == p.getId())
                            || (p.getNome().toLowerCase().contains(conteudo)))).forEachOrdered((p) -> {
                        generosFiltrados.add(p);
                    });

                    request.setAttribute("generos", generosFiltrados);

                    if (generosFiltrados.isEmpty()) {
                        request.setAttribute(ERRO, "Nenhum resultado encontrado");
                    }
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

        if (recuperarLivroRelacionadoGenero(id).size() > 0) {
            request.setAttribute("livros", recuperarLivroRelacionadoGenero(id));
            request.setAttribute("msgLivros", "Livros relacionado a esse genero");
        }

        if (recuperarMangaRelacionadoGanero(id).size() > 0) {
            request.setAttribute("mangas", recuperarMangaRelacionadoGanero(id));
            request.setAttribute("msgMangas", "Mangas relacionado a esse genero");
        }

        genero = dao.findGenero(id);
        request.setAttribute("genero", genero);
        forward = tela;
    }

    private List<Livro> recuperarLivroRelacionadoGenero(Long idGenero) {

        Genero persistGenero = em.find(Genero.class, idGenero);
        List<Livro> livros = persistGenero.getLivros();

        return livros;
    }

    private List<Manga> recuperarMangaRelacionadoGanero(Long idGenero) {

        Genero persistGenero = em.find(Genero.class, idGenero);
        List<Manga> mangas = persistGenero.getMangas();

        return mangas;
    }

}
