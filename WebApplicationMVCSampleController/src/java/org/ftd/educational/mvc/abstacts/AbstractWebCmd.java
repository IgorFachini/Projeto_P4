package org.ftd.educational.mvc.abstacts;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fabio Tavares
 * @version 2016-10-24
 * 
 */
public abstract class AbstractWebCmd {

    protected void setDefaultAppModel(HttpServletRequest request) {
        request.setAttribute("appName", "Crud");
    }
    
    
    protected final String readParameter(HttpServletRequest request, String parameterName,
            String defaultValue) {
        String value = request.getParameter(parameterName);
        if ((value == null) || (value.equals(""))) {
            value = defaultValue;
        }

        return value;
    }
    
}
