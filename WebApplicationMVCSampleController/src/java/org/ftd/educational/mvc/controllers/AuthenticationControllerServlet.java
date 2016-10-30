package org.ftd.educational.mvc.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.catolica.prog4.persistencia.daos.UserDAO;
import org.catolica.prog4.persistencia.entities.User;




/**
 *
 * @author Cyber
 */
@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/signin"})
public class AuthenticationControllerServlet extends HttpServlet {


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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        User user;
        String email = request.getParameter("email");
        String passwd = request.getParameter("senha");
        if ((email != null) && (passwd != null)) {
            user = this.findUser(email, passwd);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userid", Long.toString(user.getId()));
                session.setAttribute("username", user.getNome());
                request.getRequestDispatcher("mvc?cmd=GeneroCmd").forward(request, response);
            } else {
                request.setAttribute("msg", "A Senha informada é inválida ou seu usuário está bloqueado!");
                request.getRequestDispatcher("signin.jsp").forward(request, response);
            }
        } else {
            //SystemLogService.getInstance().doLog("Autenticação sem informação foi solicitada!");
                
            request.setAttribute("msg", "Informe um email e senha para iniciar uma autenticação!");
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
    }


    private User findUser(String email, String passwd) {
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
