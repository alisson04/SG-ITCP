package br.ifnmg.januaria.fernandes.itcp.util;

import br.ifnmg.januaria.fernandes.itcp.domain.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import java.util.logging.LogRecord;
import javax.faces.context.FacesContext;
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

    private List<String> listaIds;

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        System.out.println("__________controleDeAcesso(doFilter) Inicio");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if ((req.getServletPath().endsWith("Login.xhtml"))
                || (req.getServletPath().contains("/javax.faces.resource"))
                || session.getAttribute("USUARIOLogado") != null) {
            if (session.getAttribute("USUARIOLogado") != null) {
                if (!"Coordenador".equals(((Usuario) session.getAttribute("USUARIOLogado")).getCargo())
                        && req.getServletPath().endsWith("CadastroUsuario.xhtml")) {
                    System.out.println("5");
                    redireciona("/sigitec/inicio.xhtml", response);
                } else {
                    System.out.println("2");
                    chain.doFilter(request, response);
                }
            } else {
                System.out.println("3");
                chain.doFilter(request, response);
            }
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
