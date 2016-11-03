package org.ftd.educational.mvc.cmds;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;

/**
 *
 * @author FCGF
 */
public class LogoutCmd extends AbstractWebCmd implements IWebCmd {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setDefaultAppModel(request);
        
        HttpSession session = request.getSession(false);
        session.setAttribute("userid", null);
        session.setAttribute("username", null);
        session.setAttribute("userroleid", null);
        
        return "signin.jsp";
    }

}
