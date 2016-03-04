package br.ifnmg.januaria.fernandes.itcp.util;

import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alisson
 */
@WebServlet(name = "initDB", urlPatterns = {"/initDB"}, loadOnStartup = 1)
public class initDB extends HttpServlet {
    @Override
    public void init() throws ServletException{
        super.init();
        
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("sigitecPU");
            
            EntityManager em = emf.createEntityManager();
            
            em.getTransaction().begin();

            em.getTransaction().commit();
            
            em.close();
        }catch(Exception e){
            throw new RuntimeException("DEU MERDA", e);
        }
    }
}