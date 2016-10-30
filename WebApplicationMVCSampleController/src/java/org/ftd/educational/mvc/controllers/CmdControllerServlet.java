package org.ftd.educational.mvc.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.mvc.interfaces.IWebCmd;

/**
 *
 * @author ftdippold
 */
@WebServlet(name = "CmdControllerServlet", urlPatterns = {"/mvc"}, initParams = {
    @WebInitParam(name = "cmdAddressPackage", value = "org.ftd.educational.mvc.cmds"),
    @WebInitParam(name = "notFoundCmdCmd", value = "notfoundcmd.jsp")})
public class CmdControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            res.setCharacterEncoding("UTF-8");            
            try {
                String cmd = this.readParameter(req, "cmd", getInitParameter("notFoundCmdCmd"));
                String cmdPackage = getInitParameter("cmdAddressPackage");
                Class theClass = Class.forName(cmdPackage + "." + cmd);
                IWebCmd theCmd = (IWebCmd) theClass.newInstance();
                String nextCmd = theCmd.execute(req, res);
                req.getRequestDispatcher(nextCmd).forward(req, res);
            } catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                req.setAttribute("cmd", this.readParameter(req, "cmd", "Não submetido ao controlador!"));
                req.setAttribute("go", this.readParameter(req, "go", "Não submetido ao controlador!"));                
                req.setAttribute("msg", "ERROR NÍVEL-1 MVC: Não encontrei a Classe: " + this.readParameter(req, "cmd", "Não submetido ao controlador!"));
                req.getRequestDispatcher("WEB-INF/views/notfoundcmd.jsp").forward(req, res);
            }        
        
        

    }

    protected final String readParameter(HttpServletRequest request, String parameterName,
            String defaultValue) {
        String value = request.getParameter(parameterName);
        if ((value == null) || (value.equals(""))) {
            value = defaultValue;
        }

        return value;
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
