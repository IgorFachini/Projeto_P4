/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftd.educational.mvc.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.entities.Genero;

/**
 *
 * @author Cyber
 */
@WebServlet(name = "generoServlet", urlPatterns = {"/GeneroControllerServlet"})
public class GeneroControllerServlet extends HttpServlet {
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
    GeneroDAO dao;
    //private static String INSERIR = "/AjouterUtilisateur.jsp";//variable qui indique l'URL
    private static String editar = "/WEB-INF/views/editarGenero.jsp";
   // private static String deletar = "/SupprimerUtilisateur.jsp";
    private static String listar = "/WEB-INF/views/listarGeneros.jsp";

    public GeneroControllerServlet() {
        dao = new GeneroDAO(factory);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             TODO output your page here. You may use following sample code.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GeneroControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GeneroControllerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>"); 
        }*/
    }
    
    private List<Genero> allGeneros() {
        List<Genero> lst = dao.findGeneroEntities();

        return lst;
    }

    private void editGenero(Genero genero) {
        System.out.println("\nupdateGeneroTest");

        try {
            dao.edit(genero);
        } catch (Exception ex) {
            //Logger.getLogger(TestarPersistencia.class.getName()).log(Level.)
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("edit")) {
            forward = editar;
            Long generoId = Long.parseLong(request.getParameter("id"));
            Genero genero = dao.findGenero(generoId);
            request.setAttribute("genero", genero);
        } else {
            forward = listar;
            request.setAttribute("generos", this.allGeneros());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         doGet(request, response);

        Genero genero = new Genero();
        genero.setNome(request.getParameter("nome"));

        String generoId = request.getParameter("id");

        if (generoId == null || generoId.isEmpty()) {
            dao.create(genero);
        } else {
            genero.setId(Long.parseLong(generoId));
            editGenero(genero);
        }
        RequestDispatcher view = request.getRequestDispatcher(listar);
        request.setAttribute("generos", this.allGeneros());
        view.forward(request, response);
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
