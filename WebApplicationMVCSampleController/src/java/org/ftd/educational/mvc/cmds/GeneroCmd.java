/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftd.educational.mvc.cmds;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.catolica.prog4.persistencia.daos.GeneroDAO;
import org.catolica.prog4.persistencia.entities.Genero;
import org.ftd.educational.mvc.abstacts.AbstractWebCmd;
import org.ftd.educational.mvc.interfaces.IWebCmd;

/**
 *
 * @author Cyber
 */
public class GeneroCmd extends AbstractWebCmd implements IWebCmd{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.setDefaultAppModel(request);
        
        
        request.setAttribute("generos", this.allGeneros());
        
        return "/WEB-INF/views/listarGeneros.jsp";
    }
    
    private List<Genero> allGeneros(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenciaPU");
        GeneroDAO dao = new GeneroDAO(factory);
        List<Genero> lst = dao.findGeneroEntities();
	//Iterator<Genero> itr = lst.iterator();
        //lst.get(0).getNome()
        return  lst;
    }
    
          
}
