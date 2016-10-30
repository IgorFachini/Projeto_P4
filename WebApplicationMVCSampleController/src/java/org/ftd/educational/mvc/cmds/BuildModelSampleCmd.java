package org.ftd.educational.mvc.cmds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;

/**
 *
 * @author Fabio Tavares Dippold
 * 
 */
public class BuildModelSampleCmd extends AbstractWebCmd implements IWebCmd {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        super.setDefaultAppModel(request);
        
        
        request.setAttribute("rules", this.findAllRules());
        
        return "/WEB-INF/views/viewSample.jsp";
        
    }
    
    
    private List<Rule> findAllRules() {
        List<Rule> lst = new ArrayList<>();
        lst.add(new Rule(1L, "Administrador") );
        lst.add(new Rule(2L, "Usuário") );
        lst.add(new Rule(3L, "Visitante ") );
        
        return lst;
    }
    
}
