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
import org.catolica.prog4.persistencia.daos.LivroDAO;
import org.catolica.prog4.persistencia.daos.exceptions.NonexistentEntityException;
import org.catolica.prog4.persistencia.entities.Genero;
import org.catolica.prog4.persistencia.entities.Livro;
import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;
import org.ftd.educational.mvc.utils.util;

/**
 *
 * @author Cyber
 */
public class LivroCmd extends AbstractWebCmd implements IWebCmd {

    private static final String ERRO = "erro";
    private static final String MENSAGEM = "msg";

    private static final String list = "/WEB-INF/views/livroViews/listarLivros.jsp";
    private static final String INSERT_OR_EDIT = "/WEB-INF/views/livroViews/adicionar_editarLivro.jsp";
    private static final String detalhes = "/WEB-INF/views/livroViews/detalhesLivro.jsp";
    private static final String deletar = "/WEB-INF/views/livroViews/deletarLivro.jsp";
    private String forward = "";
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
    private final LivroDAO livroDao;
    private final GeneroDAO generoDao;
    private Long id;
    private Livro livro;
    private List<Genero> generos;

    public LivroCmd() {
        generoDao = new GeneroDAO(factory);
        livroDao = new LivroDAO(factory);
        generos = new ArrayList<>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setDefaultAppModel(request);
        id = null;
        livro = null;
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

                livro = new Livro();
                String livroId = request.getParameter("id");
                livro.setTitulo(request.getParameter("titulo"));
                Genero genero = new Genero();
                genero = generoDao.findGenero(Long.parseLong(request.getParameter("comboGeneros")));
                livro.setGenero(genero);
                if (request.getParameter("autor") != null) {
                    livro.setAutor(request.getParameter("autor"));
                }
                if (request.getParameter("ano") != null) {
                    livro.setAnoEdicao(Integer.parseInt(request.getParameter("ano")));
                }
                if (request.getParameter("valor") != null) {
                    livro.setValor(request.getParameter("valor"));
                }

                if (livroId == null || livroId.isEmpty()) {
                    livroDao.create(livro);
                    request.setAttribute(MENSAGEM, "Livro criado com sucesso");
                } else {
                    livro.setId(Long.parseLong(livroId));
                    try {
                        livroDao.edit(livro);
                        request.setAttribute(MENSAGEM, "Livro editado com sucesso");
                    } catch (Exception ex) {
                        Logger.getLogger(LivroCmd.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute(ERRO, "Erro ao editar livro");
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
                    livroDao.destroy(id);
                    request.setAttribute(MENSAGEM, "Livro excluido com sucesso");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(LivroCmd.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute(ERRO, "Erro ao excluir");
                }
                recarregarListagem(request);
                break;
            case "procurar":

                String conteudo = request.getParameter("cProcurar");
                if (conteudo == null || conteudo.isEmpty()) {
                    recarregarListagem(request);
                } else {

                    List<Livro> livrosFiltrados = new ArrayList<>();

                    allLivros().stream().filter((p) -> ((util.tryParseLong(conteudo) && Long.parseLong(conteudo) == p.getId())
                            || (p.getTitulo().toLowerCase().contains(conteudo))
                            || (p.getValor().contains(conteudo))
                            || (p.getGenero().getNome().toLowerCase().contains(conteudo)))).forEachOrdered((p) -> {
                        livrosFiltrados.add(p);
                    });

                    if (livrosFiltrados.isEmpty()) {
                        request.setAttribute(ERRO, "Nenhum resultado encontrado");
                    }

                    request.setAttribute("livros", livrosFiltrados);

                    if (livrosFiltrados.isEmpty()) {
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
        request.setAttribute("livros", this.allLivros());
    }

    private List<Livro> allLivros() {

        List<Livro> lst = livroDao.findLivroEntities();

        return lst;
    }

    private void repassarParamentroParaTela(HttpServletRequest request, String tela) {
        id = Long.parseLong(request.getParameter("id"));
        livro = livroDao.findLivro(id);
        generos = generoDao.findAll();
        request.setAttribute("comboGeneros", generos);
        request.setAttribute("idSelecionado", livro.getGenero().getId());
        request.setAttribute("livro", livro);
        forward = tela;
    }
;
}
