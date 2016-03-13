/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifnmg.januaria.fernandes.itcp.util;

import java.io.IOException;
import javax.servlet.Filter;
import java.util.logging.LogRecord;
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
public class controleDeAcesso implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        System.out.println("__________controleDeAcesso(doFilter) Inicio");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if ((session.getAttribute("USUARIOLogado") != null)
                || (req.getServletPath().endsWith("Login.xhtml"))
                || (req.getServletPath().contains("/javax.faces.resource"))) {
            System.out.println("__________controleDeAcesso(doFilter) IF");
            //redireciona("/inicio.xhtml", responce);
            chain.doFilter(request, response);
        } else {
            System.out.println("__________controleDeAcesso(doFilter) ELSE");
            redireciona("/sigitec/Login.xhtml", response);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

    private void redireciona(String url, ServletResponse response) throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect(url);
    }

    public boolean isLoggable(LogRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
