package org.ftd.educational.mvc.interfaces;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fabio Tavares Dippold
 * 
 */
public interface IWebCmd {
   String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException; 
}
