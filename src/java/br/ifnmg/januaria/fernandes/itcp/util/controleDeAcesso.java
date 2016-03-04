/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.util;

import java.io.IOException;
import java.util.logging.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alisson
 */
@WebFilter(servletNames = {"Faces Servlet"})
public abstract class controleDeAcesso implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if ((session.getAttribute("USUARIOLogado") != null)
                || (req.getRequestURI().endsWith("Login.xhtml"))
                || (req.getRequestURI().contains("javax.faces.resources/"))) {
            
            //redireciona("/inicio.xhtml", responce);
            chain.doFilter(request, response);
        }
        
        else{
            redireciona("/Login.xhtml", response);
        }
    }
    
    public void init(FilterConfig filterConfig) throws ServletException{
    }
    
    public void destroy(){
    }
    
    private void redireciona(String url, ServletResponse response) throws IOException{
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect(url);
    }
}
