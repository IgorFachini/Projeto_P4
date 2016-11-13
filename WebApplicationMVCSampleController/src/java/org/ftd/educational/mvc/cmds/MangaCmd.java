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
import org.catolica.prog4.persistencia.daos.MangaDAO;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Genero;
import org.catolica.prog4.persistencia.entities.Manga;
import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;
import org.ftd.educational.mvc.utils.util;

/**
 *
 * @author Cyber
 */
public class MangaCmd extends AbstractWebCmd implements IWebCmd {

    private static final String ERRO = "erro";
    private static final String MENSAGEM = "msg";

    private static final String list = "/WEB-INF/views/mangaViews/listarMangas.jsp";
    private static final String INSERT_OR_EDIT = "/WEB-INF/views/mangaViews/adicionar_editarManga.jsp";
    private static final String detalhes = "/WEB-INF/views/mangaViews/detalhesManga.jsp";
    private static final String deletar = "/WEB-INF/views/mangaViews/deletarManga.jsp";
    private String forward = "";
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
    private final MangaDAO mangaDao;
    private final GeneroDAO generoDao;
    private Long id;
    private Manga manga;
    private List<Genero> generos;

    public MangaCmd() {
        generoDao = new GeneroDAO(factory);
        mangaDao = new MangaDAO(factory);
        generos = new ArrayList<>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setDefaultAppModel(request);
        id = null;
        manga = null;
        String action = "";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        switch (action) {
            case "adicionar":
                request.setAttribute("tAdEd", "Adicionar");
                generos = generoDao.findAll();
                request.setAttribute("comboGeneros", generos);
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
                manga = new Manga();
                String mangaId = request.getParameter("id");
                manga.setTitulo(request.getParameter("titulo"));
                Genero genero = new Genero();
                genero = generoDao.findGenero(Long.parseLong(request.getParameter("comboGeneros")));
                manga.setGenero(genero);
                if (request.getParameter("descricao") != null) {
                    manga.setDescricao(request.getParameter("descricao"));
                }
                if (request.getParameter("valor") != null) {
                    manga.setValor(request.getParameter("valor"));
                }

                if (mangaId == null || mangaId.isEmpty()) {
                    mangaDao.create(manga);
                    request.setAttribute(MENSAGEM, "Manga criado com sucesso");
                } else {
                    manga.setId(Long.parseLong(mangaId));
                    try {
                        mangaDao.edit(manga);
                        request.setAttribute(MENSAGEM, "Manga editado com sucesso");
                    } catch (Exception ex) {
                        Logger.getLogger(MangaCmd.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute(ERRO, "Erro ao editar manga");
                    }
                }

                recarregarListagem(request);

                break;

            case "deletar?":
                repassarParamentroParaTela(request, deletar);
                break;
            case "excluido":
                id = Long.parseLong(request.getParameter("id"));
                try {
                    mangaDao.destroy(id);
                    request.setAttribute(MENSAGEM, "Manga excluido com sucesso");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(MangaCmd.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute(ERRO, "Erro ao excluir");
                }
                recarregarListagem(request);
                break;
            case "procurar":

                String conteudo = request.getParameter("cProcurar");
                if (conteudo == null || conteudo.isEmpty()) {
                    recarregarListagem(request);
                } else {

                    List<Manga> mangasFiltrados = new ArrayList<>();

                    allMangas().stream().filter((p) -> ((util.tryParseLong(conteudo) && Long.parseLong(conteudo) == p.getId())
                            || (p.getTitulo().toLowerCase().contains(conteudo))
                            || (p.getValor().contains(conteudo))
                            || (p.getGenero().getNome().toLowerCase().contains(conteudo)))).forEachOrdered((p) -> {
                        mangasFiltrados.add(p);
                    });

                    if (mangasFiltrados.isEmpty()) {
                        request.setAttribute(ERRO, "Nenhum resultado encontrado");
                    }

                    request.setAttribute("mangas", mangasFiltrados);
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
        request.setAttribute("mangas", this.allMangas());
    }

    private List<Manga> allMangas() {

        List<Manga> lst = mangaDao.findMangaEntities();

        return lst;
    }

    private void repassarParamentroParaTela(HttpServletRequest request, String tela) {
        id = Long.parseLong(request.getParameter("id"));
        manga = mangaDao.findManga(id);
        generos = generoDao.findAll();
        request.setAttribute("comboGeneros", generos);
        request.setAttribute("idSelecionado", manga.getGenero().getId());
        request.setAttribute("manga", manga);
        forward = tela;
    }

    private List<Manga> recuperarMangaRelacionadoManga(Long idManga) {
        EntityManager em = mangaDao.getEntityManager();
        em.getTransaction().begin();
        Manga persistentManga = em.find(Manga.class, idManga);
        // List<Manga> mangas = persistentManga.getMangas();
        List<Manga> mangas = null;

        return mangas;
    }

}
